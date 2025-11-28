package com.vog.projewebjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vog.projewebjava.entities.GameList;

@Repository
public interface GameListRepository extends JpaRepository<GameList, Long> {

}
