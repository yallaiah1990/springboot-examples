package com.employeemngt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemngt.model.Leave;
import com.employeemngt.service.impl.LeaveServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/leave")
@Api("Leave all Operation")
public class LeaveController {

	private final Logger logger = LoggerFactory.getLogger(LeaveController.class);

	@Autowired
	LeaveServiceImpl leaveService;

	@PostMapping("/addLeaves")
	@ApiOperation(value = "adding leaves to employee", notes = "adding leaves to employee")
	public ResponseEntity<Leave> create(@RequestBody Leave leave, String employee_id) {
		logger.info("adding leaves to eployee ");
		return new ResponseEntity<>((leaveService.addLeaves(leave)), HttpStatus.CREATED);
	}

	@GetMapping("/getLeaveBalenceByEmployeeId/{employeeId}")
	public ResponseEntity<List<Leave>> getLeaveBalenceByEmployeeId(@PathVariable("employeeId") String employeeId) {
		return new ResponseEntity<>(leaveService.getLeaveByEmployeeId(employeeId), HttpStatus.OK);
	}

	@PutMapping("/editLeaveBalenceByEmployeeId/{employeeId}")
	public ResponseEntity<Leave> editLeaveBalenceByEmployeeId(@RequestBody Leave leave,
			@PathVariable(value = "Employee_id", required = false) String employeeId) {
		logger.info("update leave for employee ID : %S",employeeId);
		return new ResponseEntity<>(leaveService.editLeaveDetailsByEmployeeId(leave), HttpStatus.CREATED);
	}

	@PostMapping("/applyLeavesByEmployeeId")
	@ApiOperation(value = "employee applying leaves", notes = "employee applying leaves", response = LeaveController.class)
	public ResponseEntity<Leave> applyLeavesByEmployeeId(@RequestBody Leave leave) {
		logger.info("employee applying leaves ");

		return new ResponseEntity<>(leaveService.applyLeavesByEmployeeId(leave), HttpStatus.CREATED);
	}

}
