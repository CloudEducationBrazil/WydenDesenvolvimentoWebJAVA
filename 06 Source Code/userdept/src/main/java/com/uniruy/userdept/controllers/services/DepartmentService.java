package com.uniruy.userdept.controllers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uniruy.userdept.entities.Department;
import com.uniruy.userdept.exceptions.ResourceNotFoundException;
import com.uniruy.userdept.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	DepartmentRepository repository;

	public List<Department> findAll(){
		List<Department> result = repository.findAll();
		
		return result;
	}
	
	public ResponseEntity<?> findById(Long id){ // Department
		try {
			Department dep = repository.findById(id).orElseThrow();
			
			//return result;
			return new ResponseEntity<>(dep, HttpStatus.OK);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Departamento não encontrado ...");
		}
	}
	
	public Department insertDepartment(Department department) {
		return repository.save(department);
	}

	public ResponseEntity<?> updateDepartment(Department department) {
		try {
			Department dep = repository.findById(department.getId()).orElseThrow();

			//System.out.println("Mensagem: " + dep.getId());
			if (dep != null) {
				dep.setName(department.getName());
				repository.save(dep);

				return new ResponseEntity<>(dep, HttpStatus.OK);
			}
			else {
				return ResponseEntity.badRequest().body("Não foi possível alterar o Departamento ...");
			}
		} 
		catch (Exception e) {
			return ResponseEntity.badRequest().body("Departamento não foi encontrado ...");
		}
	}

	// vídeo tratamento de exceção: https://youtu.be/GmbK-O3v3Gg
	// https://youtu.be/ac7JWdD3CZ0
	public ResponseEntity<?> updateDepartmentId(Department department, Long id) {
		Department dep = repository.findById(id).get();
		
		if (dep == null) {
			throw new ResourceNotFoundException("Department not exist ...");
		}
		
		dep.setName(department.getName());
		
		//repository.save(dep);
		return new ResponseEntity<>(dep, HttpStatus.OK);
	}
}