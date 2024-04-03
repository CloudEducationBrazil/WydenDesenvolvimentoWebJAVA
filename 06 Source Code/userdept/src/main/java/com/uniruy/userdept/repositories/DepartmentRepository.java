package com.uniruy.userdept.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uniruy.userdept.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
