package com.uol.pag.service;

import com.uol.pag.model.ContaBancaria;
import com.uol.pag.repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public ContaBancaria create(ContaBancaria contaBancaria) {
        return contaBancariaRepository.insert(contaBancaria);
    }

    public List<ContaBancaria> readAll() {
        return contaBancariaRepository.findAll();
    }

    public ContaBancaria update(ContaBancaria contaBancariaRequest) {
        Optional<ContaBancaria> contaBancaria = contaBancariaRepository.findById(contaBancariaRequest.getId());
        if (contaBancaria.isPresent()) {
            return contaBancariaRepository.save(contaBancaria.get());
        } else {
            throw new RuntimeException("Conta bancaria not found for this id :: " + contaBancariaRequest.getId());
        }
    }

    public void delete(String id) {
        contaBancariaRepository.deleteById(id);
    }

    public List<ContaBancaria> searching(String nome, String agencia, String chequeEspecialLiberado) {
        return contaBancariaRepository.findWithQuery(nome, agencia, chequeEspecialLiberado);
    }

    public ContaBancaria buscaDetalhada(String agencia) {
        Optional<ContaBancaria> contaBancaria = contaBancariaRepository.findContaBancariaByAgencia(agencia);
        if (contaBancaria.isPresent()) {
            return contaBancaria.get();
        } else {
            throw new RuntimeException("Conta bancaria not found ");
        }
    }



}
