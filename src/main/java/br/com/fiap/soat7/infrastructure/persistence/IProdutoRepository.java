package br.com.fiap.soat7.infrastructure.persistence;

import br.com.fiap.soat7.domain.types.Categoria;
import br.com.fiap.soat7.infrastructure.persistence.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProdutoRepository  extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByCategoria(Categoria categoria);
}
