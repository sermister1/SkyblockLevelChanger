package com.sermister1.sblevelchanger;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class Config {
	public static Configuration config;
	public static String levelColor;
	public static void loadConfig(File configFile) {
	    config = new Configuration(configFile);

	    // Load the configuration file
	    config.load();

	    // Read the value of "levelcolor" from the config file or assign a default value
	    levelColor = config.getString("levelcolor", Configuration.CATEGORY_GENERAL, "f", "The color for the level");
	    
	    // Save the configuration file
	    config.save();
	    if (config.hasChanged()) {
	        config.save();
	    }

	}
	public static void init(File configFile) {
	    // If the config file does not exist, create it
	    if (!configFile.exists()) {
	        try {
	            configFile.createNewFile();
	        } catch (Exception e) {
	            // Handle any IO exception that may occur
	            e.printStackTrace();
	        }
	    }

	    // Load the configuration values from the file
	    loadConfig(configFile);
	}
}
