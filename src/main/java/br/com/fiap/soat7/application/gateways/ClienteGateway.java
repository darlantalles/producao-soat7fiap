package br.com.fiap.soat7.application.gateways;

import br.com.fiap.soat7.domain.entity.Cliente;

import java.util.List;

public interface ClienteGateway {

    Cliente createCliente(Cliente cliente);

    Cliente findByCpf(String cpf);

    List<Cliente> findAll();
}
