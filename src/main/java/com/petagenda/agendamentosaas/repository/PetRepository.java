package com.petagenda.agendamentosaas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petagenda.agendamentosaas.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByCliente(Long clienteId);
}
