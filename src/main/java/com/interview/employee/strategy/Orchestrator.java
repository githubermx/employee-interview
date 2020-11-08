package com.interview.employee.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.employee.enums.InputFormatEnum;
import com.interview.employee.enums.OrderByCodeEnum;
import com.interview.employee.exceptions.InputFormatNotSupportedException;
import com.interview.employee.model.Employee;
import com.interview.employee.model.Summary;
import com.interview.employee.service.EmployeeWriter;
import com.interview.employee.service.impl.CommaSeparatedProcessor;
import com.interview.employee.service.impl.EmployeeFileWriter;
import com.interview.employee.service.impl.SpacedFilledProcessor;
import com.interview.employee.template.EmployeeProcessorTemplate;

/**
 * Use the strategy design pattern choosing the correct implementation depends
 * on the file format code.
 *
 */
public class Orchestrator {

	private static final Logger logger = LoggerFactory.getLogger(Orchestrator.class);

	private final EmployeeProcessorTemplate readerTemplate;
	private final EmployeeWriter writer;

	public Orchestrator(InputFormatEnum inputFormatEnum) {
		switch (inputFormatEnum) {
		case SPACE_FILLED:
			this.readerTemplate = new SpacedFilledProcessor();
			break;
		case COMMA_SEPARATED:
			this.readerTemplate = new CommaSeparatedProcessor();
			break;
		default:
			throw new InputFormatNotSupportedException();
		}
		this.writer = new EmployeeFileWriter();
	}

	public Summary executeStrategy(Path inputFile, OrderByCodeEnum orderByCodeEnum) throws IOException {

		Summary summary = new Summary();
		/*
		 * 1) Map file line to employee
		 */
		summary.setLines(Files.lines(inputFile).skip(1).count());
		List<Employee> employees = Files.lines(inputFile).skip(1) // Skip the first line because it contains only the
																	// format code (1 or 2)
				.map(readerTemplate::process) // Transform the line to employee
				.filter(Objects::nonNull) // Discard those employees that was impossible to process
				.collect(Collectors.toList());

		if (!employees.isEmpty()) {
			/*
			 * 2) Order
			 */
			summary.setEmployees(employees.size());
			logger.info("Ordering output by {}", orderByCodeEnum);
			employees.sort(orderByCodeEnum.getComparator());

			/*
			 * 3) Write to file
			 */
			writer.writeEmployee(employees, inputFile);
		} else {
			logger.info("Any file line could be turn into an employee record, please verify.");
		}
		return summary;
	}

}
