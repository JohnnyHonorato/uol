package com.uol.pag.repository;

import com.uol.pag.model.ContaBancaria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaBancariaRepository extends MongoRepository<ContaBancaria, String> {

    @Query("{'nome' : ?0, 'agencia' : ?1, 'chequeEspecialLiberado' : ?2}")
    List<ContaBancaria> findWithQuery(String nome, String agencia, String chequeEspecialLiberado);


    Optional<ContaBancaria> findContaBancariaByAgencia(String agencia);

}
