package com.uol.pag.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.uol.pag.model.ContaBancaria;
import com.uol.pag.repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class MigracaoService {

    @Autowired
    private ContaBancariaRepository contaRepository;

    public void uploadCsv() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/static/contas_bancarias.csv"))) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                contaRepository.insert(ContaBancaria.builder()
                        .nome(lineInArray[0])
                        .numeroConta(lineInArray[1])
                        .agencia(lineInArray[2])
                        .chequeEspecialLiberado(lineInArray[3])
                        .saldo(Double.valueOf(lineInArray[4]))
                        .chequeEspecial(Double.valueOf(lineInArray[5]))
                        .taxa(Double.valueOf(lineInArray[6]))
                        .build());
            }
        }
    }


}
