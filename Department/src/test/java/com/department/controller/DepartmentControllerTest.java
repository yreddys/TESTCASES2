package com.department.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.department.dto.DepartmentDto;
import com.department.service.DepartmentService;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    public void testCreateDepartment() throws Exception {
        // Mocking the behavior of departmentService.createDepartment() method
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("Engineering");
        departmentDto.setEmpId(1);

        DepartmentDto createDeptResponse = new DepartmentDto();
        createDeptResponse.setDepartmentId(1);
        createDeptResponse.setDepartmentName("Engineering");
        createDeptResponse.setEmpId(1);

        when(departmentService.createDepartment(departmentDto)).thenReturn(createDeptResponse);

        // Sending a POST request to create a department
        mockMvc.perform(post("/department") // Correct endpoint URL
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"departmentName\": \"Engineering\", \"empId\": 1 }"))
                .andExpect(status().isCreated()); // Expecting 201 Created status
    }
}
