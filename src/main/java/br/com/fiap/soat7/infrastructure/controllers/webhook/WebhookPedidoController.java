package br.com.fiap.soat7.infrastructure.controllers.webhook;

import br.com.fiap.soat7.application.usecases.pedido.ConfirmarPagamentoPedidoUsecase;
import br.com.fiap.soat7.domain.entity.Pedido;
import br.com.fiap.soat7.infrastructure.controllers.webhook.request.ConfirmarPagamentoWebhookRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhook/pedido")
public class WebhookPedidoController {

    private final ConfirmarPagamentoPedidoUsecase confirmarPagamentoPedidoUsecase;
    private final ModelMapper modelMapper;

    public WebhookPedidoController(ConfirmarPagamentoPedidoUsecase confirmarPagamentoPedidoUsecase,
                                   ModelMapper modelMapper) {
        this.confirmarPagamentoPedidoUsecase = confirmarPagamentoPedidoUsecase;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/confirmar-pagamento")
    public ResponseEntity<Void> confirmarPagamento(@RequestBody ConfirmarPagamentoWebhookRequest request) {
        Pedido pedidoRequest = modelMapper.map(request, Pedido.class);
        confirmarPagamentoPedidoUsecase.confirmarPagamento(pedidoRequest);
        return ResponseEntity.ok().build();
    }
}