package com.br.estoque.comprador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compradores")
public class CompradorController {

    private final CompradorService compradorService;

    public CompradorController(CompradorService compradorService) {
        this.compradorService = compradorService;
    }

    @PostMapping
    public ResponseEntity<Comprador> cadastrarComprador(@RequestBody Comprador comprador) {
        Comprador novoComprador = compradorService.cadastrarComprador(comprador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoComprador);
    }

    @GetMapping
    public ResponseEntity<List<Comprador>> buscarTodos() {
        List<Comprador> list = compradorService.listarTodos();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Comprador> buscarCompradorPorId(@PathVariable Long id) {
        Comprador comprador = compradorService.buscarPorId(id);
        return ResponseEntity.ok(comprador);
    }
}
