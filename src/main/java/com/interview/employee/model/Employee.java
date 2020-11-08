package com.interview.employee.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {

	private String firstName;
	private String lastName;
	private String startDate;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String zip;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getZip() {
		return zip;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", startDate=" + startDate
				+ ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state
				+ ", country=" + country + ", zip=" + zip + "]";
	}

	public String toHumanReadable() {
		StringBuilder sb = new StringBuilder();
		sb.append("   ");
		sb.append(firstName);
		sb.append(" ");
		sb.append(lastName);
		sb.append(", (");
		String formattedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd"))
				.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
		sb.append(formattedStartDate.substring(0, 1).toUpperCase());
		sb.append(formattedStartDate.substring(1));
		sb.append("),\n");
		sb.append("   ");
		sb.append(address1);
		sb.append(", ");
		sb.append(address2);
		sb.append(",\n");
		sb.append("   ");
		sb.append(city);
		sb.append(", ");
		sb.append(state);
		sb.append(",\n");
		sb.append("   ");
		sb.append(country);
		sb.append(", ");
		sb.append(zip);
		sb.append("\n");
		return sb.toString();
	}

}
