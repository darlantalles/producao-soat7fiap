package br.com.fiap.soat7.config;

import br.com.fiap.soat7.application.gateways.ProdutoGateway;
import br.com.fiap.soat7.application.usecases.produto.AdicionarProdutoUsecase;
import br.com.fiap.soat7.application.usecases.produto.ConsultarProdutoCategoriaUsecase;
import br.com.fiap.soat7.application.usecases.produto.EditarProdutoUsecase;
import br.com.fiap.soat7.application.usecases.produto.ExcluirProdutoUsecase;
import br.com.fiap.soat7.infrastructure.gateways.ProdutoRepositoryGateway;
import br.com.fiap.soat7.infrastructure.persistence.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {

    @Bean
    AdicionarProdutoUsecase adicionarProdutoUseCase(ProdutoGateway produtoGateway) {
        return new AdicionarProdutoUsecase(produtoGateway);
    }

    @Bean
    ConsultarProdutoCategoriaUsecase consultarProdutoUseCase(ProdutoGateway produtoGateway) {
        return new ConsultarProdutoCategoriaUsecase(produtoGateway);
    }

    @Bean
    EditarProdutoUsecase editarProdutoUseCase(ProdutoGateway produtoGateway) {
        return new EditarProdutoUsecase(produtoGateway);
    }

    @Bean
    ExcluirProdutoUsecase excluirProdutoUseCase(ProdutoGateway produtoGateway) {
        return new ExcluirProdutoUsecase(produtoGateway);
    }

    @Bean
    ProdutoGateway produtoGateway(ProdutoRepository produtoRepository) {
        return new ProdutoRepositoryGateway(produtoRepository);
    }
}
