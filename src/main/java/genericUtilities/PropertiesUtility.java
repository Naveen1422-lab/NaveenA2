package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {

	private Properties property;

	/**
	 * This method is used to initializes properties file
	 * 
	 * @param filepath
	 */

	public void propertiesInit(String filepath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filepath);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readFromProperties(String key) {
		return property.getProperty(key);
	}
}
