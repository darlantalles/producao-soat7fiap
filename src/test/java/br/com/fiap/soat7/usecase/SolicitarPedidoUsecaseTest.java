package br.com.fiap.soat7.usecase;

import br.com.fiap.soat7.application.gateways.PedidoGateway;
import br.com.fiap.soat7.application.usecases.pedido.SolicitarPedidoUsecase;
import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.domain.types.StatusPagamento;
import br.com.fiap.soat7.domain.types.StatusPedido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SolicitarPedidoUsecaseTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private SolicitarPedidoUsecase solicitarPedidoUsecase;

    @Test
    void criarPedido_deveCriarUmNovoPedidoComStatusCorreto_quandoPagamentoAprovado() {
        // Arrange
        Pedido pedido = new Pedido();
        pedido.setId(1L); // Adicione um ID para facilitar a verificação
        // ... outros dados do pedido ...

        Pedido pedidoAprovado = new Pedido();
        pedidoAprovado.setId(1L);
        pedidoAprovado.setStatus(StatusPedido.RECEBIDO);
        pedidoAprovado.setStatusPagamento(StatusPagamento.APROVADO); // Simulando aprovação
        // ... outros dados ...

        when(pedidoGateway.adicionarPedido(any(Pedido.class))).thenReturn(pedidoAprovado);

        // Act
        Pedido resultado = solicitarPedidoUsecase.solicitarPedido(pedido);

        // Assert
        assertNotNull(resultado);
        assertEquals(StatusPedido.RECEBIDO, resultado.getStatus());
        assertEquals(StatusPagamento.APROVADO, resultado.getStatusPagamento()); // Verifica se o status está correto
    }

    @Test
    void criarPedido_deveLancarException_quandoGatewayFalhar() {
        // Arrange
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        // ... outros dados ...

        Pedido pedidoAprovado = new Pedido();
        pedidoAprovado.setId(1L);
        pedidoAprovado.setStatusPagamento(StatusPagamento.APROVADO);

        // Simulando erro no Gateway
        doThrow(new RuntimeException("Erro no gateway")).when(pedidoGateway).adicionarPedido(any(Pedido.class));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> solicitarPedidoUsecase.solicitarPedido(pedido));

    }
}