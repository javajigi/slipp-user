package net.slipp.support;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

@SuppressWarnings("unchecked")
public class ConfigManager {
	public static String environment;
	public static Map<String, Object> properties;
	
	public static Map<String, Object> getDatabaseConfig() throws FileNotFoundException {
		loadConfiguration();
		return (Map<String, Object>) properties.get(environment);
	}
	
	public static String getEnvironment() {
		return System.getProperty("MYENVIRONMENT","development");
	}
	
	private static void loadConfiguration() {
		try {
			environment = getEnvironment();
			Yaml yaml = new Yaml();
			FileInputStream input = new FileInputStream("src/main/resources/database.yml");

			properties = (Map<String, Object>)yaml.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
