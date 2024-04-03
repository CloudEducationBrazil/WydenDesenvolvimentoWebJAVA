package com.uniruy.userdept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniruy.userdept.entities.Department;
import com.uniruy.userdept.repositories.DepartmentRepository;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {
	
	@Autowired
	DepartmentRepository repository;

	@GetMapping
	public List<Department> findAll(){
		List<Department> result = repository.findAll();
		
		return result;
	}
	
	@GetMapping(value="{id}")
	public Department findById(@PathVariable Long id){
		Department result = repository.findById(id).get();
		
		return result;
	}
}