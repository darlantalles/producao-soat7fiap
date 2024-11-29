package br.com.fiap.soat7.infrastructure.integration.service;


import br.com.fiap.soat7.domain.Pedido;
import br.com.fiap.soat7.infrastructure.integration.client.PagamentoClient;

public class PagamentoService {

    private PagamentoClient pagamentoClient;

    public PagamentoService(PagamentoClient pagamentoClient) {
        this.pagamentoClient = pagamentoClient;
    }

    public Pedido efetuarPagamento(final Pedido pedido) {
        return pagamentoClient.efetuarPagamento(pedido).getBody();
    }
}
