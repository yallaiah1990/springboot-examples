package com.employeemngt.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeemngt.model.Leave;
import com.employeemngt.service.impl.LeaveServiceImpl;

@RunWith(value = SpringJUnit4ClassRunner.class)
public class LeaveControllerTest {

	@InjectMocks
	public LeaveController leavecontroller;

	private Leave leave;

	@Mock
	public LeaveServiceImpl leaveservice;

	@Before
	public void initiaSetup() throws ParseException {
		leave = new Leave();
		leave.setDescription("Personal Work");
		leave.setLeaveEmployeeId("12");
		leave.setLeaveType("Casual");
		leave.setLeavesBalence(10);
	}

	@Test
	public void createLeaveTest() {
		when(leaveservice.addLeaves(anyObject())).thenReturn(leave);
		ResponseEntity<Leave> actualResult = leavecontroller.create(leave, leave.getLeaveEmployeeId());
		Leave leaveResult = actualResult.getBody();
		assertThat("Casual", is(leaveResult.getLeaveType()));
		assertThat("12", is(leaveResult.getLeaveEmployeeId()));
	}

	@Test
	public void getLeaveBalenceByEmployeeIdTest() {
		List<Leave> leaves = new ArrayList<>();
		leaves.add(leave);

		when(leaveservice.getLeaveByEmployeeId(anyObject())).thenReturn(leaves);
		ResponseEntity<List<Leave>> actualResult = leavecontroller
				.getLeaveBalenceByEmployeeId(leave.getLeaveEmployeeId());
		List<Leave> leaveResult = actualResult.getBody();
		assertThat("Casual", is(leaveResult.get(0).getLeaveType()));
		assertThat("12", is(leaveResult.get(0).getLeaveEmployeeId()));
	}
	@Test
	public void editLeaveBalenceByEmployeeIdTest() {
		when(leaveservice.editLeaveDetailsByEmployeeId(anyObject())).thenReturn(leave);
		ResponseEntity<Leave> actualResult = leavecontroller.editLeaveBalenceByEmployeeId(leave, leave.getLeaveEmployeeId());
		Leave leaveResult = actualResult.getBody();
		assertThat("Casual", is(leaveResult.getLeaveType()));
		assertThat("12", is(leaveResult.getLeaveEmployeeId()));
	}

	@Test
	public void applyLeavesByEmployeeIdTest() {
		when(leaveservice.applyLeavesByEmployeeId(anyObject())).thenReturn(leave);
		ResponseEntity<Leave> actualResult = leavecontroller.applyLeavesByEmployeeId(leave);
		Leave leaveResult = actualResult.getBody();
		assertThat("Casual", is(leaveResult.getLeaveType()));
		assertThat("12", is(leaveResult.getLeaveEmployeeId()));
	}
}
