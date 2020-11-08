package com.interview.employee.app;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.employee.enums.OrderByCodeEnum;

/**
 * Entry point class
 *
 */
public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		validateInputParams(args);
		new EmployeeApplication().run(args[0],
				args.length == 2 ? OrderByCodeEnum.fromOrderByCode(args[1]) : OrderByCodeEnum.BY_FIRST_NAME);
	}

	private static void validateInputParams(String[] args) {
		if (args.length == 1) {
			logger.info("Output will be ordered {}", OrderByCodeEnum.BY_FIRST_NAME);
		} else if ((args.length == 2 && !OrderByCodeEnum.isValidOrderByCode(args[1])) || args.length > 2
				|| args.length == 0) {
			logger.info("\nIncorrect parameters."
					+ "\nUsage example: \n\tjava -jar employee-0.0.1-jar-with-dependencies C:\\input-files\\my-employees.txt 1"
					+ "\nwhere: \n\t0.0.1 is the version of the application, "
					+ "\n\tthe first param C:\\input-files\\my-employees.txt is the input file path "
					+ "\n\tand the second param 1 is the number of column to order the output."
					+ "\n\tthis second param can take any of these values:"
					+ "\n\t\t1 -> Order by first name (This is the default value)." + "\n\t\t2 -> Order by last name."
					+ "\n\t\t3 -> Order by start date." + "\nPlease try again!");
			System.exit(-1);
		}
	}

}
