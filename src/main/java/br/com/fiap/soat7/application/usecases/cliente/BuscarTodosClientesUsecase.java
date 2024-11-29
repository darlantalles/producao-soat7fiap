package br.com.fiap.soat7.application.usecases.cliente;

import br.com.fiap.soat7.application.gateways.ClienteGateway;
import br.com.fiap.soat7.domain.entity.Cliente;

import java.util.List;

public class BuscarTodosClientesUsecase {

    private final ClienteGateway clienteGateway;

    public BuscarTodosClientesUsecase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public List<Cliente> buscarTodosClientes() {
        return clienteGateway.findAll();
    }
}
