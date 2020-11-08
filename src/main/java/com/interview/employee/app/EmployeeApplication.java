package com.interview.employee.app;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.employee.enums.InputFormatEnum;
import com.interview.employee.enums.OrderByCodeEnum;
import com.interview.employee.model.Summary;
import com.interview.employee.strategy.Orchestrator;

/**
 * Principal orchestrator class
 *
 */
public class EmployeeApplication {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeApplication.class);

	public Summary run(String filePath, OrderByCodeEnum orderByCodeEnum) {
		try {
			logger.info("Processing file {}", filePath);
			/*
			 * Gets first line of file to read the format code (1 or 2)
			 */
			Path inputFile = Paths.get(filePath);
			String firstLine = Files.lines(inputFile).findFirst()
					.orElseThrow(() -> new IllegalArgumentException("Empty file."));

			/*
			 * Using strategy gets the corresponding implementation based on the format code
			 */
			Orchestrator orchestrator = new Orchestrator(InputFormatEnum.fromFormatCode(firstLine));

			/*
			 * Finally processes the file
			 */
			Summary summary = orchestrator.executeStrategy(inputFile, orderByCodeEnum);
			logger.info("Read lines: {}", summary.getLines());
			logger.info("Transformed employees lines: {}", summary.getEmployees());
			return summary;
		} catch (NoSuchFileException e) {
			logger.error("Input file does not exist: {}", filePath);
		} catch (UncheckedIOException e) {
			logger.error("Error on file reading, please verify is a plain text file: {}", e.getMessage());
		} catch (IOException e) {
			logger.error("Unexpected I/O exception: {}", e.getMessage());
		}
		return null;
	}

}
