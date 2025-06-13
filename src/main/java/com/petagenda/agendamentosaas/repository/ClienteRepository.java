package com.petagenda.agendamentosaas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petagenda.agendamentosaas.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
