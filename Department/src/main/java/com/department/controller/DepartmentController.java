package com.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.department.dto.DepartmentDto;
import com.department.exception.DepartmentNotFoundException;
import com.department.exception.departmentNameShouldStartWithE;
import com.department.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	@PostMapping
	ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto)
			throws departmentNameShouldStartWithE {
		DepartmentDto createDeptResponse = departmentService.createDepartment(departmentDto);
		return new ResponseEntity<>(createDeptResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{departmentId}")
	ResponseEntity<DepartmentDto> getDepartment(@PathVariable int departmentId) throws DepartmentNotFoundException {
		DepartmentDto getDepartment = departmentService.getDepartment(departmentId);
		return new ResponseEntity<>(getDepartment, HttpStatus.OK);
	}
}
