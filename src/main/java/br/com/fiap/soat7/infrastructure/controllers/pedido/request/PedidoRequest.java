package br.com.fiap.soat7.infrastructure.controllers.pedido.request;

import br.com.fiap.soat7.domain.types.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PedidoRequest {

    @Schema(example = "000.000.000-00")
    private String cpf;
    @JsonIgnore
    private Date dataCadastro;
    @JsonIgnore
    private StatusPedido status;
    @JsonDeserialize(contentAs = Long.class)
    private List<Long> idProdutoList;
    @Schema(example = "Observações do seu pedido")
    private String observacao;
}
