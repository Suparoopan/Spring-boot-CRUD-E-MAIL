package com.sgic.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgic.employee.entities.Employee;
import com.sgic.employee.repositories.EmployeeRepository;
import com.sgic.employeeDTO.EmployeeDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public List<Employee> selectAllUser() {
		return employeeRepository.findAll();
	}

	@Override
	public List<EmployeeDto> employeeDetails() {
		return employeeRepository.findAll().stream().map(this::convertToEmployeeDto).collect(Collectors.toList());

	}

	public EmployeeDto convertToEmployeeDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setEmail(employee.getEmail());
		return employeeDto;

	}

	@Override
	public Object updateEmployee(Long employeeId, Employee e_Employee) {
		Optional<Employee> emp = employeeRepository.findById(employeeId);
		Employee employee = emp.get();
		employee.setEmail(e_Employee.getEmail());
		employee.setFirstName(e_Employee.getFirstName());
		employee.setLastName(e_Employee.getLastName());
		employeeRepository.save(employee);
		return employee;
	}

	@Override
	public void deleteDetails(Long employeeId) {

		employeeRepository.deleteById(employeeId);

	}
}
