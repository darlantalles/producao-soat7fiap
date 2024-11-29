package br.com.fiap.soat7.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.fiap.soat7.domain.Pedido;

import java.util.List;

@FeignClient(name = "pedido", url = "${url.pedido}")
public interface PedidoClient {


    @PostMapping("/pedidos/checkout")
    ResponseEntity<Pedido> adicionarPedido(@RequestBody Pedido pedido);

    @GetMapping("/pedidos")
    ResponseEntity<List<Pedido>> buscarPedidos();


    @PostMapping("/pedidos/{id}/pronto")
    ResponseEntity<Pedido> atualizarComoPronto(@PathVariable("id") Long id);

    @PostMapping("/pedidos/{id}/preparar")
    ResponseEntity<Pedido> atualizarComoEmPreparacao(@PathVariable("id") Long id);

    @PostMapping("/pedidos/{id}/finalizar")
    ResponseEntity<Pedido> atualizarComoFinalizado(@PathVariable("id") Long id);

}
