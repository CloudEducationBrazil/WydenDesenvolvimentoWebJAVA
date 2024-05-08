package com.uniruy.userdept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uniruy.userdept.controllers.services.DepartmentService;
import com.uniruy.userdept.entities.Department;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController<JSONObject> {
	// https://medium.com/@daryl-goh/spring-boot-requestentity-vs-responseentity-requestbody-vs-responsebody-dc808fb0d86c
	
	@Autowired
	DepartmentService service;

	@GetMapping
	public ResponseEntity<List<Department>> findAll(){
		List<Department> result = service.findAll();
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		//Department result = service.findById(id);
		//service.findById(id);
		
//		return result;
		return service.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Department> insertDepartment(@RequestBody Department department) {
		Department criadoDepartment = service.insertDepartment(department);
		
	    // Retorna o recurso criado com um código de status 201 (criado) 
	    return ResponseEntity 
	            .status(HttpStatus.CREATED) 
	            .body(criadoDepartment); 
		
		//service.insertDepartment(department);
	}

	@PutMapping
	public @ResponseBody ResponseEntity<?> updateDepartment(@RequestBody Department department) throws Exception {
		return service.updateDepartment(department);
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<?> updateDepartmentId(@RequestBody Department department, @PathVariable Long id) {
		//Department dep = service.findById(id);
		//ResponseEntity<?> dep = service.findById(id);
		
		//dep.setName(department.getName());
		
		service.updateDepartmentId(department, id);
		
		return ResponseEntity.ok("Departamento alterado com sucesso. ");
	}
}