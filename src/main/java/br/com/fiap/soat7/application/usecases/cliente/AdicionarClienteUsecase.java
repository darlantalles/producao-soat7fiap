package br.com.fiap.soat7.application.usecases.cliente;

import br.com.fiap.soat7.application.gateways.ClienteGateway;
import br.com.fiap.soat7.domain.entity.Cliente;

public class AdicionarClienteUsecase {

    private final ClienteGateway clienteGateway;


    public AdicionarClienteUsecase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }


    public Cliente criarCliente(Cliente cliente) {
        return clienteGateway.createCliente(cliente);
    }
}
