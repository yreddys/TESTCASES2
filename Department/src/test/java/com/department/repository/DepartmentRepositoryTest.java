package com.department.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.department.entity.Department;

@DataJpaTest
public class DepartmentRepositoryTest {
	@Autowired
	private DepartmentRepository departmentRepository;

	@DisplayName("testing create department")
	@Test
	void createDepartment() {
		Department department = new Department();
		department.setDepartmentId(1);
		department.setDepartmentName("Hello");
		department.setEmpId(1);

		// Save the department to the database
		Department savedDepartment = departmentRepository.save(department);

		// Verify that the department is saved and not null
		assertNotNull(savedDepartment);

		// Verify that the saved department has the correct values
		assertEquals(department.getDepartmentId(), savedDepartment.getDepartmentId());
		assertEquals(department.getDepartmentName(), savedDepartment.getDepartmentName());
		assertEquals(department.getEmpId(), savedDepartment.getEmpId());

	}

	

}
