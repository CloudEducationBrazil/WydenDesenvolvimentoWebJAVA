package com.wyden.comercio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyden.comercio.entities.Department;
import com.wyden.comercio.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository repository;
	
	public Department findById(Long id) {
		Department dep = repository.findById(id).get();
		return dep;
	}
}