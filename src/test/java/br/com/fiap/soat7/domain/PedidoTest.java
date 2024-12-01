package br.com.fiap.soat7.domain;


import br.com.fiap.soat7.domain.types.StatusPagamento;
import br.com.fiap.soat7.domain.types.StatusPedido;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void deveCriarPedidoComId() {
        Long id = 1L;
        Pedido pedido = new Pedido(id);
        assertNotNull(pedido);
        assertEquals(id, pedido.getId());
        assertNull(pedido.getCpf());
        assertNull(pedido.getDataCadastro());
        assertNull(pedido.getStatus());
        assertNull(pedido.getProdutoList());
        assertNull(pedido.getObservacao());
        assertNull(pedido.getStatusPagamento());
    }

    @Test
    void deveCriarPedidoComTodosOsAtributos() {
        Long id = 1L;
        String cpf = "12345678900";
        Date dataCadastro = new Date();
        StatusPedido status = StatusPedido.RECEBIDO;
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto()); // Adicione dados se necessário nos testes
        String observacao = "Observação do pedido";
        StatusPagamento statusPagamento = StatusPagamento.APROVADO;

        Pedido pedido = new Pedido(id, cpf, dataCadastro, status, produtos, observacao, statusPagamento);

        assertNotNull(pedido);
        assertEquals(id, pedido.getId());
        assertEquals(cpf, pedido.getCpf());
        assertEquals(dataCadastro, pedido.getDataCadastro());
        assertEquals(status, pedido.getStatus());
        assertEquals(produtos.size(), pedido.getProdutoList().size()); //Verifica o tamanho da lista
        assertEquals(observacao, pedido.getObservacao());
        assertEquals(statusPagamento, pedido.getStatusPagamento());
    }

    @Test
    void deveCriarPedidoComListaDeProdutosVazia(){
        Long id = 1L;
        String cpf = "12345678900";
        Date dataCadastro = new Date();
        StatusPedido status = StatusPedido.RECEBIDO;
        List<Produto> produtos = new ArrayList<>();
        String observacao = "Observação do pedido";
        StatusPagamento statusPagamento = StatusPagamento.APROVADO;

        Pedido pedido = new Pedido(id, cpf, dataCadastro, status, produtos, observacao, statusPagamento);

        assertNotNull(pedido);
        assertEquals(0, pedido.getProdutoList().size());
    }

}