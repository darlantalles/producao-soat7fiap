package br.com.fiap.soat7.config;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.application.usecases.pedido.AtualizarStatusPedidoUsecase;
import br.com.fiap.soat7.application.usecases.pedido.ConsultarFilaPedidoUsecase;
import br.com.fiap.soat7.application.usecases.pedido.SolicitarPedidoUsecase;
import br.com.fiap.soat7.infrastructure.client.PedidoClient;
import br.com.fiap.soat7.infrastructure.service.PedidoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfig {

    @Bean
    SolicitarPedidoUsecase criarPedidoUseCase(PedidoGateway pedidoGateway) {
        return new SolicitarPedidoUsecase(pedidoGateway);
    }

    @Bean
    ConsultarFilaPedidoUsecase buscarTodosPedidosUseCase(PedidoGateway pedidoGateway) {
        return new ConsultarFilaPedidoUsecase(pedidoGateway);
    }


    @Bean
    AtualizarStatusPedidoUsecase marcarPedidoProntoUseCase(PedidoGateway pedidoGateway) {
        return new AtualizarStatusPedidoUsecase(pedidoGateway);
    }

    @Bean
    PedidoGateway pedidoGateway(PedidoClient pedidoClient) {
        return new PedidoService(pedidoClient);
    }

}
