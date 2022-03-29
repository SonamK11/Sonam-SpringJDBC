package com.zensar.training.service;

import java.sql.Connection;
import java.util.List;

import com.mysql.cj.xdevapi.DbDoc;
import com.zensar.training.bean.Employee;
import com.zensar.training.db.DBConnectionFactory;
import com.zensar.training.db.EmployeeDAO;
import com.zensar.training.db.EmployeeJdbcImpl;

public class EmployeeServiceImpl implements EmployeeServices{

	@Override
	public boolean addEmployee(Employee employee) throws Exception {
	boolean result=false;
	Connection connection =DBConnectionFactory.createConnection();
	connection.setAutoCommit(false);//record are not told to 
	EmployeeDAO dao=new EmployeeJdbcImpl();
	result=dao.addEmployee(connection, employee);//validate emp data can be written here
	if(result==true) 
		connection.commit();
	else 
		connection.rollback();

		return result;
	}

	@Override
	public boolean updateEmployee(Employee employee) throws Exception {
		boolean result=false;
		Connection connection =DBConnectionFactory.createConnection();
		EmployeeDAO dao=new EmployeeJdbcImpl();
		result=dao.updateEmployee(connection, employee);
			return result;
	}

	@Override
	public boolean deleteEmployee(Employee employee) throws Exception {
		boolean result=false;
		Connection connection=DBConnectionFactory.createConnection();
		EmployeeDAO dao= new EmployeeJdbcImpl();
		result=dao.deleteEmployee(connection, employee);
		return result;
	}

	@Override
	public Employee findEmployee(int id) throws Exception {
		Connection connection=DBConnectionFactory.createConnection();
		EmployeeDAO dao=new EmployeeJdbcImpl();
		Employee employee=dao.findEmployee(connection, id);
		
		return employee;
	}

	@Override
	public List<Employee> findAllEmployees() throws Exception {
		Connection connection=DBConnectionFactory.createConnection();
		EmployeeDAO dao= new EmployeeJdbcImpl();
		return dao.findAllEmployee(connection);
	}
	
	
}
