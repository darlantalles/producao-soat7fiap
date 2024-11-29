package br.com.fiap.soat7.application.usecases.cliente;

import br.com.fiap.soat7.application.gateways.ClienteGateway;
import br.com.fiap.soat7.domain.entity.Cliente;

public class BuscarClientePorCpfUsecase {

    private final ClienteGateway clienteGateway;

    public BuscarClientePorCpfUsecase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public Cliente buscarClientePorCpf(String cpf ) {
        return clienteGateway.findByCpf(cpf);
    }
}
