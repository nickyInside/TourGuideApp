/**
 * Enum describing the different sustainability features displayed by the tour sites.
 */
public enum Feature {
	
	BIOFUEL("Biofuel"),
	GEOTHERMAL("Geothermal"),
	LEED_CERTIFICATION("LEED Certification"),
	LOW_IMPACT_MATERIALS("Low Impact Materials"),
	RAINWATER_RECOVERY("Rainwater Recovery"),
	SOLAR_ENERGY("Solar Energy"),
	SUSTAINABLE_AGRICULTURE("Sustainable Agriculture"),
	WASTEWATER_TREATMENT("Wastewater Treatment");
	
	// The string to use to be able to display a feature (e.g., in a user interface)
	private String displayName;

	/**
	 * Provide the display name of a feature
	 * @return feature as a string
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	@Override 
	public String toString() {
		return getDisplayName();
	}

	/**
	 * A private constructor. Called implicitly when features are initialized above.
	 * @param displayName The string to display for a feature.
	 */
	private Feature(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * Parses a feature's display name to produce the corresponding Feature.
	 * @param displayName  the display name of a Feature
	 * @throws IllegalArgumentException if there is no Feature with the given display name
	 * @return the corresponding Feature
	 */
	public static Feature parseFeature(String displayName) {
		for (Feature f : Feature.values()) {
			if (f.getDisplayName().equals(displayName)) {
				return f;
			}
		}
		throw new IllegalArgumentException("No Feature with displayName: \"" + displayName + "\"");
	}
	
}
