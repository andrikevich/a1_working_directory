package by.a1.andrikevich.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	public static Properties getProperty(String propertyName) {
		Properties prop = new Properties();
		
        try (InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertyName)) {
        	
	            //load a properties file from class path, inside static method
	            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
	}

}
