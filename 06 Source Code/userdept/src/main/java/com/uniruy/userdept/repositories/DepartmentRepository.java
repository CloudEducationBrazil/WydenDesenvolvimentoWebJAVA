package com.uniruy.userdept.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uniruy.userdept.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	@Query(nativeQuery = true, value = "select * from tb_department t where upper(t.name) = upper(:name2)") // Linguagem JPQL
	Department buscarPorName(String name2);
}
