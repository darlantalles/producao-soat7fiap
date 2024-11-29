package br.com.fiap.soat7.application.usecases.pedido;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.domain.entity.Pedido;
import br.com.fiap.soat7.domain.types.StatusPedido;

public class PrepararPedidoUsecase {

    private final PedidoGateway pedidoGateway;


    public PrepararPedidoUsecase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public Pedido prepararPedido(Long id) {
        Pedido pedido = this.pedidoGateway.buscarPorId(id);
        pedido.setStatus(StatusPedido.EM_PREPARACAO);
        return pedidoGateway.adicionarPedido(pedido);
    }
}
