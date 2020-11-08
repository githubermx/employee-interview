package com.interview.employee.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.interview.employee.enums.OrderByCodeEnum;
import com.interview.employee.exceptions.InputFormatNotSupportedException;
import com.interview.employee.model.Summary;

public class EmployeeApplicationTest {

	@Test
	public void whenFileDoesNotExists_doNotThrowsExceptionOnlyConsoleInformation() {
		new EmployeeApplication().run("src/test/resources/layoutOne/nonexistent.in", OrderByCodeEnum.BY_FIRST_NAME);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenEmptyFile_thenThrowsIllegalArgumentException() {
		new EmployeeApplication().run("src/test/resources/layoutOne/empty.in", OrderByCodeEnum.BY_FIRST_NAME);
	}
	
	@Test(expected = InputFormatNotSupportedException.class)
	public void whenInvalidFormatCode_thenThrowsInputFormatNotSupportedException() {
		new EmployeeApplication().run("src/test/resources/layoutOne/withoutHeader.in", OrderByCodeEnum.BY_FIRST_NAME);
	}
	
	@Test
	public void testSpacedFilledFormat_whenAllLinesAreFine_thenAllEmployeeAreWritten() {
		Summary s = new EmployeeApplication().run("src/test/resources/layoutOne/employee1.in", OrderByCodeEnum.BY_FIRST_NAME);
		assertEquals(2, s.getEmployees());
	}
	
	/**
	 * File has three lines, but one of them has an invalid date 2OO312081, this record must be discarded, the two fine lines must be written.
	 */
	@Test
	public void testSpacedFilledFormat_whenSomeLinesAreFine_thenOnlyFineLinesAreWritten() {
		Summary s = new EmployeeApplication().run("src/test/resources/layoutOne/employee2.in", OrderByCodeEnum.BY_FIRST_NAME);
		assertEquals(2, s.getEmployees());
	}
	
	/**
	 * File has three lines, but one of them has an invalid record size, 81 instead of 80, this record must be discarded, the two fine lines must be written.
	 */
	@Test
	public void testSpacedFilledFormat_whenRecordSizeIsWrong_thenOnlyFineLinesAreWritten() {
		Summary s = new EmployeeApplication().run("src/test/resources/layoutOne/employee3.in", OrderByCodeEnum.BY_FIRST_NAME);
		assertEquals(2, s.getEmployees());
	}
	
	@Test
	public void testCommaSeparatedFormat_whenAllLinesAreFine_thenAllEmployeeAreWritten() {
		Summary s = new EmployeeApplication().run("src/test/resources/layoutTwo/employee1.in", OrderByCodeEnum.BY_FIRST_NAME);
		assertEquals(2, s.getEmployees());
	}
	
	/**
	 * File has three lines, but one of them has an invalid name W4ll instead of Will, this record must be discarded, the two fine lines must be written.
	 */
	@Test
	public void testCommaSeparatedFormat_whenSomeLinesAreFine_thenOnlyFineLinesAreWritten() {
		Summary s = new EmployeeApplication().run("src/test/resources/layoutTwo/employee2.in", OrderByCodeEnum.BY_FIRST_NAME);
		assertEquals(2, s.getEmployees());
	}
	
}
