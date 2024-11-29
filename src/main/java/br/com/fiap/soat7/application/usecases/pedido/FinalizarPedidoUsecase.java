package br.com.fiap.soat7.application.usecases.pedido;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.domain.entity.Pedido;
import br.com.fiap.soat7.domain.types.StatusPedido;

public class FinalizarPedidoUsecase {

    private final PedidoGateway pedidoGateway;


    public FinalizarPedidoUsecase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }


    public Pedido finalizarPedido(Long id) {
        Pedido pedido = this.pedidoGateway.buscarPorId(id);
        pedido.setStatus(StatusPedido.FINALIZADO);
        return pedidoGateway.adicionarPedido(pedido);
    }
}
