package com.openapi.ui_automation;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * This class has all the  property methods to read a content from config file
 */
public class fileUtils {
	private static Logger log = Logger.getLogger(fileUtils.class);
	public static Properties prop;

	public static String readFileAsString(String file) throws Exception {
		return new String(Files.readAllBytes(Paths.get(file)));
	}

	public static FileInputStream getFileInputStream(String relativePath) {
		FileInputStream FIS = null;
		try {
			File file = new File(System.getProperty("user.dir") + relativePath);
			FIS = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FIS;
	}


	public static String getConfigProperty(String property) {
		if(prop==null) {
			prop = new Properties();
			try {
				prop.load(getFileInputStream("/src/test/resources/config.properties"));
			} catch (Exception e) {
				prop=null;
				e.printStackTrace();
			}
		}
		log.info("Property>> "+property+":"+prop.getProperty(property));
		return prop.getProperty(property);
	}

}
