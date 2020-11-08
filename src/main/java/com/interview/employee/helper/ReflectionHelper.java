package com.interview.employee.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.interview.employee.exceptions.InvalidConfigurationException;
import com.interview.employee.model.Employee;

public class ReflectionHelper {

	/**
	 * Invoke by reflection the {@link Employee} setters.
	 * 
	 * @param employee        Current employee instance
	 * @param relatedProperty Corresponding key of type field.related.property.0 in
	 *                        employee_layout.properties
	 * @param value           Read value from file
	 * @throws InvalidConfigurationException If declared related property has a
	 *                                       wrong configuration
	 * 
	 */
	public static void invokeCorrespondingSetter(Employee employee, String relatedProperty, String value)
			throws InvalidConfigurationException {
		try {
			String setterName = "set" + relatedProperty.substring(0, 1).toUpperCase() + relatedProperty.substring(1);
			Method setter = Employee.class.getMethod(setterName, String.class);
			setter.invoke(employee, value);
		} catch (IllegalAccessException | IllegalArgumentException e) {
			throw new InvalidConfigurationException(
					String.format("Error on finding setter method for property %s", relatedProperty));
		} catch (InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new InvalidConfigurationException(
					String.format("Error on setting value %s for property %s", value, relatedProperty));
		}
	}
}
