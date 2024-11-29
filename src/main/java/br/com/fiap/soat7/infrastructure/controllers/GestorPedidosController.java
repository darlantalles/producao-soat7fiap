package br.com.fiap.soat7.infrastructure.controllers;

import br.com.fiap.soat7.application.usecases.pedido.*;
import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.domain.Produto;
import br.com.fiap.soat7.domain.types.StatusPedido;
import br.com.fiap.soat7.infrastructure.controllers.request.PedidoRequest;
import br.com.fiap.soat7.infrastructure.controllers.response.PedidoResponse;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/v1/GestorPedidos")
public class GestorPedidosController {

    private final SolicitarPedidoUsecase solicitarPedidoUsecase;
    private final AtualizarStatusPedidoUsecase atualizarStatusPedidoUsecase;
    private final ConsultarFilaPedidoUsecase consultarFilaPedidoUsecase;
    private final ModelMapper modelMapper;

    public GestorPedidosController(SolicitarPedidoUsecase solicitarPedidoUsecase,
                                   AtualizarStatusPedidoUsecase atualizarStatusPedidoUsecase,
                                   ConsultarFilaPedidoUsecase consultarFilaPedidoUsecase,
                                   ModelMapper modelMapper) {
        this.solicitarPedidoUsecase = solicitarPedidoUsecase;
        this.atualizarStatusPedidoUsecase = atualizarStatusPedidoUsecase;
        this.consultarFilaPedidoUsecase = consultarFilaPedidoUsecase;
        this.modelMapper = modelMapper;
        setupModelMapper(this.modelMapper);
    }

    @PostMapping("/solicitar")
    public ResponseEntity<PedidoResponse> solicitarPedido(@RequestBody PedidoRequest request) {
        Pedido pedidoRequest = modelMapper.map(request, Pedido.class);
        PedidoResponse response = modelMapper.map(solicitarPedidoUsecase.solicitarPedido(pedidoRequest), PedidoResponse.class);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/listar-fila")
    public ResponseEntity<List<Pedido>> consultarFilaPedido() {
        return ResponseEntity.ok().body(consultarFilaPedidoUsecase.consultarFilaPedido());
    }

    @PostMapping("/atualizar-status/{id}/{statusPedido}")
    public ResponseEntity<PedidoResponse> atualizarStatus(@PathVariable Long id, @PathVariable StatusPedido statusPedido) {
        Pedido pedido = atualizarStatusPedidoUsecase.atualizar(id, statusPedido);
        PedidoResponse response = modelMapper.map(pedido, PedidoResponse.class);
        return ResponseEntity.ok().body(response);
    }

    private void setupModelMapper(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<List<Long>, List<Produto>> idToProdutoConverter = new AbstractConverter<>() {
            protected List<Produto> convert(List<Long> source) {
                return source.stream().map(Produto::new).toList();
            }
        };
        modelMapper.typeMap(PedidoRequest.class, Pedido.class).addMappings(mapper ->
                mapper.using(idToProdutoConverter).map(PedidoRequest::getIdProdutoList, Pedido::setProdutoList)
        );
    }

}
