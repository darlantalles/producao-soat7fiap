package br.com.fiap.soat7.infrastructure.integration.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.fiap.soat7.domain.Pedido;

@FeignClient(name = "pagamentos", url = "${pagamentos.url}")
public interface PagamentoClient {

    @PostMapping(value = "/pagamentos", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Pedido> efetuarPagamento(@RequestBody Pedido pedido);

}
