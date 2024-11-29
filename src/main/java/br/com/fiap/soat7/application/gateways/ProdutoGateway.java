package br.com.fiap.soat7.application.gateways;

import br.com.fiap.soat7.domain.entity.Produto;
import br.com.fiap.soat7.domain.types.Categoria;

import java.util.List;

public interface ProdutoGateway {

    Produto adicionarProduto (Produto produto);

    Produto editarProduto (Long id, Produto produto);

    void excluir (Long id);

    List<Produto> consultarPorCategoria(Categoria categoria);
}
