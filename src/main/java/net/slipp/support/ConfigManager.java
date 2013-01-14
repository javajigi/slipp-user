package net.slipp.support;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

@SuppressWarnings("unchecked")
public class ConfigManager {
	public static String environment = null;
	public static Map<String, Object> properties = null;
	
	public static Map<String, Object> getDatabaseConfig() throws FileNotFoundException {
		loadConfiguration();
		
		if(properties == null)
			return null;
		
		return (Map<String, Object>) properties.get(environment);
	}
	
	public static String getEnvironment() {
		return System.getProperty("MYENVIRONMENT","development");
	}
	
	private static void loadConfiguration() {
		try {
			environment = getEnvironment();
			Yaml yaml = new Yaml();
			
			ClassPathResource resource = new ClassPathResource("database.yml");
			
			properties = (Map<String, Object>)yaml.load(resource.getInputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
