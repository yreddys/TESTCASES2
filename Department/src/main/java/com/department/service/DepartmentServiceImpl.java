package com.department.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import com.department.exception.DepartmentNotFoundException;
import com.department.exception.departmentNameShouldStartWithE;
import com.department.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public DepartmentDto createDepartment(DepartmentDto departmentDto) throws departmentNameShouldStartWithE {
		String departmentName = departmentDto.getDepartmentName();
		if (departmentName == null) {
			throw new IllegalArgumentException("Department name cannot be null.");
		}
		if (!departmentName.toLowerCase().startsWith("e")) {
			throw new departmentNameShouldStartWithE("Department name must start with 'e'.");
		}
		Department dept = new Department();
		dept.setDepartmentName(departmentDto.getDepartmentName());
		dept.setEmpId(departmentDto.getEmpId());
		dept = departmentRepository.save(dept);
		DepartmentDto deptDto = new DepartmentDto();
		deptDto.setDepartmentId(dept.getDepartmentId());
		deptDto.setDepartmentName(dept.getDepartmentName());
		deptDto.setEmpId(dept.getEmpId());
		return deptDto;
	}

	@Override
	public DepartmentDto getDepartment(int departmentId) throws DepartmentNotFoundException {
		Optional<Department> existingdept = departmentRepository.findById(departmentId);
		if (existingdept.isPresent()) {
			Department dept = existingdept.get();
			DepartmentDto deptDto = new DepartmentDto();
			deptDto.setDepartmentId(dept.getDepartmentId());
			deptDto.setDepartmentName(dept.getDepartmentName());
			deptDto.setEmpId(dept.getEmpId());
			return deptDto;
		} else {
			throw new DepartmentNotFoundException("Department with ID " + departmentId + " not found");
		}

	}

}
