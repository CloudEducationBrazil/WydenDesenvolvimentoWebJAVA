package com.vog.projewebjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vog.projewebjava.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
}
