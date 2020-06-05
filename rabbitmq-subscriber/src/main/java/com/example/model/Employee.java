package com.example.model;

public class Employee {

	private String empDept;
	private String empName;
	

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", empDept=" + empDept + "]";
	}

}
