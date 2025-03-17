package com.br.estoque.venda;

import com.br.estoque.comprador.Comprador;
import com.br.estoque.comprador.CompradorRepository;
import com.br.estoque.produto.Produto;
import com.br.estoque.produto.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final CompradorRepository compradorRepository;

    public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository, CompradorRepository compradorRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.compradorRepository = compradorRepository;
    }

//    @Transactional
//    public Venda realizarVenda(Long produtoId, int quantidadeVendida, Comprador comprador) {
//        if (produtoId == null) {
//            throw new RuntimeException("ID do produto não pode ser nulo!");
//        }
//        if (comprador == null || comprador.getId() == null) {
//            throw new RuntimeException("ID do comprador não pode ser nulo!");
//        }
//
//        Produto produto = produtoRepository.findById(produtoId)
//                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
//
//        if (produto.getQuantidadeEstoque() < quantidadeVendida) {
//            throw new RuntimeException("Quantidade em estoque insuficiente!");
//        }
//
//        // Atualiza o estoque
//        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidadeVendida);
//        produtoRepository.save(produto);
//
//        // Salva o comprador (se necessário)
//        comprador = compradorRepository.save(comprador);
//
//        // Cria e salva a venda
//        Venda venda = new Venda();
//        venda.setProduto(produto);
//        venda.setComprador(comprador);
//        venda.setQuantidadeVendida(quantidadeVendida);
//
//        return vendaRepository.save(venda);
//    }

//    public Venda registrarVenda(@Valid Venda vendaRequest) {
//        Optional<Produto> produtoOpt = produtoRepository.findById(vendaRequest.getProduto().getId());
//
//        if (produtoOpt.isPresent()) {
//            Produto produto = produtoOpt.get();
//
//            if(produto.getQuantidadeEstoque() >= vendaRequest.getQuantidadeVendida()) {
//                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - vendaRequest.getQuantidadeVendida());
//                produtoRepository.save(produto);
//
//                compradorRepository.save(vendaRequest.getComprador());
//                return vendaRepository.save(vendaRequest);
//            } else {
//                throw new RuntimeException("Estoque Insuficiente!");
//            }
//        } else {
//            throw new RuntimeException("Produto não encontrado!");
//        }
//    }

    @Transactional
    public Venda registrarVenda(Venda venda) {
        if (venda.getProduto() == null || venda.getProduto().getId() == null) {
            throw new RuntimeException("Produto não pode ser nulo");
        }
        if(venda.getComprador() == null || venda.getComprador().getId() == null) {
            throw new RuntimeException("Comprador não pode ser nulo");
        }

        Optional<Produto> produtoOpt = produtoRepository.findById(venda.getProduto().getId());
        if(produtoOpt.isEmpty()) {
            throw new RuntimeException("Produto não encontrado");
        }

        Optional<Comprador> compradorOpt = compradorRepository.findById(venda.getComprador().getId());
        if(compradorOpt.isEmpty()) {
            throw new RuntimeException("Comprador não encontrado");
        }

        Produto produto = produtoOpt.get();
        Comprador comprador = compradorOpt.get();

        if(produto.getQuantidadeEstoque() < venda.getQuantidadeVendida()) {
            throw new RuntimeException("Quantidade em estoque insuficiente!");
        }

        //Lógica para atualizar o estoque no banco
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - venda.getQuantidadeVendida());
        produtoRepository.save(produto);

        //Atualizar os dados da venda antes de salvar o registro da venda
        venda.setProduto(produto);
        venda.setComprador(comprador);

        return vendaRepository.save(venda);
    }

    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }

}



