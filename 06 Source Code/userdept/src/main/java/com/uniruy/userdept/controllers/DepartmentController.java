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
	public ResponseEntity<List<Department>> getAllDepartment(){
		List<Department> result = service.getAllDepartment();
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> getDepartmentById(@PathVariable Long id){
		return service.getDepartmentById(id);
	}
	
	@GetMapping(value="/name/{name2}")
	public ResponseEntity<?> getDepartmentByName(@PathVariable String name2){
		return service.getDepartmentByName(name2);
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<?> createDepartment(@RequestBody Department department){
			service.createDepartment(department);
			return ResponseEntity.ok().body(department);

			//return ResponseEntity.ok("Departamento criado com sucesso");
			
/*		} catch (BadRequestException e) {
			// TODO: handle exception
			RestErrorMessage badRequest = new RestErrorMessage();
			
			badRequest.setTimestamp(Instant.now());
			badRequest.setStatus(HttpStatus.BAD_REQUEST.value());
			badRequest.setError(HttpStatus.BAD_REQUEST);
			badRequest.setMessage(e.getMessage());
			badRequest.setPath("path");
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequest);
		}
*/		
	    // Retorna o recurso criado com um código de status 201 (criado) 
	    //return ResponseEntity 
		//            .status(HttpStatus.CREATED) 
		//            .body(criadoDepartment); 
	}

	@PutMapping
	public @ResponseBody ResponseEntity<String> updateDepartment(@RequestBody Department department){
		service.updateDepartment(department);
		return ResponseEntity.ok("Departamento alterado com sucesso");
	}

	@PutMapping(value="/{id}")
	public @ResponseBody ResponseEntity<String> updateDepartmentId(@RequestBody Department department, @PathVariable Long id) {
		service.updateDepartmentId(department, id);
		
		return ResponseEntity.ok("Departamento alterado com sucesso. ");
	}
}