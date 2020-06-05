package com.employeemngt.repository;

@FunctionalInterface
public interface EmployeeIdSequenceRepository {
	long getNextSequenceId(String key);
}