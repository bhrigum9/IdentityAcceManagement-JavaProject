package epita.fr.java.identity.management.common.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import epita.fr.java.identity.management.service.Configuration;

/**
 * 
 * @author Bhrigu Mahajan
 *
 */
public final class ReadPropertiesFile {

	private ReadPropertiesFile() {
		throw new IllegalStateException("Utility class");
	}
	// method used to store property file objects in a MAP

	public static Map<String, String> readProperties() {
		Map<String, String> propertyMap = new HashMap<>();
		Configuration propMap = Configuration.getInstance();
		Set<Object> set = propMap.listOfPropertyKeys();
		List<Object> list = new ArrayList<>(set);
		for (int i = 0; i < list.size(); i++) {
			String key = (String) list.get(i);
			String value = propMap.getProperty(key);
			propertyMap.put(key, value);
		}
		return propertyMap;
	}
}
