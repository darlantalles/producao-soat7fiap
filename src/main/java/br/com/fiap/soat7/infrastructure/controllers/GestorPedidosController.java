package br.com.fiap.soat7.infrastructure.controllers;

import br.com.fiap.soat7.application.usecases.pedido.*;
import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.domain.Produto;
import br.com.fiap.soat7.domain.types.StatusPedido;
import br.com.fiap.soat7.infrastructure.controllers.request.PedidoRequest;
import br.com.fiap.soat7.infrastructure.controllers.response.PedidoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/v1/gestor-pedidos")
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
    @Operation(summary = "Solicitar um novo pedido", description = "Cria um novo pedido com base nas informações fornecidas no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido solicitado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o pedido")
    })
    @PostMapping("/solicitar")
    public ResponseEntity<PedidoResponse> solicitarPedido(@RequestBody PedidoRequest request) {
        Pedido pedidoRequest = modelMapper.map(request, Pedido.class);
        PedidoResponse response = modelMapper.map(solicitarPedidoUsecase.solicitarPedido(pedidoRequest), PedidoResponse.class);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Listar todos os pedidos na fila", description = "Retorna a lista de todos os pedidos pendentes na fila de produção.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada com sucesso")
    })
    @GetMapping("/listar-fila")
    public ResponseEntity<List<Pedido>> consultarFilaPedido() {
        return ResponseEntity.ok().body(consultarFilaPedidoUsecase.consultarFilaPedido());
    }

    @Operation(summary = "Atualizar o status de um pedido", description = "Atualiza o status de um pedido específico com base no ID e no novo status fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do pedido atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "400", description = "Status inválido fornecido")
    })
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
