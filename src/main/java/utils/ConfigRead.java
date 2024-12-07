package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigRead {

	public static Properties configProperties(String fileName) throws Exception{
		Properties configProperties = new Properties();
		InputStream inputStream = new FileInputStream(fileName.trim());
		
		configProperties.load(inputStream);
		return configProperties;
	}
	
}
