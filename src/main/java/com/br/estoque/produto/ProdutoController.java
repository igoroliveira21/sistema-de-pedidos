package com.br.estoque.produto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.query.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody @Valid Produto produto) {
        Produto novoProduto = produtoService.cadastrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping
    public List<Produto> buscapProductos() {
         return produtoService.listarProdutos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProdutoEstoque(@PathVariable @NotNull Long id, @RequestBody @Valid Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        return ResponseEntity.ok(produtoAtualizado);
    }
}
