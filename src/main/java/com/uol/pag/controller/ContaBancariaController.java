package com.uol.pag.controller;

import com.uol.pag.dto.ContaBancariaDto;
import com.uol.pag.dto.ContaBancariaFormatadaDto;
import com.uol.pag.model.ContaBancaria;
import com.uol.pag.service.ContaBancariaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conta-bancaria")
public class ContaBancariaController {

    @Autowired
    ContaBancariaService contaBancariaService;

    @PostMapping
    public ContaBancariaDto createNote(@RequestBody ContaBancariaDto dto) {
        ContaBancaria contaBancaria = contaBancariaService.create(this.convertToModel(dto));
        return convertToDto(contaBancaria);
    }

    @GetMapping
    public List<ContaBancariaDto> readAll() {
        List<ContaBancaria> contas = contaBancariaService.readAll();
        return contas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public List<ContaBancariaDto> searching(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String agencia,
            @RequestParam(required = false) String chequeEspecialLiberado
    ) {
        List<ContaBancaria> contas = this.contaBancariaService.searching(nome, agencia, chequeEspecialLiberado);
        return contas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("busca-detalhada")
    public ContaBancariaFormatadaDto buscaDetalhada(@PathVariable(value = "numeroConta") String numeroConta) {
        ContaBancaria contaBancaria = contaBancariaService.buscaDetalhada(numeroConta);
        return this.convertToFormatadaDto(contaBancaria);
    }

    @PutMapping("/{id}")
    public ContaBancaria update(@RequestBody ContaBancariaDto contaBancariaDto) {
        return contaBancariaService.update(this.convertToModel(contaBancariaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") String id) {
        contaBancariaService.delete(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    private ContaBancariaFormatadaDto convertToFormatadaDto(ContaBancaria contaBancaria) {
        return ContaBancariaFormatadaDto.builder()
                .nome(contaBancaria.getNome())
                .contaFormatada(contaBancaria.getNumeroConta() + "/" + contaBancaria.getAgencia())
                .saldo("R$" + contaBancaria.getSaldo().toString())
                .liberado(contaBancaria.getChequeEspecialLiberado().equals(0) ? "Liberado" : "Não liberado")
                .chequeEspecialSomado("R$" + contaBancaria.getChequeEspecial() + ( contaBancaria.getChequeEspecial() * (contaBancaria.getTaxa() / 100)))
            .build();
    }

    private ContaBancariaDto convertToDto(ContaBancaria contaBancaria) {
        ModelMapper modelMapper = new ModelMapper();
        ContaBancariaDto contaBancariaDto = modelMapper.map(contaBancaria, ContaBancariaDto.class);
        return contaBancariaDto;
    }

    private ContaBancaria convertToModel(ContaBancariaDto contaBancaria) {
        ModelMapper modelMapper = new ModelMapper();
        ContaBancaria contaBancariaModel = modelMapper.map(contaBancaria, ContaBancaria.class);
        return contaBancariaModel;
    }


}
