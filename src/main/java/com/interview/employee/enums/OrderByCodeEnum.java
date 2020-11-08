package com.interview.employee.enums;

import java.util.Comparator;
import java.util.stream.Stream;

import com.interview.employee.exceptions.InputFormatNotSupportedException;
import com.interview.employee.model.Employee;

public enum OrderByCodeEnum {
	BY_FIRST_NAME("1", (a, b) -> a.getFirstName().compareTo(b.getFirstName())),
	BY_LAST_NAME("2", (a, b) -> a.getLastName().compareTo(b.getLastName())),
	BY_START_DATE("3", (a, b) -> a.getStartDate().compareTo(b.getStartDate()));

	private final String orderByCode;
	private final Comparator<Employee> comparator;

	OrderByCodeEnum(String orderByCode, Comparator<Employee> comparator) {
		this.orderByCode = orderByCode;
		this.comparator = comparator;
	}

	public Comparator<Employee> getComparator() {
		return comparator;
	}

	public static OrderByCodeEnum fromOrderByCode(String orderByCode) {
		return Stream.of(values()).filter(e -> e.orderByCode.equals(orderByCode)).findFirst()
				.orElseThrow(() -> new InputFormatNotSupportedException(
						String.format("OrderBy code is not supported: [%s]", orderByCode)));
	}

	public static boolean isValidOrderByCode(String orderByCode) {
		return Stream.of(values()).anyMatch(e -> e.orderByCode.equals(orderByCode));
	}

}
