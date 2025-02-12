package com.br.estoque.produto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    private final ModelMapper modelmapper;

    public ProdutoService(ProdutoRepository repository, ModelMapper modelmapper) {
        this.repository = repository;
        this.modelmapper = modelmapper;
    }


    public Produto cadastrarProduto(@Valid Produto dto) {
        Produto produto = modelmapper.map(dto, Produto.class);
        repository.save(produto);
        return produto;
    }

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public Produto atualizarProduto(@NotNull Long id, @Valid Produto produto) {
        produto.setId(id);
        produto = repository.save(produto);
        return produto;
    }
}
