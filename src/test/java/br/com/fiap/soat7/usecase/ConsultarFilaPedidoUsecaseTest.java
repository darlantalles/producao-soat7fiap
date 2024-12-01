package br.com.fiap.soat7.usecase;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.application.usecases.pedido.ConsultarFilaPedidoUsecase;
import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.domain.types.StatusPedido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ConsultarFilaPedidoUsecaseTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private ConsultarFilaPedidoUsecase consultarFilaPedidoUsecase;

    @Test
    void consultarFilaPedidos_deveRetornarListaDePedidosOrdenadaSemPedidosFinalizados() {

        Pedido pedido1 = new Pedido();
        pedido1.setStatus(StatusPedido.RECEBIDO);
        pedido1.setId(1L);

        Pedido pedido2 = new Pedido();
        pedido2.setStatus(StatusPedido.PRONTO);
        pedido2.setId(2L);

        Pedido pedido3 = new Pedido();
        pedido3.setStatus(StatusPedido.EM_PREPARACAO);
        pedido3.setId(3L);

        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2, pedido3);
        when(pedidoGateway.buscarPedidos()).thenReturn(pedidos);

        List<Pedido> pedidosFiltrados = consultarFilaPedidoUsecase.consultarFilaPedido();

        assertEquals(3, pedidosFiltrados.size());
        assertEquals(StatusPedido.PRONTO, pedidosFiltrados.get(0).getStatus());
        assertEquals(StatusPedido.EM_PREPARACAO, pedidosFiltrados.get(1).getStatus());
        assertEquals(2L, pedidosFiltrados.get(0).getId());
        assertEquals(3L, pedidosFiltrados.get(1).getId());

    }


    @Test
    void buscarPedidos_deveRetornarListaVaziaSeNaoHouverPedidos() {
        when(pedidoGateway.buscarPedidos()).thenReturn(new ArrayList<>());

        List<Pedido> pedidosFiltrados = consultarFilaPedidoUsecase.consultarFilaPedido();

        assertTrue(pedidosFiltrados.isEmpty());
    }


    @Test
    void buscarPedidos_deveRetornarListaVaziaSeTodosOsPedidosEstiveremFinalizados() {
        Pedido pedido1 = new Pedido();
        pedido1.setStatus(StatusPedido.FINALIZADO);

        Pedido pedido2 = new Pedido();
        pedido2.setStatus(StatusPedido.FINALIZADO);

        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);
        when(pedidoGateway.buscarPedidos()).thenReturn(pedidos);

        List<Pedido> pedidosFiltrados = consultarFilaPedidoUsecase.consultarFilaPedido();

        assertTrue(pedidosFiltrados.isEmpty());
    }

    @Test
    void buscarPedidos_deveRetornarNullSeOGatewayRetornarNull() {
        when(pedidoGateway.buscarPedidos()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> consultarFilaPedidoUsecase.consultarFilaPedido());
    }


}