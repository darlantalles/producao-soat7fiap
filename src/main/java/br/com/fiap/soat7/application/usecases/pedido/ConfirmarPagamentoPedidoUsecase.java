package br.com.fiap.soat7.application.usecases.pedido;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.domain.entity.Pedido;

public class ConfirmarPagamentoPedidoUsecase {

    private final PedidoGateway pedidoGateway;

    public ConfirmarPagamentoPedidoUsecase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public void confirmarPagamento(Pedido pedidoRequest) {
        Pedido pedido = this.pedidoGateway.buscarPorId(pedidoRequest.getId());
        pedido.setStatusPagamento(pedidoRequest.getStatusPagamento());
        pedidoGateway.adicionarPedido(pedido);
    }
}
