package com.uol.pag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaBancariaDto {

    private String id;

    private String nome;

    private String numeroConta;

    private String agencia;

    private String chequeEspecialLiberado;

    private Double saldo;

    private Double chequeEspecial;

    private Double taxa;
}
