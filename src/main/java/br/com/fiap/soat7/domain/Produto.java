package br.com.fiap.soat7.domain;

import br.com.fiap.soat7.domain.types.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Produto {

    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private String descricao;
    private String imagem;

    public Produto(Long id) {
        this.id = id;
    }
}
