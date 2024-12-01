package br.com.fiap.soat7.usecase;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.application.usecases.pedido.AtualizarStatusPedidoUsecase;
import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.domain.types.StatusPedido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizarStatusPedidoUsecaseTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private AtualizarStatusPedidoUsecase atualizarStatusPedidoUsecase;

    @Test
    void marcarComoPronto_deveAtualizarStatusParaPronto_quandoPedidoExiste() {
        // Arrange
        Long id = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setStatus(StatusPedido.RECEBIDO); // ou qualquer outro status inicial

        when(pedidoGateway.atualizarStatus(id, StatusPedido.PRONTO)).thenReturn(pedido);
        when(pedidoGateway.adicionarPedido(any(Pedido.class))).thenReturn(pedido); // Simula a atualização

        // Act
        Pedido pedidoPronto = atualizarStatusPedidoUsecase.atualizar(id, StatusPedido.PRONTO);

        // Assert
        assertNotNull(pedidoPronto);
        assertEquals(id, pedidoPronto.getId());
        assertEquals(StatusPedido.PRONTO, pedidoPronto.getStatus());
    }

}