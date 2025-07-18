package com.uniruy.userdept.controllers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uniruy.userdept.entities.Department;
import com.uniruy.userdept.exceptions.EntityNotFoundException;
import com.uniruy.userdept.repositories.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository repository;

	public List<Department> getAllDepartment(){
		List<Department> result = repository.findAll();
		return result;
	}
	
	public ResponseEntity<?> getDepartmentById(Long id){ // Department
		//Department dep = repository.findById(id).orElseThrow(EventNotFoundException::new);
		Department dep = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Departamento não encontrado: "+id));
		return new ResponseEntity<>(dep, HttpStatus.OK);
	}
	
	public ResponseEntity<?> getDepartmentByName(String name2) { // Department
		Department dep = repository.buscarPorName(name2);
		
		if ( dep == null ) {
			return new ResponseEntity<>("Departamento não encontrado: "+name2, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(dep, HttpStatus.OK);
	}
	
	public void createDepartment(Department department) {
		repository.save(department);
	}

	public void updateDepartment(Department department) {
			Department dep = repository.findById(department.getId()).orElseThrow(() -> new EntityNotFoundException("Departamento não encontrado: "+department.getId()));

			if (dep != null) {
				dep.setName(department.getName());
				repository.save(dep);
			}
	}

	// vídeo tratamento de exceção: https://youtu.be/GmbK-O3v3Gg; https://youtu.be/ac7JWdD3CZ0
	public void updateDepartmentId(Department department, Long id) {
		Department dep = repository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		dep.setName(department.getName());
		repository.save(dep);

		//return new ResponseEntity<>(dep, HttpStatus.OK);
	}
}