package com.wyden.comercio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wyden.comercio.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
}
