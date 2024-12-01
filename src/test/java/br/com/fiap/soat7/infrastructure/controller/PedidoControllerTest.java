package br.com.fiap.soat7.infrastructure.controller;


import br.com.fiap.soat7.application.usecases.pedido.AtualizarStatusPedidoUsecase;
import br.com.fiap.soat7.application.usecases.pedido.ConsultarFilaPedidoUsecase;
import br.com.fiap.soat7.application.usecases.pedido.SolicitarPedidoUsecase;
import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.domain.Produto;
import br.com.fiap.soat7.domain.types.Categoria;
import br.com.fiap.soat7.domain.types.StatusPagamento;
import br.com.fiap.soat7.domain.types.StatusPedido;
import br.com.fiap.soat7.infrastructure.controllers.GestorPedidosController;
import br.com.fiap.soat7.infrastructure.controllers.request.PedidoRequest;
import br.com.fiap.soat7.infrastructure.controllers.response.PedidoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Mock
    private SolicitarPedidoUsecase solicitarPedidoUsecase;
    @Mock
    private AtualizarStatusPedidoUsecase atualizarStatusPedidoUsecase;
    @Mock
    private ConsultarFilaPedidoUsecase consultarFilaPedidoUsecase;

    private GestorPedidosController pedidoController;

    private Pedido pedido;
    private PedidoRequest pedidoRequest;
    private PedidoResponse pedidoResponse;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<List<Long>, List<Produto>> idToProdutoConverter = new AbstractConverter<>() {
            protected List<Produto> convert(List<Long> source) {
                return source.stream().map(Produto::new).toList();
            }
        };
        modelMapper.typeMap(PedidoRequest.class, Pedido.class).addMappings(mapper ->
                mapper.using(idToProdutoConverter).map(PedidoRequest::getIdProdutoList, Pedido::setProdutoList)
        );
        pedidoController = new GestorPedidosController(solicitarPedidoUsecase, atualizarStatusPedidoUsecase, consultarFilaPedidoUsecase, modelMapper);
        pedido = new Pedido();
        Produto p1 = new Produto(1L,"teste", Categoria.BEBIDA, BigDecimal.TEN, "teste", "teste imagem");
        List<Produto> produtos = new ArrayList<>();
        produtos.add(p1);

        pedido.setId(1L);
        pedido.setCpf("70156056110");
        pedido.setDataCadastro(Date.valueOf(LocalDate.now()));
        pedido.setStatusPagamento(StatusPagamento.APROVADO);
        pedido.setProdutoList(produtos);
        pedido.setObservacao("Teste");
        pedido.setStatus(StatusPedido.RECEBIDO);

        List<Long> listRequest = new ArrayList<>();
        listRequest.add(1L);
        pedidoRequest = new PedidoRequest();
        pedidoRequest.setCpf("70156056110");
        pedidoRequest.setDataCadastro(Date.valueOf(LocalDate.now()));
        pedidoRequest.setIdProdutoList(listRequest);
        pedidoRequest.setObservacao("Teste");
        pedidoRequest.setStatus(StatusPedido.RECEBIDO);


        pedidoResponse = new PedidoResponse();
        pedidoResponse.setId(1L);
        pedidoResponse.setId(1L);
        pedidoResponse.setCpf("70156056110");
        pedidoResponse.setDataCadastro(Date.valueOf(LocalDate.now()));
        pedidoResponse.setStatusPagamento(StatusPagamento.APROVADO);
        pedidoResponse.setProdutoList(produtos);
        pedidoResponse.setObservacao("Teste");
        pedidoResponse.setStatus(StatusPedido.RECEBIDO);

    }


    @Test
    void solicitarPedido_deveRetornarPedidoResponse_quandoSucesso() {
        when(solicitarPedidoUsecase.solicitarPedido(any(Pedido.class))).thenReturn(pedido);
        ResponseEntity<PedidoResponse> response = pedidoController.solicitarPedido(pedidoRequest);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(pedidoResponse.getId(), response.getBody().getId());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void marcarComoPronto_deveRetornarPedidoResponse_quandoSucesso() {
        Long id = 1L;
        when(atualizarStatusPedidoUsecase.atualizar(id, StatusPedido.PRONTO)).thenReturn(pedido);
        ResponseEntity<PedidoResponse> response = pedidoController.atualizarStatus(id, StatusPedido.PRONTO);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(pedidoResponse.getId(), response.getBody().getId());
        assertEquals(200, response.getStatusCode().value());
    }


    @Test
    void consultarFilaPedidos_deveRetornarListaDePedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);
        when(consultarFilaPedidoUsecase.consultarFilaPedido()).thenReturn(pedidos);
        ResponseEntity<List<Pedido>> response = pedidoController.consultarFilaPedido();
        assertNotNull(response);
        assertEquals(pedidos, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void atualizaPedidoEmPreparacao_deveRetornarPedidoResponse_quandoSucesso() {
        Long id = 1L;
        when(atualizarStatusPedidoUsecase.atualizar(id, StatusPedido.EM_PREPARACAO)).thenReturn(pedido);
        ResponseEntity<PedidoResponse> response = pedidoController.atualizarStatus(id, StatusPedido.EM_PREPARACAO);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(pedidoResponse.getId(), response.getBody().getId());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void atualizarPedidoParaFinalizado_deveRetornarPedidoResponse_quandoSucesso() {
        Long id = 1L;
        when(atualizarStatusPedidoUsecase.atualizar(id, StatusPedido.FINALIZADO)).thenReturn(pedido);
        ResponseEntity<PedidoResponse> response = pedidoController.atualizarStatus(id, StatusPedido.FINALIZADO);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(pedidoResponse.getId(), response.getBody().getId());
        assertEquals(200, response.getStatusCode().value());
    }

}