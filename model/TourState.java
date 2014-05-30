import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TourState {
	
	private KeyValueStore store;
	private POIRegistry registry;
	private String SelectedPOI = "SelectedPOI";
	private String SelectedFeatures = "SelectedFeatures";
	
	public TourState(POIRegistry registry, KeyValueStore store) {
		this.registry = registry;
		this.store = store;
	}
	
	/**
	* Set the selected points of interest. *
	* Requires: selectedPOIs is a non-null list and all items in the list
	* are valid registered points of interest *
	* Effects: The selectedPOIs is remembered as selected points of
	* interest using the KeyValueStore object associated with this
	* object and each feature, for which the selectedPOIs include all
	* examples of points in the registry with that feature, becomes
	* selected. The set of selected features is also remembered in
	* the associated KeyValueStore object. */
	
	//Each feature is selected if and only if the selectedPOIs are the only points
	//with this feature.
	
	public void setSelectedPOIs(List<PointOfInterest> selectedPOIs) {
		List<String> ids = new LinkedList<String>();
		List<Feature> selectedfs = new ArrayList<Feature>();
		Set<Feature> unselectedfs = new HashSet<Feature>();
		List<PointOfInterest> allpoi = new ArrayList<PointOfInterest>(registry.getPointsByLocation());
		List<String> listofstring = new LinkedList<String>();
		List<PointOfInterest> unselectedpoi = new LinkedList<PointOfInterest>();
		
		for(PointOfInterest poi : selectedPOIs){
			ids.add(poi.getId());
			
			for (Feature f: poi.getFeatures())
				if (!selectedfs.contains(f)){
					selectedfs.add(f);
				}
		}
		store.putStringList(SelectedPOI, ids);
		
		//for each Feature "f": f is selected if and only if the selected POIs are 
		//the only POIs with this feature.
		//Loop through the  selected POIs, adding their features to a list then 
		//loop through the unselected POIs, removing their features from the list.
	
		for(PointOfInterest p : allpoi) {
			if(!selectedPOIs.contains(p)) {
				unselectedpoi.add(p);
			}
		}
		
		
		for (PointOfInterest p : unselectedpoi) {
			for (Feature f : p.getFeatures()) {
				if (!unselectedfs.contains(f)){
					unselectedfs.add(f);
				}
			}
		}
		
		for (Feature f : unselectedfs){
			if (selectedfs.contains(f)) {
				selectedfs.remove(f);
			}
		}
		
		store.putStringList(SelectedFeatures, listofstring);
	}
	
	
	/** 
	Set the selected features.
	Requires: selectedFeatures is a non-null list and all items in the list 
	are valid features
	Effects: The selectedFeatures is remembered as selected features using the 
	KeyValueStore object associated with this object and the set of POIs that 
	contain a feature in selectedFeatures are selected and remembered in the 
	KeyValueStore object, replacing any previously stored selected POIs.
	*/
	public void setSelectedFeatures(List<Feature> selectedFeatures) {
		List<String> listofstr = new LinkedList<String>();
		List<String> lstpoi = new LinkedList<String>();
		Set<PointOfInterest> poiset = new HashSet<PointOfInterest>();
		
		for (Feature f : selectedFeatures){
			listofstr.add(f.name());
		}
		store.putStringList(SelectedFeatures, listofstr);
		
		for(Feature f : selectedFeatures){
			poiset.addAll(registry.getPointsWithFeature(f));
		}
			
		for(PointOfInterest p : poiset  ) {
			lstpoi.add(p.getId());
		}
		store.putStringList(SelectedPOI, listofstr);
	}
		
	
	/**
	* Get currently selected points of interest from storage in walking
	* tour order. *
	* Effects: return currently selected points of interest in
	* associated KeyValueStore object in walking
	* tour order. If there are no selected points of interest
	* initialized in storage, return all registered points of
	* interest. */ 
	public List<PointOfInterest> getSelectedPOIs() {
		List<PointOfInterest> pois = new LinkedList<PointOfInterest>();
		List<String> storepoi = store.getStringList(SelectedPOI) ;
		
		if (storepoi == null){
			return registry.getPointsByLocation();
		}
		for (String s  : storepoi) {
			pois.add(registry.lookupPoint(s));
		}
		return pois;
	}
	
	
	/**
	* Get currently selected features from storage.
	*
	* Effects: return currently selected features. If there are no * selected features 
	* initialized in storage, return all
	* available features.
	*/
	public List<Feature> getSelectedFeatures() {
		List<Feature> flist = new LinkedList<Feature>();
		List<String> storefeature = store.getStringList(SelectedFeatures) ;
		
		if (storefeature == null ) {
			for(Feature f : Feature.values() ){
				flist.add(f);
			}
			return flist;
		}
		
		for(String s: storefeature){
			Feature.parseFeature(s);
		}
		return flist;
	}

}
