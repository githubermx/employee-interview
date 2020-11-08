package com.interview.employee.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.interview.employee.model.Employee;

public interface EmployeeWriter {

	void writeEmployee(List<Employee> employees, Path inputFile) throws IOException;

}
