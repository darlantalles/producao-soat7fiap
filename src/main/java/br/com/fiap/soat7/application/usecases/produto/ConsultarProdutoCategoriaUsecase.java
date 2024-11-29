package br.com.fiap.soat7.application.usecases.produto;

import br.com.fiap.soat7.application.gateways.ProdutoGateway;
import br.com.fiap.soat7.domain.entity.Produto;
import br.com.fiap.soat7.domain.types.Categoria;

import java.util.List;

public class ConsultarProdutoCategoriaUsecase {

    private final ProdutoGateway produtoGateway;

    public ConsultarProdutoCategoriaUsecase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public List<Produto> consultarPorCategoria(Categoria categoria) {
        return produtoGateway.consultarPorCategoria(categoria);
    }
}
