package com.uol.pag.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("conta")
public class ContaBancaria {

    @Id
    private String id;

    private String nome;

    private String numeroConta;

    private String agencia;

    private String chequeEspecialLiberado;

    private Double saldo;

    private Double chequeEspecial;

    private Double taxa;

}
