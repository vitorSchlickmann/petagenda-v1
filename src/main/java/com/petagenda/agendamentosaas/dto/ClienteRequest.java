package com.petagenda.agendamentosaas.dto;

import lombok.Data;

@Data
public class ClienteRequest {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;
    private String nomePet;
    private String raca;
    private String especie;
}
