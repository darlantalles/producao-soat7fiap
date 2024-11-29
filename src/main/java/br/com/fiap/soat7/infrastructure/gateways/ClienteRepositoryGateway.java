package br.com.fiap.soat7.infrastructure.gateways;

import br.com.fiap.soat7.application.gateways.ClienteGateway;
import br.com.fiap.soat7.domain.entity.Cliente;
import br.com.fiap.soat7.infrastructure.persistence.IClienteRepository;
import br.com.fiap.soat7.infrastructure.persistence.entity.ClienteEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ClienteRepositoryGateway implements ClienteGateway {

    private final IClienteRepository clienteRepository;

    public ClienteRepositoryGateway(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente createCliente(Cliente cliente)  {
        ClienteEntity clienteEntity;

        if(Objects.isNull(cliente.getId())) {
            clienteEntity = new ClienteEntity(cliente);
        } else {
            clienteEntity = this.clienteRepository.findByCpf(cliente.getCpf())
                    .orElseThrow(() ->new NoSuchElementException(String.format("Cliente [%s] não encontrado.",
                            cliente.getCpf())));
            clienteEntity.atualizar(cliente);
        }

        ClienteEntity clienteSalvo = this.clienteRepository.save(clienteEntity);
        cliente.setId(clienteSalvo.getId());
        return cliente;
    }

    @Override
    public Cliente findByCpf(String cpf) {
        ClienteEntity clienteEntity = this.clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new NoSuchElementException(String.format("O Cliente [%s] não foi encontrado.",
                        cpf)));
        return clienteEntity.toCliente();
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll().stream().map(Cliente::new).toList();
    }

}

