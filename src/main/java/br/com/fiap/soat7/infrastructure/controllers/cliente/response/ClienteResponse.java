package br.com.fiap.soat7.infrastructure.controllers.cliente.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponse {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
}
