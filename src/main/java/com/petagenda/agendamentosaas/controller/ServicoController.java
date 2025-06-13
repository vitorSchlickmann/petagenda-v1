package com.petagenda.agendamentosaas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petagenda.agendamentosaas.model.Servico;
import com.petagenda.agendamentosaas.service.ServicoService;

@RestController
@RequestMapping("/api/servico")
@CrossOrigin(origins = "*")
public class ServicoController {
    
    private final ServicoService servicoService;
    
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @PostMapping
    public ResponseEntity<Servico> cadastrar(@RequestBody Servico servico) {
        return ResponseEntity.ok(servicoService.salvar(servico));
    }

    @GetMapping
    public ResponseEntity<List<Servico>> listarTodos(@PathVariable Long empresaId) {
        return ResponseEntity.ok(servicoService.listarTodos());
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Servico>> listarPorEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(servicoService.listarPorEmpresa(empresaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
}
