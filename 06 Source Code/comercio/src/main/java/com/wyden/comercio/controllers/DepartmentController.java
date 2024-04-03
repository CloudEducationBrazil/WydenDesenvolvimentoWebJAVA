package com.wyden.comercio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wyden.comercio.entities.Department;
import com.wyden.comercio.services.DepartmentService;

@RestController
@RequestMapping(value="/departments")
public class DepartmentController {
	
	@Autowired
	DepartmentService service;
	
	@GetMapping(value="{id}")
	public Department findById(@PathVariable Long id) {
		Department dep = service.findById(id);
		return dep;
	}
}
