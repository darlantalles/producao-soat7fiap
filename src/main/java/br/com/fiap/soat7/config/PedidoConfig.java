package br.com.fiap.soat7.config;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.application.usecases.pedido.*;
import br.com.fiap.soat7.infrastructure.gateways.PedidoRepositoryGateway;
import br.com.fiap.soat7.infrastructure.persistence.PedidoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfig {

    @Bean
    AdicionarPedidoUsecase criarPedidoUseCase(PedidoGateway pedidoGateway) {
        return new AdicionarPedidoUsecase(pedidoGateway);
    }

    @Bean
    BuscarTodosPedidosUsecase buscarTodosPedidosUseCase(PedidoGateway pedidoGateway) {
        return new BuscarTodosPedidosUsecase(pedidoGateway);
    }


    @Bean
    MarcarPedidoProntoUsecase marcarPedidoProntoUseCase(PedidoGateway pedidoGateway) {
        return new MarcarPedidoProntoUsecase(pedidoGateway);
    }

    @Bean
    PedidoGateway pedidoGateway(PedidoRepository pedidoRepository) {
        return new PedidoRepositoryGateway(pedidoRepository);
    }
}
