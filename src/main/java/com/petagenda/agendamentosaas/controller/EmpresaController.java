package com.petagenda.agendamentosaas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petagenda.agendamentosaas.model.Empresa;
import com.petagenda.agendamentosaas.service.EmpresaService;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin(origins = "*")
public class EmpresaController {
    
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<Empresa> cadastrarEmpresa(@RequestBody Empresa empresa) {
        return ResponseEntity.ok(empresaService.salvar(empresa));
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarTodasEmpresas() {
        return ResponseEntity.ok(empresaService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        Empresa atualizada = empresaService.atualizar(id, empresaAtualizada);

        return ResponseEntity.ok(atualizada);
    }

    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
        empresaService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
