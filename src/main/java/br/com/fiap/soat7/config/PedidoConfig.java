package br.com.fiap.soat7.config;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.application.usecases.pedido.*;
import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.infrastructure.client.PedidoClient;
import br.com.fiap.soat7.infrastructure.service.PedidoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.util.List;

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

    @Bean
    public PedidoClient pedidoClient() {
        return new PedidoClient() {
            @Override
            public ResponseEntity<Pedido> adicionarPedido(Pedido pedido) {
                return null;
            }

            @Override
            public ResponseEntity<Pedido> atualizarComoPronto(Long id) {
                return null;
            }

            @Override
            public ResponseEntity<Pedido> atualizarComoEmPreparacao(Long id) {
                return null;
            }

            @Override
            public ResponseEntity<Pedido> atualizarComoFinalizado(Long id) {
                return null;
            }

            @Override
            public ResponseEntity<List<Pedido>> buscarPedidos() {
                return null;
            }

        };
    }
}
