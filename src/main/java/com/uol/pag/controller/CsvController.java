package com.uol.pag.controller;

import com.opencsv.exceptions.CsvValidationException;
import com.uol.pag.service.MigracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CsvController {

    @Autowired
    private MigracaoService migracaoService;

    @GetMapping("/povoar")
    public void upload() throws CsvValidationException, IOException {
        migracaoService.uploadCsv();
    }

}
