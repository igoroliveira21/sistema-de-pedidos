package com.br.estoque.produto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    private final ModelMapper modelmapper;

    public ProdutoService(ProdutoRepository repository, ModelMapper modelmapper) {
        this.repository = repository;
        this.modelmapper = modelmapper;
    }


    public Produto cadastrarProduto(@Valid Produto produto) {
        repository.save(produto);
        return produto;
    }

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public Produto atualizarQuantidadePreco(Long id, int quantidadeAdicional, double preco ) {
        Optional<Produto> produtoOpt = repository.findById(id);
        if(produtoOpt.isEmpty()) {
            throw new RuntimeException("Produto não econtrado!");
        }

        Produto produto = produtoOpt.get();
        produto.setQuantidadeEstoque(quantidadeAdicional);
        produto.setPreco(preco);

        return repository.save(produto);
     }

    public void excluirProduto(@NotNull Long id) {
        Optional<Produto> produtoOpt = repository.findById(id);
        if (produtoOpt.isEmpty()) {
            throw new RuntimeException("Produto não existe em estoque");
        }

        repository.delete(produtoOpt.get());
    }
}
