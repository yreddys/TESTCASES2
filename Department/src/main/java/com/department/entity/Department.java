package com.department.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Department {
	@Id
	@GeneratedValue
	private int departmentId;
	private String departmentName;
	private int empId;

}
