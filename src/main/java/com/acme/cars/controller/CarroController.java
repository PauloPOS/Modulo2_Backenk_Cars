package com.acme.cars.controller;

import com.acme.cars.exception.RecursoNaoEncontradoException;
import com.acme.cars.model.Carro;
import com.acme.cars.service.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", exposedHeaders = "X-Total-Count")
public class CarroController {

    private final CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> listarTodos(
            @RequestParam(value = "page", defaultValue = "0") int page, // Página
            @RequestParam(value = "size", defaultValue = "10") int size // Tamanho da página
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Carro> carrosPage = carroService.listarTodosPaginado(pageable); // Paginado
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(carrosPage.getTotalElements())); // Total de registros

        return new ResponseEntity<>(carrosPage.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable Long id) {
        try {
            Carro carro = carroService.buscarPorId(id);
            return ResponseEntity.ok(carro);
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Carro> salvar(@RequestBody Carro carro) {
        Carro carroSalvo = carroService.salvar(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizar(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
        try {
            Carro carro = carroService.atualizar(id, carroAtualizado);
            return ResponseEntity.ok(carro);
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            carroService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para pesquisar carros por Fabricante
    @GetMapping("/search")
    public ResponseEntity<List<Carro>> listarPorFabricante(@RequestParam("fabricante") String fabricante) {
        List<Carro> carros = carroService.listarPorFabricante(fabricante);
        if (carros.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(carros);
    }
    @GetMapping("/fabricante")
public ResponseEntity<List<String>> listarFabricante() {
    List<String> fabricante = carroService.listarFabricante();
    return ResponseEntity.ok(fabricante);
}
}
