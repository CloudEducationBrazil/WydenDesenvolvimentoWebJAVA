package com.uniruy.userdept.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uniruy.userdept.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
