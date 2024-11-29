package br.com.fiap.soat7.infrastructure.gateways;

import br.com.fiap.soat7.application.gateways.ProdutoGateway;
import br.com.fiap.soat7.domain.entity.Produto;
import br.com.fiap.soat7.domain.types.Categoria;
import br.com.fiap.soat7.infrastructure.persistence.ProdutoRepository;

import java.util.List;

public class ProdutoRepositoryGateway implements ProdutoGateway {

    private final ProdutoRepository repository;

    public ProdutoRepositoryGateway(ProdutoRepository repository) {
        this.repository = repository;
    }


    @Override
    public Produto adicionarProduto(Produto produto) {
        return this.repository.save(produto);
    }

    @Override
    public Produto editarProduto(Long id, Produto produto) {
        return this.repository.atualizar(id,produto);
    }

    @Override
    public void excluir(Long id) {
        repository.delete(id);
    }

    @Override
    public List<Produto> consultarPorCategoria(Categoria categoria) {
        return repository.consultarPorCategoria(categoria);
    }
}
