package br.com.fiap.soat7.infrastructure.service;

import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.domain.types.StatusPedido;
import br.com.fiap.soat7.infrastructure.client.PedidoClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoClient pedidoClient;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();  // Crie um pedido de exemplo com os dados necessários.
    }

    @Test
    void testAdicionarPedido() {
        // Dado que o pedidoClient adicione um pedido com sucesso
        when(pedidoClient.adicionarPedido(any(Pedido.class))).thenReturn(ResponseEntity.ok(pedido));

        // Quando o método adicionarPedido for chamado
        Pedido result = pedidoService.adicionarPedido(pedido);

        // Então o resultado deve ser igual ao pedido retornado pelo cliente
        assertEquals(pedido, result);
        verify(pedidoClient, times(1)).adicionarPedido(any(Pedido.class)); // Verifica que o método foi chamado uma vez
    }

    @Test
    void testBuscarPedidos() {
        // Dado que o pedidoClient retorne uma lista de pedidos
        when(pedidoClient.buscarPedidos()).thenReturn(ResponseEntity.ok(Collections.singletonList(pedido)));

        // Quando o método buscarPedidos for chamado
        List<Pedido> result = pedidoService.buscarPedidos();

        // Então o resultado deve ser uma lista com o pedido
        assertEquals(1, result.size());
        assertEquals(pedido, result.getFirst());
        verify(pedidoClient, times(1)).buscarPedidos(); // Verifica que o método foi chamado uma vez
    }

    @Test
    void testAtualizarStatus() {
        // Dado que o pedidoClient retorne um pedido com o status atualizado
        when(pedidoClient.atualizarComoPronto(anyLong())).thenReturn(ResponseEntity.ok(pedido));
        when(pedidoClient.atualizarComoEmPreparacao(anyLong())).thenReturn(ResponseEntity.ok(pedido));
        when(pedidoClient.atualizarComoFinalizado(anyLong())).thenReturn(ResponseEntity.ok(pedido));

        // Quando o método atualizarStatus for chamado para diferentes StatusPedido
        Pedido resultPronto = pedidoService.atualizarStatus(1L, StatusPedido.PRONTO);
        Pedido resultEmPreparacao = pedidoService.atualizarStatus(1L, StatusPedido.EM_PREPARACAO);
        Pedido resultFinalizado = pedidoService.atualizarStatus(1L, StatusPedido.FINALIZADO);

        // Então o resultado deve ser o pedido retornado pelo cliente
        assertEquals(pedido, resultPronto);
        assertEquals(pedido, resultEmPreparacao);
        assertEquals(pedido, resultFinalizado);

        verify(pedidoClient, times(1)).atualizarComoPronto(anyLong()); // Verifica que o método foi chamado uma vez para cada status
        verify(pedidoClient, times(1)).atualizarComoEmPreparacao(anyLong());
        verify(pedidoClient, times(1)).atualizarComoFinalizado(anyLong());
    }
}
