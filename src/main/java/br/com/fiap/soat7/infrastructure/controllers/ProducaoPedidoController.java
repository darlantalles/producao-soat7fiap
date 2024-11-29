package br.com.fiap.soat7.infrastructure.controllers;

import br.com.fiap.soat7.application.usecases.pedido.*;
import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.domain.Produto;
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
@RequestMapping("/rest/api/v1/Pedido")
public class ProducaoPedidoController {

    private final AdicionarPedidoUsecase adicionarPedidoUsecase;
    private final MarcarPedidoProntoUsecase marcarPedidoProntoUsecase;
    private final BuscarTodosPedidosUsecase buscarTodosPedidosUsecase;

    private final ModelMapper modelMapper;


    public ProducaoPedidoController(AdicionarPedidoUsecase adicionarPedidoUsecase,
                                    MarcarPedidoProntoUsecase marcarPedidoProntoUsecase,
                                    BuscarTodosPedidosUsecase buscarTodosPedidosUsecase,
                                    ModelMapper modelMapper) {
        this.adicionarPedidoUsecase = adicionarPedidoUsecase;
        this.marcarPedidoProntoUsecase = marcarPedidoProntoUsecase;
        this.buscarTodosPedidosUsecase = buscarTodosPedidosUsecase;
        this.modelMapper = modelMapper;
        setupModelMapper(this.modelMapper);
    }

    @PostMapping("/solicitar")
    public ResponseEntity<PedidoResponse> solicitarPedido(@RequestBody PedidoRequest request) {
        Pedido pedidoRequest = modelMapper.map(request, Pedido.class);
        PedidoResponse response = modelMapper.map(adicionarPedidoUsecase.criarPedido(pedidoRequest), PedidoResponse.class);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/atualizar-status/{id}")
    public ResponseEntity<PedidoResponse> atualizarStatus(@PathVariable Long id) {
        Pedido pedido = marcarPedidoProntoUsecase.marcarComoPronto(id);
        PedidoResponse response = modelMapper.map(pedido, PedidoResponse.class);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/listar-fila")
    public ResponseEntity<List<Pedido>> buscarFilaPedido(){
        return ResponseEntity.ok().body(buscarTodosPedidosUsecase.buscarFilaPedido());
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
