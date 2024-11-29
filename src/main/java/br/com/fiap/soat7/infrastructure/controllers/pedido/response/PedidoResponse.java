package br.com.fiap.soat7.infrastructure.controllers.pedido.response;

import br.com.fiap.soat7.domain.entity.Produto;
import br.com.fiap.soat7.domain.types.StatusPagamento;
import br.com.fiap.soat7.domain.types.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PedidoResponse {

    private Long id;
    private String cpf;
    @Schema(type = "string", pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataCadastro;
    private StatusPedido status;
    private StatusPagamento statusPagamento;
    private List<Produto> produtoList;
    private String observacao;
}
