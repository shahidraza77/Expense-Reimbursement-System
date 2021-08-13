package com.rim.emp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rim.emp.utils.*;
import com.rim.emp.model.Employee;
import com.rim.emp.model.Request;

public class EmployeeDAOImp implements EmployeeDAO{

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public Employee getEmployee(String email) {
		// TODO Auto-generated method stub
		Employee e1=null;
		Session session=HibernateUtils.getSessionFactory().openSession();
	      Transaction tx=session.beginTransaction();
	      e1 =session.get(Employee.class, email);
	       tx.commit();
	       session.close();
		return e1;
	}

	@Override
	public String updateEmployee(int id, Employee p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteEmployee(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addEmployee(Employee r) {
		
		
		Session session=HibernateUtils.getSessionFactory().openSession();
	      Transaction tx=session.beginTransaction();
	     // Employee e =new Employee();
	     // Request rq=new Request();
	     // r.getReq().add(rq);
	      //e1 =session.get(Employee.class, email);
	       session.save(r);
	       tx.commit();
	       session.close();
		return "data submitted successfull";
	}

	@Override
	public String addRequest(Request r) {
		// TODO Auto-generated method stub
		  Session session=HibernateUtils.getSessionFactory().openSession();
	      Transaction tx=session.beginTransaction();
	      session.save(r);
	       tx.commit();
	       session.close();
		return "data submitted successfull";
	}

}
