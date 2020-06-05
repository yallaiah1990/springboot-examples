package com.employeemngt.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employeemngt.model.Leave;

@Repository
public interface LeaveRepository extends MongoRepository<Leave, String> {
	Leave findByLeaveTypeAndLeaveEmployeeId(String leaveType, String leaveEmployeeId);

	List<Leave> findByLeaveEmployeeId(String leaveEmployeeId);
}
