package com.employeemngt.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "Leave")
public class Leave {

	@Id
	private String leaveId;

	private String leaveType;

	private String description;

	private String leaveStatus;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date leaveTo;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date leaveFrom;

	/*
	 * @DBRef
	 * 
	 * @Field(value = "employee") private Employee employee;
	 */

	private int leavesTotal;

	private int leavesUsed;

	private int leavesBalence;

	private String leaveEmployeeId;

	public String getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public Date getLeaveTo() {
		return leaveTo;
	}

	public void setLeaveTo(Date leaveTo) {
		this.leaveTo = leaveTo;
	}

	public Date getLeaveFrom() {
		return leaveFrom;
	}

	public void setLeaveFrom(Date leaveFrom) {
		this.leaveFrom = leaveFrom;
	}

	public int getLeavesTotal() {
		return leavesTotal;
	}

	public void setLeavesTotal(int leavesTotal) {
		this.leavesTotal = leavesTotal;
	}

	public int getLeavesUsed() {
		return leavesUsed;
	}

	public void setLeavesUsed(int leavesUsed) {
		this.leavesUsed = leavesUsed;
	}

	public int getLeavesBalence() {
		return leavesBalence;
	}

	public void setLeavesBalence(int leavesBalence) {
		this.leavesBalence = leavesBalence;
	}

	public String getLeaveEmployeeId() {
		return leaveEmployeeId;
	}

	public void setLeaveEmployeeId(String leaveEmployeeId) {
		this.leaveEmployeeId = leaveEmployeeId;
	}
}
