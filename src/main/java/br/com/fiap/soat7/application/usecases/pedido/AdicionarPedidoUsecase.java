package br.com.fiap.soat7.application.usecases.pedido;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.domain.entity.Pedido;
import br.com.fiap.soat7.domain.types.StatusPagamento;
import br.com.fiap.soat7.domain.types.StatusPedido;

import java.util.Date;

public class AdicionarPedidoUsecase {

    private final PedidoGateway pedidoGateway;


    public AdicionarPedidoUsecase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }


    public Pedido criarPedido(Pedido pedido) {
        pedido.setDataCadastro(new Date());
         pedido.setStatus(StatusPedido.RECEBIDO);
        pedido.setStatusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO);
        return pedidoGateway.adicionarPedido(pedido);
    }
}
