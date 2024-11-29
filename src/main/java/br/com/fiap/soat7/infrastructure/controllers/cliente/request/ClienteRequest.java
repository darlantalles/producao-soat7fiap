package br.com.fiap.soat7.infrastructure.controllers.cliente.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteRequest {
    private String nome;
    private String cpf;
    private String email;
}
