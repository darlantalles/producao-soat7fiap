package br.com.fiap.soat7.application.usecases.pedido;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.domain.entity.Pedido;
import br.com.fiap.soat7.domain.types.StatusPedido;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BuscarTodosPedidosUsecase {

    private final PedidoGateway pedidoGateway;


    public BuscarTodosPedidosUsecase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }


    public List<Pedido> buscarPedidos() {

        List<Pedido> pedidos = pedidoGateway.buscarPedidos();

        List<Pedido> pedidosFiltrados = pedidos.stream()
                .filter(pedido -> !Objects.equals(pedido.getStatus(), StatusPedido.FINALIZADO))
                .collect(Collectors.toList());

        pedidosFiltrados.sort(Comparator.comparingInt(pedido -> pedido.getStatus().getOrder()));
        return pedidosFiltrados;
    }


}
