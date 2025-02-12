package com.br.estoque.comprador;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompradorService {

    private final CompradorRepository compradorRepository;

    public CompradorService(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }

    public Comprador cadastrarComprador(Comprador comprador) {
        return compradorRepository.save(comprador);
    }


    public List<Comprador> listarTodos() {
        return compradorRepository.findAll();
    }


    public Comprador buscarPorId(Long id) {
        return compradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado!"));
    }
}
