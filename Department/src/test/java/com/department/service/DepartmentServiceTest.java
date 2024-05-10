package com.department.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import com.department.exception.departmentNameShouldStartWithE;
import com.department.repository.DepartmentRepository;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

	@Mock
	private DepartmentRepository departmentRepository;

	@InjectMocks
	private DepartmentServiceImpl departmentService;

	@DisplayName("Testing positive scenario")
	@Test
	void createDepartment_PositiveScenario() throws departmentNameShouldStartWithE {
		// Create a mock DepartmentDto
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentName("Engineering");
		departmentDto.setEmpId(1);

		// Create a mock Department entity
		Department department = new Department();
		department.setDepartmentId(0); // Set departmentId to 0 to match the actual invocation
		department.setDepartmentName("Engineering");
		department.setEmpId(1);

		// Mock the behavior of departmentRepository.save() method
		when(departmentRepository.save(department)).thenReturn(department); // Update the department to match the actual
																			// invocation

		// Call the createDepartment method
		DepartmentDto createdDepartment = departmentService.createDepartment(departmentDto);

		// Verify that the DepartmentDto returned by the service matches the expected
		// DepartmentDto
		assertEquals(departmentDto.getDepartmentName(), createdDepartment.getDepartmentName());
		assertEquals(departmentDto.getEmpId(), createdDepartment.getEmpId());

		// Verify that the departmentRepository.save() method was called once with the
		// correct parameter
		verify(departmentRepository).save(department);
	}

	@DisplayName("Testing department name is null")
	@Test
	void createDepartment_NullDepartmentName() {
		// Create a mock DepartmentDto with null department name
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setEmpId(1);

		// Verify that IllegalArgumentException is thrown when department name is null
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> departmentService.createDepartment(departmentDto));
		assertEquals("Department name cannot be null.", exception.getMessage());
	}

	@DisplayName("Testing department name does not start with 'e'")
	@Test
	void createDepartment_DepartmentNameNotStartsWithE() {
		// Create a mock DepartmentDto with department name not starting with 'e'
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentName("Marketing");
		departmentDto.setEmpId(1);

		// Verify that departmentNameShouldStartWithE exception is thrown when
		// department name does not start with 'e'
		departmentNameShouldStartWithE exception = assertThrows(departmentNameShouldStartWithE.class,
				() -> departmentService.createDepartment(departmentDto));
		assertEquals("Department name must start with 'e'.", exception.getMessage());
	}

}
