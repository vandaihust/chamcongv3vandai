package com.vandai.mobi.dto;

import lombok.Data;
@Data
public class EmployeeDto {
	private String idEmployee;
	private String name;
	private boolean sex;
	private String birthDate;
	private String idCardNumber;
	private String phone;
	private String academicLevel;
	private String email;
	private String maritalStatus;
	private String avatar;

	private Long idUser;
	private Long idDepartment;
	private int idPosition;
}
