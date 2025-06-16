package com.petagenda.agendamentosaas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petagenda.agendamentosaas.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
}
