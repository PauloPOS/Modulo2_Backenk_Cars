package com.acme.cars.repository;

import com.acme.cars.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    // Busca carros pelo fabricante, ignorando diferenças de maiúsculas e minúsculas
    List<Carro> findByFabricanteContainingIgnoreCase(String fabricante);

    // Busca distintos fabricante disponíveis no banco de dados
    @Query("SELECT DISTINCT c.fabricante FROM Carro c")
    List<String> findDistinctFabricante();
}
