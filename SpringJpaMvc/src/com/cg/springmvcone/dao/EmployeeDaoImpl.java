package com.cg.springmvcone.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cg.springmvcone.dto.Employee;
@Repository("employeedao")
public class EmployeeDaoImpl implements IEmployeeDao{
    
	@PersistenceContext
	EntityManager entityManager;
	@Override
	public int addEmployeeData(Employee emp) {
		// TODO Auto-generated method stub
		entityManager.persist(emp);
		entityManager.flush();
		return emp.getEmpId();
	}

	@Override
	public List<Employee> showAllEmployee() {
		// TODO Auto-generated method stub
		Query queryOne = entityManager.createQuery("FROM Employee");
		List<Employee> myList= queryOne.getResultList();
		return myList;
	}

	@Override
	public void deleteEmployee(int empId) {
		// TODO Auto-generated method stub
		Query queryTwo = entityManager.createQuery("DELETE FROM Employee "+"WHERE empId=:eid");
		queryTwo.setParameter("eid",empId);
		queryTwo.executeUpdate();
	}

	@Override
	public void updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee searchEmployee(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
