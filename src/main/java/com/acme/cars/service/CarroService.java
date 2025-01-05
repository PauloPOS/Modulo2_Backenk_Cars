package com.acme.cars.service;

import com.acme.cars.exception.RecursoNaoEncontradoException;
import com.acme.cars.model.Carro;
import com.acme.cars.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarroService {
    private final CarroRepository carroRepository;

    // Método para listar carros paginados
    public Page<Carro> listarTodosPaginado(Pageable pageable) {
        return carroRepository.findAll(pageable);
    }

    // Buscar carro por ID
    public Carro buscarPorId(Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado com id: " + id));
    }

    // Salvar um novo carro
    public Carro salvar(Carro carro) {
        return carroRepository.save(carro);
    }

    // Atualizar um carro existente
    public Carro atualizar(Long id, Carro carroAtualizado) {
        if (!carroRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Carro não encontrado com id: " + id);
        }
        carroAtualizado.setId(id);
        return carroRepository.save(carroAtualizado);
    }

    // Deletar um carro
    public void deletar(Long id) {
        carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado com id: " + id));
        carroRepository.deleteById(id);
    }

    // Buscar carros pelo fabricante
    public List<Carro> listarPorFabricante(String fabricante) {
        return carroRepository.findByFabricanteContainingIgnoreCase(fabricante);
    }

    // Listar fabricantes distintos
    public List<String> listarFabricante() {
        return carroRepository.findDistinctFabricante();
    }
}
