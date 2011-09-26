/**
 * 
 */
package org.openiaml.emf;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;


/**
 * A SoftCache uses SoftReferences to implement a very basic memory cache.
 * Abstract classes have to implement the method which will repopulate 
 * a given key if the reference is lost. 
 * 
 * @author jmwright
 *
 */
public abstract class SoftCache<Source,Result> {

	protected Map<Object,SoftReference<Result>> referenceMap = new HashMap<Object,SoftReference<Result>>();
	
	/**
	 * Manually remove the reference provided by the given key, if it exists.
	 * 
	 * @param key
	 */
	public void remove(Source key) {
		referenceMap.remove(key);
	}
	
	/**
	 * Get a value from the SoftCache. If the value does
	 * not exist, or has been lost, then
	 * {@link #retrieve(Object)} will be used to re-instantiate
	 * it.
	 * 
	 * @param key
	 * @return
	 */
	public Result get(Source key) {
		if (referenceMap.containsKey(key)) {
			// already exists in the map
			SoftReference<Result> ref = referenceMap.get(key);
			Result result = ref.get();
			if (result != null) {
				return result;
			}
		} 
		
		// need to create it 
		Result result = retrieve(key);
		put(key, result);
		// return the result
		return result;
	}
	
	/**
	 * Put a {@link SoftReference} of the given value into the
	 * SoftCache.
	 * 
	 * @param key
	 * @param value
	 */
	public void put(Source key, Result value) {
		SoftReference<Result> soft = new SoftReference<Result>(value);
		referenceMap.put(key, soft);
	}
	
	/**
	 * From a given source input, return a new copy of the 
	 * result. This does not store the value inside the SoftCache.
	 * Abstract classes need to implement this method.
	 * 
	 * @param input The key to load as a cache value
	 * @return A new copy of the cache value for input
	 */
	public abstract Result retrieve(Source input);

}
