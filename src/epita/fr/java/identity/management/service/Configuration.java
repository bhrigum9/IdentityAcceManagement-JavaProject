package epita.fr.java.identity.management.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import epita.fr.java.identity.management.constants.IdentityConstants;

/**
 * Configuration class to read property files
 * 
 * @author Bhrigu Mahajan
 *
 */
public class Configuration {
	private static final Logger logger = new Logger(Configuration.class);
	private static Configuration instance;

	private final Properties properties;

	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	private Configuration() {
		properties = new Properties();
		try {
			String file = System.getProperty(IdentityConstants.PROP_FILE);

			FileInputStream fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);
		} catch (final IOException e) {
			logger.error("error while loading the configuration", e);
		}
	}

	public String getProperty(String key) {

		return properties.getProperty(key);

	}

	public boolean containsProperty(String key) {
		return properties.containsKey(key);
	}

	public Set<Object> listOfPropertyKeys() {
		return this.properties.keySet();
	}

}
