package br.com.fiap.soat7.config;

import br.com.fiap.soat7.application.gateways.ClienteGateway;
import br.com.fiap.soat7.application.usecases.cliente.AdicionarClienteUsecase;
import br.com.fiap.soat7.application.usecases.cliente.BuscarClientePorCpfUsecase;
import br.com.fiap.soat7.application.usecases.cliente.BuscarTodosClientesUsecase;
import br.com.fiap.soat7.infrastructure.gateways.ClienteRepositoryGateway;
import br.com.fiap.soat7.infrastructure.persistence.IClienteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteConfig {

    @Bean
    AdicionarClienteUsecase criarClienteUseCase(ClienteGateway clienteGateway) {
        return new AdicionarClienteUsecase(clienteGateway);
    }

    @Bean
    BuscarClientePorCpfUsecase buscarClienteUseCase(ClienteGateway clienteGateway) {
        return new BuscarClientePorCpfUsecase(clienteGateway);
    }

    @Bean
    BuscarTodosClientesUsecase buscarTodosClientesUseCase(ClienteGateway clienteGateway) {
        return new BuscarTodosClientesUsecase(clienteGateway);
    }

    @Bean
    ClienteGateway clienteGateway(IClienteRepository clienteRepository) {
        return new ClienteRepositoryGateway(clienteRepository);
    }

}
