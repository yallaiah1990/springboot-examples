package com.employeemngt.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemngt.model.Leave;
import com.employeemngt.repository.LeaveRepository;
import com.employeemngt.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

	private final Logger logger = LoggerFactory.getLogger(LeaveServiceImpl.class);

	@Autowired
	LeaveRepository leaverepository;

	@Override
	public Leave addLeaves(Leave leaves) {
		return leaverepository.save(leaves);
	}

	@Override
	public List<Leave> getLeaveByEmployeeId(String employeeId) {
		return leaverepository.findByLeaveEmployeeId(employeeId);
	}

	@Override
	public Leave applyLeavesByEmployeeId(Leave leaves) {
		logger.info("Applying Leaves..");
		Leave leave = leaverepository.findByLeaveTypeAndLeaveEmployeeId(leaves.getLeaveType(),
				leaves.getLeaveEmployeeId());
		leaves.setLeaveId(leave.getLeaveId());
		return leaverepository.save(leaves);
	}

	@Override
	public Leave editLeaveDetailsByEmployeeId(Leave leaves) {

		Leave leave = leaverepository.findByLeaveTypeAndLeaveEmployeeId(leaves.getLeaveType(),
				leaves.getLeaveEmployeeId());

		leaves.setLeaveId(leave.getLeaveId());

		return leaverepository.save(leaves);
	}

}
