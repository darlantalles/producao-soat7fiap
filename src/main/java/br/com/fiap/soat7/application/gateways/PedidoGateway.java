package br.com.fiap.soat7.application.gateways;


import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.domain.types.StatusPedido;

import java.util.List;

public interface PedidoGateway {

    Pedido adicionarPedido(Pedido pedido);

    List<Pedido> buscarPedidos();

    Pedido atualizarStatus(Long id, StatusPedido statusPedido);
}
