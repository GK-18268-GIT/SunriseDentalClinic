package com.dental.model;

public abstract class Person {
	protected String name;
	protected String contactNumber;
	protected String address;
	protected int age;

	public Person() {
		
	}
	
	public Person(String name, String contactNumber, String address, int age) {
		this.name = name;
		this.contactNumber = contactNumber;
		this.address = address;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public abstract String getRole();
	
}

