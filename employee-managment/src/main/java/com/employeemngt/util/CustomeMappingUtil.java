package com.employeemngt.util;

import com.employeemngt.constant.EmployeeManagementConstants;
import com.opencsv.bean.ColumnPositionMappingStrategy;

public class CustomeMappingUtil<T> extends ColumnPositionMappingStrategy<T> {

	protected static final String[] headres = { EmployeeManagementConstants.EMPLOYEE_FIRSTNAME,
			EmployeeManagementConstants.EMPLOYEE_LASTNAME, EmployeeManagementConstants.EMPLOYEE_GMAIL,
			EmployeeManagementConstants.EMPLOYEE_GENDER, EmployeeManagementConstants.EMPLOYEE_DATEOFJOIN,
			EmployeeManagementConstants.EMPLOYEE_DATEOFBIRTH, EmployeeManagementConstants.EMPLOYEE_PHONENUMBER,
			EmployeeManagementConstants.EMPLOYEE_ADDRESS_PRESENT,
			EmployeeManagementConstants.EMPLOYEE_ADDRESS_RESIDENCE,
			EmployeeManagementConstants.EMPLOYEE_DEPARTMENT_NAME,
			EmployeeManagementConstants.EMPLOYEE_DEPARTMENT_DESCRIPTION };

	@Override
	public String[] generateHeader() {
		return headres;
	}
}
