package br.com.fiap.soat7.infrastructure.controllers.webhook.request;

import br.com.fiap.soat7.domain.types.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConfirmarPagamentoWebhookRequest {
    @JsonProperty("idPedido")
    private Long id;
    private StatusPagamento statusPagamento;
}