import java.util.List;

/**
 * Abstract key-value storage interface.  Currently contains only methods for getting and setting
 * string lists -- other types (String, Double, Integer, etc.) could be supported in future.
 * 
 * Defining this interface allows us to test the classes in the model package without having to run the
 * emulator and the Android unit testing framework.
 */
public interface KeyValueStore {
	/** reserved character */
	public static final String RESERVED_CHAR_STRING = new String(";");
	
	/**
	 * Set the value for the specified key.
	 * 
	 * <br><br>
	 * <b>Modifies</b>:<br> this<br><br>
	 * <b>Effects</b>:<br> If key is not null and all strings in value are non-null and do not contain the reserved character<br>
	 * then value is remembered for key, overwriting any existing value stored for key.<br>
	 * Otherwise, IllegalArgumentException is thrown.<br>
	 * 
	 * @param key Key for the value to be set.
	 * @param value The new string list to be assigned to this key (may be null).
	 * 
	 * @throws IllegalArgumentException If key is null or a string in the list is null or a string in the list contains the reserved character.
	 */
	void putStringList(String key, List<String> value);
	
	/**
	 * Get the string list corresponding to the specified key.
	 * 
	 * <br><br>
	 * <b>Effects:</b>:<br> If key is not null, returns value (possibly null) stored for key.<br>
	 * If key is null, throws IllegalArgumentException. 
	 * Don't worry about testing or implementing ClassCastException.
	 *   
	 * @param key The key whose value is to be retrieved.
	 * @return The corresponding string list, or null if a null value has been assigned to the key,
	 *         or if no value has yet been assigned to the key.
	 * 
	 * @throws IllegalArgumentException if key is null
	 * @throws ClassCastException If the entry for the specified key is not convertible to a string list.
	 */
	List<String> getStringList(String key);
}	
