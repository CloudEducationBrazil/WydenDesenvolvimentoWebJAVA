package com.uniruy.userdept.controllers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniruy.userdept.entities.Department;
import com.uniruy.userdept.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	DepartmentRepository repository;

	public List<Department> findAll(){
		List<Department> result = repository.findAll();
		
		return result;
	}
	
	public Department findById(Long id){
		Department result = repository.findById(id).get();
		
		return result;
	}
	
	public void insertDepartment(Department department) {
		repository.save(department);
	}

	public void updateDepartment(Department department) {
		repository.save(department);
	}

	public void updateDepartmentId(Department department, Long id) {
		Department dep = repository.findById(id).get();
		
		dep.setName(department.getName());
		
		repository.save(dep);
	}
}