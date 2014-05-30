import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class POIRegistry {
	private static List<PointOfInterest> reg = new LinkedList<PointOfInterest>();
		
	/**
	 * Static default instance.
	 */
	private static POIRegistry defaultInstance = createDefaultInstance();
	
	public static POIRegistry getDefault() {
		return defaultInstance;
	}
	
	public void add(PointOfInterest poi) {
		reg.add(poi);
	}

	/**
	 * Creates the default POIRegistry instance, which contains the 
	 * UBC Sustainability POIs that are listed in UBC-Sustainability-MapInfo.xml
	 */
	private static POIRegistry createDefaultInstance() {
		POIRegistry result = new POIRegistry();
		POIRegistry.getDefault();
		
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(new MapInfoParser(result));
			reader.parse("UBC-Sustainability-MapInfo.xml");
			
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	* Look up a point by id *
	* Requires: id is a valid PointOfInterest id
	* Effects: Returns the registered PointOfInterest matching id */
	public PointOfInterest lookupPoint(String id) {
		for (PointOfInterest p: reg) {
			if (p.getId().equals(id)) {
				return p;
			}
		}
		return null;
	}
		
		
	
	/**
	* Return registered points of interest sorted by display name
	*
	* Effects: Return registered points of interest sorted by display * 
	* name with capitals sorted before lower case
	*/
	public List<PointOfInterest> getPointsAlphabetical() {
		LinkedList<PointOfInterest> newlist = new LinkedList<PointOfInterest>(reg);
		Collections.sort(newlist);
		return newlist;
	}

	
	/**
	* Return registered points of interest in walking tour order. Note
	* that the points of interest are stored in the xml file
	* that you are given in the project description in walking tour
	* order.
	*
	* Effects: Return list of registered points of interest in walking * tour order
	*/
	public List<PointOfInterest> getPointsByLocation() {
		return reg;
	}
		
		
	/**
	* Return registered points of interest with a specified
	* sustainability feature. *
	* Requires: feature is well-formed and not null
	* Effects: Return registered points of interest with specified * feature
	*/
	public List<PointOfInterest> getPointsWithFeature(Feature feature) {
		List<PointOfInterest> result = new LinkedList<PointOfInterest>();
		for (PointOfInterest p: reg){
			if (p.getFeatures().contains(feature)) {
				result.add(p);
			}
		}
		return result;
	}
}
	
	
	
