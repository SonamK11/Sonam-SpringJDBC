package com.zensar.training.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.DbDoc;
import com.zensar.training.bean.Employee;
import com.zensar.training.bean.Gender;
import com.zensar.training.db.DBConnectionFactory;
import com.zensar.training.db.EmployeeDAO;

public class  EmployeeSpringJDBCImpl implements EmployeeDAO{
	
	@Override
	public boolean addEmployee(JdbcTemplate jdbcTemplate, Employee employee) throws Exception {
		boolean result=true;
		Object[] rowdata= {employee.getName(),
							employee.getHiredDate(),
							employee.getGrade(),
							employee.getBasicSalary(),
							employee.getGender().toString().charAt(0)};
		int r=jdbcTemplate.update(INSERT_QRY, rowdata);
		if(r==0);
		result= false;
		return result;
	}

	@Override
	public boolean updateEmployee(JdbcTemplate jdbcTemplate, Employee employee) throws Exception {
		boolean result=true;
		Object[] rowdata= {employee.getName(),
							employee.getHiredDate(),
							employee.getGrade(),
							employee.getBasicSalary(),
							employee.getGender().toString().charAt(0),
							employee.getId()};
		int r=jdbcTemplate.update(UPDATE_QRY, rowdata);
		if(r==0);
		result= false;
		return result;
	}

	@Override
	public boolean deleteEmployee(JdbcTemplate jdbcTemplate, Employee employee) throws Exception {

		boolean result=true;
		Object[] rowdata= {employee.getId()};
int r=jdbcTemplate.update(DELETE_QRY, rowdata);
if(r==0);
result= false;
return result;
	}

	@Override
	public Employee findEmployee(JdbcTemplate jdbcTemplaten, int rmpId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAllEmployee(JdbcTemplate jdbcTemplaten) throws Exception {
		
		return null;
	}
	
	private class EmployeeRowMapper implements RowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee employee=new Employee();
			employee.setId(rs.getInt(1));
			employee.setName(rs.getString(2));
			
			
			java.sql.Date dojdate=rs.getDate(3);
			LocalDate doj=DateConversion.convertToLocalDate(dojdate);
			
			employee.setHiredDate(doj);
			employee.setGrade(rs.getString(4).charAt(0));
			employee.setBasicSalary(rs.getDouble(5));
			
			
			char gend=rs.getString(6).charAt(0);
			if(gend=='M')
				employee.setGender(Gender.MALE);
			else
				employee.setGender(Gender.FEMALE);
			
			
			return employee;
		}
		
	}

	
}
