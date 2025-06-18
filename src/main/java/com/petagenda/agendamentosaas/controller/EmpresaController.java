package com.petagenda.agendamentosaas.controller;

import com.petagenda.agendamentosaas.model.Empresa;
import com.petagenda.agendamentosaas.repository.EmpresaRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*") // Permite acesso do front-end (React)
public class EmpresaController {

    private final EmpresaRepository empresaRepo;

    public EmpresaController(EmpresaRepository empresaRepo) {
        this.empresaRepo = empresaRepo;
    }

    // ✅ Listar todas as empresas
    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        List<Empresa> empresas = empresaRepo.findAll();
        return ResponseEntity.ok(empresas);
    }

    // ✅ Buscar empresa por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEmpresaPorId(@PathVariable Long id) {
        Optional<Empresa> empresaOpt = empresaRepo.findById(id);
        return empresaOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Criar nova empresa
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody Empresa empresa) {
        Empresa novaEmpresa = empresaRepo.save(empresa);
        return ResponseEntity.ok(novaEmpresa);
    }

    // ✅ Atualizar empresa existente
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        Optional<Empresa> empresaOpt = empresaRepo.findById(id);
        if (empresaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Empresa empresa = empresaOpt.get();
        empresa.setNome(empresaAtualizada.getNome());
        empresa.setEmail(empresaAtualizada.getEmail());
        empresa.setSenha(empresaAtualizada.getSenha());
        empresa.setCnpj(empresaAtualizada.getCnpj());
        empresa.setEndereco(empresaAtualizada.getEndereco());
        empresa.setCep(empresaAtualizada.getCep());
        empresa.setEstado(empresaAtualizada.getEstado());
        empresa.setCidade(empresaAtualizada.getCidade());
        empresa.setBairro(empresaAtualizada.getBairro());
        empresa.setRua(empresaAtualizada.getRua());
        empresa.setNumero(empresaAtualizada.getNumero());
        empresa.setComplemento(empresaAtualizada.getComplemento());
        empresa.setImageLogoUrl(empresaAtualizada.getImageLogoUrl());

        empresaRepo.save(empresa);

        return ResponseEntity.ok(empresa);
    }

    // ✅ Deletar empresa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEmpresa(@PathVariable Long id) {
        if (!empresaRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        empresaRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
