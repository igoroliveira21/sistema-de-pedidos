package com.br.estoque.venda;

import com.br.estoque.comprador.Comprador;
import com.br.estoque.produto.Produto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

@Entity
@Table(name = "venda")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Venda {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "comprador_id")
    private Comprador comprador;

    @Min(1)
    @Column(name = "quantidade_vendida")
    private int quantidadeVendida;

//    private LocalDateTime dataVenda = LocalDateTime.now();

    public Venda(){}

    public Venda(Long id, Produto produto, Comprador comprador, int quantidadeVendida) {
        this.id = id;
        this.produto = produto;
        this.comprador = comprador;
        this.quantidadeVendida = quantidadeVendida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
}
