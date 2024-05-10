package com.department.service;

import org.springframework.stereotype.Service;

import com.department.dto.DepartmentDto;
import com.department.exception.DepartmentNotFoundException;
import com.department.exception.departmentNameShouldStartWithE;


public interface DepartmentService {

	DepartmentDto createDepartment(DepartmentDto departmentDto) throws departmentNameShouldStartWithE;

	DepartmentDto getDepartment(int departmentId) throws DepartmentNotFoundException;

}
