package br.com.fiap.soat7.application.usecases.pedido;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.domain.types.StatusPedido;

public class AtualizarStatusPedidoUsecase {

    private final PedidoGateway pedidoGateway;

    public AtualizarStatusPedidoUsecase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public Pedido atualizar(Long id, StatusPedido statusPedido) {
        Pedido pedido = this.pedidoGateway.atualizarStatus(id, statusPedido);
        pedido.setStatus(StatusPedido.PRONTO);
        return pedidoGateway.adicionarPedido(pedido);
    }
}
