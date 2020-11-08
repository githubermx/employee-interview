package com.interview.employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.interview.employee.exceptions.InvalidConfigurationException;
import com.interview.employee.exceptions.InvalidLineException;
import com.interview.employee.helper.ResourceBundleHelper;
import com.interview.employee.template.EmployeeProcessorTemplate;

public class CommaSeparatedProcessor extends EmployeeProcessorTemplate {

	@Override
	public void validateLine(String line) throws InvalidConfigurationException, InvalidLineException {
		Integer fieldsNumber = ResourceBundleHelper.getNumericValue(bundle, "fields.number");
		String[] fieldsArray = line.split(",");
		if (fieldsArray.length != fieldsNumber) {
			throw new InvalidLineException(
					String.format("Line does not have the expected fields: %d, actual: %d, line: [%s]", fieldsNumber,
							fieldsArray.length, line));
		}
	}

	@Override
	protected List<String> readValues(String line) {
		List<String> values = new ArrayList<>();
		String[] fieldsArray = line.split(",");
		for (String s : fieldsArray) {
			values.add(s.trim());
		}
		return values;
	}

}
