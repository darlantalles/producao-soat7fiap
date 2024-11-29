package br.com.fiap.soat7.infrastructure.controllers.produto;

import br.com.fiap.soat7.application.usecases.produto.AdicionarProdutoUsecase;
import br.com.fiap.soat7.application.usecases.produto.ConsultarProdutoCategoriaUsecase;
import br.com.fiap.soat7.application.usecases.produto.EditarProdutoUsecase;
import br.com.fiap.soat7.application.usecases.produto.ExcluirProdutoUsecase;
import br.com.fiap.soat7.domain.entity.Produto;
import br.com.fiap.soat7.domain.types.Categoria;
import br.com.fiap.soat7.infrastructure.controllers.produto.request.CategoriaRequest;
import br.com.fiap.soat7.infrastructure.controllers.produto.request.ProdutoRequest;
import br.com.fiap.soat7.infrastructure.controllers.produto.response.ProdutoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest/api/v1/produtos")
public class ProdutoController {

    private final AdicionarProdutoUsecase adicionarProdutoUsecase;

    private final ConsultarProdutoCategoriaUsecase consultarProdutoCategoriaUsecase;

    private final ExcluirProdutoUsecase excluirProdutoUsecase;

    private final EditarProdutoUsecase editarProdutoUsecase;

    private final ModelMapper modelMapper;

    public ProdutoController(AdicionarProdutoUsecase adicionarProdutoUsecase,
                             ConsultarProdutoCategoriaUsecase consultarProdutoCategoriaUsecase,
                             ExcluirProdutoUsecase excluirProdutoUsecase,
                             EditarProdutoUsecase editarProdutoUsecase, ModelMapper modelMapper) {
        this.adicionarProdutoUsecase = adicionarProdutoUsecase;
        this.consultarProdutoCategoriaUsecase = consultarProdutoCategoriaUsecase;
        this.excluirProdutoUsecase = excluirProdutoUsecase;
        this.editarProdutoUsecase = editarProdutoUsecase;
        this.modelMapper = modelMapper;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id, @RequestBody ProdutoRequest request)  {
        Produto produtoRequest = modelMapper.map(request, Produto.class);
        Produto retorno = editarProdutoUsecase.editarProduto(id,produtoRequest);
        ProdutoResponse response = modelMapper.map(retorno, ProdutoResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionarProduto(@RequestBody ProdutoRequest request){
        Produto produtoRequest = modelMapper.map(request, Produto.class);
        Produto retorno = adicionarProdutoUsecase.adicionarProduto(produtoRequest);
        ProdutoResponse response = modelMapper.map(retorno, ProdutoResponse.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        excluirProdutoUsecase.excluir(id);
        return ResponseEntity.noContent().build();
    }

     @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Produto>> buscarPorCategoria(@PathVariable CategoriaRequest categoria) {
        List<Produto> produtos =
                consultarProdutoCategoriaUsecase.consultarPorCategoria(Categoria.valueOf(categoria.name()));
        return ResponseEntity.ok(produtos);
    }

}
