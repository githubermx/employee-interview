package com.interview.employee.template;

import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.employee.exceptions.InvalidConfigurationException;
import com.interview.employee.exceptions.InvalidFieldException;
import com.interview.employee.exceptions.InvalidLineException;
import com.interview.employee.helper.ReflectionHelper;
import com.interview.employee.helper.ResourceBundleHelper;
import com.interview.employee.model.Employee;

/**
 * Use the template design pattern, the template method is process
 *
 */
public abstract class EmployeeProcessorTemplate {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeProcessorTemplate.class);

	protected final ResourceBundle bundle = ResourceBundle.getBundle("employee_layout");

	protected abstract void validateLine(String line) throws InvalidConfigurationException, InvalidLineException;

	protected abstract List<String> readValues(String line) throws InvalidConfigurationException;

	public final Employee process(String line) {
		try {
			validateLine(line);
			List<String> values = readValues(line);
			return transform(values);
		} catch (InvalidConfigurationException e) {
			logger.error("Configuration error during processing line: [{}], message: [{}], it will be discarded.", line,
					e.getMessage());
		} catch (InvalidLineException | InvalidFieldException e) {
			logger.warn("Data error during processing this line: [{}], message: [{}], it will be discarded.", line,
					e.getMessage());
		}
		return null;
	}

	private Employee transform(List<String> values) throws InvalidConfigurationException, InvalidFieldException {
		Employee employee = new Employee();
		for (int i = 0; i < values.size(); i++) {
			String regex = bundle.getString("field.regex." + i);
			boolean isRequired = ResourceBundleHelper.getBooleanValue(bundle, "field.is.required." + i);
			String defaultValue = ResourceBundleHelper.getOptionalValue(bundle, "field.default." + i);
			String relatedProperty = bundle.getString("field.related.property." + i);

			String value = values.get(i);
			if ("".equals(value)) {
				value = defaultValue;
			}

			if (isRequired && "".equals(value)) {
				throw new InvalidFieldException(String
						.format("Required field was expected field %d related to property %s.", i, relatedProperty));
			}

			if (!value.matches(regex)) {
				throw new InvalidFieldException(
						String.format("Field %d-%s does not match with its regular expression.", i, relatedProperty));
			}

			ReflectionHelper.invokeCorrespondingSetter(employee, relatedProperty, value);
		}

		return employee;
	}

}
