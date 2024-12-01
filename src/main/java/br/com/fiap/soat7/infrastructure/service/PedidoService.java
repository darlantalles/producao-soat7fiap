package br.com.fiap.soat7.infrastructure.service;


import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.domain.types.StatusPedido;
import br.com.fiap.soat7.infrastructure.client.PedidoClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService  implements PedidoGateway {

    private final PedidoClient pedidoClient;

    public PedidoService(PedidoClient pedidoClient) {
        this.pedidoClient = pedidoClient;
    }

    @Override
    public Pedido adicionarPedido(Pedido pedido) {
        return pedidoClient.adicionarPedido(pedido).getBody();
    }

    @Override
    public List<Pedido> buscarPedidos() {
        return pedidoClient.buscarPedidos().getBody();
    }

    @Override
    public Pedido atualizarStatus(Long id, StatusPedido statusPedido) {
        return switch (statusPedido) {
            case PRONTO -> pedidoClient.atualizarComoPronto(id).getBody();
            case EM_PREPARACAO -> pedidoClient.atualizarComoEmPreparacao(id).getBody();
            case FINALIZADO -> pedidoClient.atualizarComoFinalizado(id).getBody();
            default -> null;
        };
    }
}
