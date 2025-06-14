package com.petagenda.agendamentosaas.controller;

import com.petagenda.agendamentosaas.model.Pet;
import com.petagenda.agendamentosaas.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*")
public class PetController {

    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Pet> cadastrar(@RequestBody Pet pet) {
        return ResponseEntity.ok(service.salvar(pet));
    }

    @GetMapping
    public ResponseEntity<List<Pet>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pet>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.listarPorCliente(clienteId));
    }
}
