package com.uol.pag.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaBancariaFormatadaDto {

    private String id;

    private String nome;

    private String contaFormatada;

    private String saldo;

    private String liberado;

    private String chequeEspecialSomado;


}
