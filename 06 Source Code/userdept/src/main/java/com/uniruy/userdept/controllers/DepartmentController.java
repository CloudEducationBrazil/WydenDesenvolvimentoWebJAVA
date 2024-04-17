package com.uniruy.userdept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniruy.userdept.controllers.services.DepartmentService;
import com.uniruy.userdept.entities.Department;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {
	
	@Autowired
	DepartmentService service;

	@GetMapping
	public ResponseEntity<List<Department>> findAll(){
		List<Department> result = service.findAll();
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value="/{id}")
	public Department findById(@PathVariable Long id){
		Department result = service.findById(id);
		
		return result;
	}
	
	@PostMapping()
	public void insertDepartment(@RequestBody Department department) {
		service.insertDepartment(department);
	}

	@PutMapping()
	public void updateDepartment(@RequestBody Department department) throws Exception {
		try {
			Department dep = service.findById(department.getId());
			
			if (dep != null) {
				dep.setName(department.getName());
	
				service.updateDepartment(department);
			}
			
		} catch (Exception e) {
			System.out.println("Mensagem: " + e.getMessage());
		}
	}

	@PutMapping(value="/{id}")
	public void updateDepartmentId(@RequestBody Department department, @PathVariable Long id) {
		Department dep = service.findById(id);
		
		dep.setName(department.getName());
		
		service.updateDepartmentId(dep, id);
	}
}