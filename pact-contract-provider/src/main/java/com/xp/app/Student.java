package com.xp.app;

public class Student {
	private String name;
	private String locationName;
	private int rank;

	public Student(String name, String locationName, int quantity) {
		super();
		this.name = name;
		this.locationName = locationName;
		this.rank = quantity;
	}

	public Student() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", locationName="
				+ locationName + ", rank=" + rank + "]";
	}
	
	

}
