package br.com.fiap.soat7.infrastructure.controllers.pedido.response;

import br.com.fiap.soat7.domain.types.StatusPagamento;
import br.com.fiap.soat7.domain.types.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class PedidoStatusPagamentoResponse {

    private Long id;
    @Schema(type = "string", pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataCadastro;
    private StatusPedido status;
    private StatusPagamento statusPagamento;
}
