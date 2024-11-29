package br.com.fiap.soat7.application.usecases.produto;

import br.com.fiap.soat7.application.gateways.ProdutoGateway;
import br.com.fiap.soat7.domain.entity.Produto;

public class AdicionarProdutoUsecase {

    private final ProdutoGateway produtoGateway;

    public AdicionarProdutoUsecase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public Produto adicionarProduto(Produto produto) {
        return produtoGateway.adicionarProduto(produto);
    }
}
