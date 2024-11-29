package br.com.fiap.soat7.application.usecases.pedido;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.domain.types.StatusPedido;

public class MarcarPedidoProntoUsecase {

    private final PedidoGateway pedidoGateway;


    public MarcarPedidoProntoUsecase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }


    public Pedido marcarComoPronto(Long id) {
        Pedido pedido = this.pedidoGateway.buscarPorId(id);
        pedido.setStatus(StatusPedido.PRONTO);
        return pedidoGateway.adicionarPedido(pedido);
    }
}