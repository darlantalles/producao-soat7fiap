package br.com.fiap.soat7.infrastructure.controllers.produto.response;

import br.com.fiap.soat7.domain.types.Categoria;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoResponse {


    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private String descricao;
    private String imagem;
}
