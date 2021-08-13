package com.rim.emp.dao;

import java.util.List;
import com.rim.emp.model.Employee;
import com.rim.emp.model.Request;

public interface EmployeeDAO {
	public List<Employee> getEmployees();
	public Employee getEmployee(String email);
	public String addEmployee(Employee r);
	public String updateEmployee(int id,Employee p);
	public String deleteEmployee(int id);
	public String addRequest(Request r);

}
