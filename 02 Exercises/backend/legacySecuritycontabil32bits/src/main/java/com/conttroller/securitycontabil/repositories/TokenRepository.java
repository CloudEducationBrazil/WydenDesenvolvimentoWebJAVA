package com.conttroller.securitycontabil.repositories;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.conttroller.securitycontabil.entities.Token;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    // Busca simples sem lock
	Optional<Token> findByCnpjAndSistema(String cnpj, String sistema);
	
	@Modifying
	@Transactional
	@Query(
	    value = "MERGE INTO tb_token (cnpj, sistema, habilitado, financeiro, validade) " +
	            "KEY(cnpj, sistema) " +
	            "VALUES (:cnpj, :sistema, :habilitado, :financeiro, :validade)",
	    nativeQuery = true
	)
	void mergeToken(@Param("cnpj") String cnpj,
	                @Param("sistema") String sistema,
	                @Param("habilitado") String habilitado,
	                @Param("financeiro") String financeiro,
	                @Param("validade") OffsetDateTime validade);
	
    // Busca com lock pessimista
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM Token t WHERE t.cnpj = :cnpj AND t.sistema = :sistema")
    Optional<Token> findByCnpjAndSistemaForUpdate(@Param("cnpj") String cnpj, @Param("sistema") String sistema);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Token t WHERE t.cnpj = :cnpj AND t.sistema NOT IN :sistemas")
    void deleteByCnpjAndSistemaNotIn(@Param("cnpj") String cnpj, @Param("sistemas") List<String> sistemas);
}