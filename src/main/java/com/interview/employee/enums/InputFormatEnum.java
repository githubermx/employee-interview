package com.interview.employee.enums;

import java.util.stream.Stream;

import com.interview.employee.exceptions.InputFormatNotSupportedException;

public enum InputFormatEnum {
	SPACE_FILLED("1"), COMMA_SEPARATED("2");

	private final String formatCode;

	InputFormatEnum(String formatCode) {
		this.formatCode = formatCode;
	}

	public static InputFormatEnum fromFormatCode(String formatCode) {
		return Stream.of(values()).filter(e -> e.formatCode.equals(formatCode)).findFirst()
				.orElseThrow(() -> new InputFormatNotSupportedException(
						String.format("Input format is not supported: [%s]", formatCode)));
	}

}
