package com.employeemngt.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeemngt.model.Leave;
import com.employeemngt.repository.LeaveRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class LeaveServiceImplTest {

	@InjectMocks
	LeaveServiceImpl leaveServiceImpl;

	@Mock
	LeaveRepository leaverepository;

	private Leave leave;

	@Before
	public void initialSetup() throws ParseException {
		leave = new Leave();
		leave.setLeaveId("12345");
		leave.setLeaveEmployeeId("123");
		leave.setLeaveType("Casual");
		leave.setLeaveFrom(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/02"));
		leave.setLeaveTo(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/10"));
		leave.setLeaveStatus("Approve");
		leave.setLeavesTotal(12);
		leave.setLeavesUsed(5);
		leave.setLeavesBalence(7);
		leave.setDescription("Going Home Town");
	}

	@Test
	public void addLeavesTest() throws ParseException {
		when(leaverepository.save(any(Leave.class))).thenReturn(leave);

		Leave leaveResult = leaveServiceImpl.addLeaves(leave);

		assertThat("12345", is(leaveResult.getLeaveId()));

		assertThat("123", is(leaveResult.getLeaveEmployeeId()));

		assertThat("Casual", is(leaveResult.getLeaveType()));

		assertThat(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/02"), is(leaveResult.getLeaveFrom()));

		assertThat(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/10"), is(leaveResult.getLeaveTo()));

		assertThat("Approve", is(leaveResult.getLeaveStatus()));

		assertThat(12, is(leaveResult.getLeavesTotal()));

		assertThat(5, is(leaveResult.getLeavesUsed()));

		assertThat(7, is(leaveResult.getLeavesBalence()));

	}

	@Test
	public void getLeaveByEmployeeIdTest() throws ParseException {
		List<Leave> leaves = new ArrayList<>();
		leaves.add(leave);
		when(leaverepository.findByLeaveEmployeeId(anyString())).thenReturn(leaves);

		List<Leave> leaveResult = leaveServiceImpl.getLeaveByEmployeeId(leave.getLeaveEmployeeId());

		assertThat("12345", is(leaveResult.get(0).getLeaveId()));

		assertThat("123", is(leaveResult.get(0).getLeaveEmployeeId()));

		assertThat("Casual", is(leaveResult.get(0).getLeaveType()));

		assertThat(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/02"), is(leaveResult.get(0).getLeaveFrom()));

		assertThat(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/10"), is(leaveResult.get(0).getLeaveTo()));

		assertThat("Approve", is(leaveResult.get(0).getLeaveStatus()));

		assertThat(12, is(leaveResult.get(0).getLeavesTotal()));

		assertThat(5, is(leaveResult.get(0).getLeavesUsed()));

		assertThat(7, is(leaveResult.get(0).getLeavesBalence()));
	}

	@Test
	public void applyLeavesByEmployeeIdTest() throws ParseException {

		when(leaverepository.findByLeaveTypeAndLeaveEmployeeId(anyString(), anyString())).thenReturn(leave);

		when(leaverepository.save(any(Leave.class))).thenReturn(leave);

		Leave leaveResult = leaveServiceImpl.applyLeavesByEmployeeId(leave);

		assertThat("12345", is(leaveResult.getLeaveId()));

		assertThat("123", is(leaveResult.getLeaveEmployeeId()));

		assertThat("Casual", is(leaveResult.getLeaveType()));

		assertThat(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/02"), is(leaveResult.getLeaveFrom()));

		assertThat(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/10"), is(leaveResult.getLeaveTo()));

		assertThat("Approve", is(leaveResult.getLeaveStatus()));

		assertThat(12, is(leaveResult.getLeavesTotal()));

		assertThat(5, is(leaveResult.getLeavesUsed()));

		assertThat(7, is(leaveResult.getLeavesBalence()));

	}

	@Test
	public void editLeaveDetailsByEmployeeIdTest() throws ParseException {
		
		when(leaverepository.findByLeaveTypeAndLeaveEmployeeId(anyString(), anyString())).thenReturn(leave);
		
		when(leaverepository.save(any(Leave.class))).thenReturn(leave);
		
		Leave leaveResult = leaveServiceImpl.editLeaveDetailsByEmployeeId(leave);

		assertThat("12345", is(leaveResult.getLeaveId()));

		assertThat("123", is(leaveResult.getLeaveEmployeeId()));

		assertThat("Casual", is(leaveResult.getLeaveType()));

		assertThat(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/02"), is(leaveResult.getLeaveFrom()));

		assertThat(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/10"), is(leaveResult.getLeaveTo()));

		assertThat("Approve", is(leaveResult.getLeaveStatus()));

		assertThat(12, is(leaveResult.getLeavesTotal()));

		assertThat(5, is(leaveResult.getLeavesUsed()));

		assertThat(7, is(leaveResult.getLeavesBalence()));
	}
}
