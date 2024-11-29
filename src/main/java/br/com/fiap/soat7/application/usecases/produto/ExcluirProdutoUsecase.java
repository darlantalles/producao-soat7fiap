package br.com.fiap.soat7.application.usecases.produto;

import br.com.fiap.soat7.application.gateways.ProdutoGateway;

public class ExcluirProdutoUsecase {

    private final ProdutoGateway produtoGateway;

    public ExcluirProdutoUsecase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public void excluir(Long id) {
        produtoGateway.excluir(id);
    }
}
