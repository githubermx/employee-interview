package com.interview.employee.helper;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.interview.employee.exceptions.InvalidConfigurationException;

public class ResourceBundleHelper {

	public static boolean getBooleanValue(ResourceBundle bundle, String key) {
		try {
			return Boolean.parseBoolean(bundle.getString(key));
		} catch (ClassCastException | MissingResourceException e) {
			return false;
		}
	}

	public static String getOptionalValue(ResourceBundle bundle, String key) {
		try {
			return bundle.getString(key);
		} catch (ClassCastException | MissingResourceException e) {
			return "";
		}
	}

	public static Integer getNumericValue(ResourceBundle bundle, String key) throws InvalidConfigurationException {
		try {
			return Integer.parseInt(bundle.getString(key));
		} catch (NumberFormatException | MissingResourceException e) {
			throw new InvalidConfigurationException(String.format("Key %s is not properly configured.", key));
		}
	}

}
