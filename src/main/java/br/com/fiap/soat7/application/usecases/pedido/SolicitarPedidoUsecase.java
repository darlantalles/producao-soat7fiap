package br.com.fiap.soat7.application.usecases.pedido;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.domain.Pedido;

public class SolicitarPedidoUsecase {

    private final PedidoGateway pedidoGateway;


    public SolicitarPedidoUsecase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }


    public Pedido solicitarPedido(Pedido pedido) {
        return pedidoGateway.adicionarPedido(pedido);
    }
}
