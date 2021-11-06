package com.sgic.employee.service;

import java.util.List;

import com.sgic.employee.entities.Employee;
import com.sgic.employeeDTO.EmployeeDto;

public interface EmployeeService {
	void saveEmployee(Employee employee);

	List<Employee> selectAllUser();

	Object updateEmployee(Long employeeId, Employee e);

	void deleteDetails(Long employeeId);

	List<EmployeeDto> employeeDetails();

}
