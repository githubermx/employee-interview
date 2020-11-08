package com.interview.employee.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.employee.model.Employee;
import com.interview.employee.service.EmployeeWriter;

public class EmployeeFileWriter implements EmployeeWriter {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeFileWriter.class);

	@Override
	public void writeEmployee(List<Employee> employees, Path inputFile) throws IOException {
		String outputFile = inputFile.toAbsolutePath().toString() + ".out";
		int total = 0;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false))) {
			for (int i = 0; i < employees.size(); i++) {
				writer.append(String.valueOf(i + 1));
				writer.append("\n");
				writer.append(employees.get(i).toHumanReadable());
				total++;
			}
			logger.info("Output file: {} with {} employees.", outputFile, total);
		}
	}

}
