package com.petagenda.agendamentosaas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.petagenda.agendamentosaas.model.Pet;
import com.petagenda.agendamentosaas.repository.PetRepository;

@Service
public class PetService {
    
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet salvar(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> listarPorCliente(Long clienteId) {
        return petRepository.findByCliente(clienteId);
    }

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }
}
