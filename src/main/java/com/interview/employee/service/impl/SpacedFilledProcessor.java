package com.interview.employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.interview.employee.exceptions.InvalidConfigurationException;
import com.interview.employee.exceptions.InvalidLineException;
import com.interview.employee.helper.ResourceBundleHelper;
import com.interview.employee.template.EmployeeProcessorTemplate;

public class SpacedFilledProcessor extends EmployeeProcessorTemplate {

	@Override
	public void validateLine(String line) throws InvalidConfigurationException, InvalidLineException {
		int recordSize = ResourceBundleHelper.getNumericValue(bundle, "record.size");
		if (line.length() != recordSize) {
			throw new InvalidLineException(
					String.format("Line does not have the expected length: %d, actual: %d", recordSize, line.length()));
		}
	}

	@Override
	protected List<String> readValues(String line) throws InvalidConfigurationException {
		List<String> values = new ArrayList<>();
		int fieldsNumber = ResourceBundleHelper.getNumericValue(bundle, "fields.number");
		int totalLength = 0;
		for (int i = 0; i < fieldsNumber; i++) {
			int fieldLength = ResourceBundleHelper.getNumericValue(bundle, "field.length." + i);
			values.add(line.substring(totalLength, totalLength + fieldLength).trim());
			totalLength += fieldLength;
		}
		return values;
	}

}
