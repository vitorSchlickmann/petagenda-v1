package com.petagenda.agendamentosaas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.petagenda.agendamentosaas.model.Empresa;
import com.petagenda.agendamentosaas.repository.EmpresaRepository;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public List<Empresa> listar() {
        return empresaRepository.findAll();
    }

    public Empresa buscarPorId(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa do " + id + " não encontrada"));
    }

    public Empresa atualizar(Long id, Empresa novaEmpresa) {
        Empresa existente = buscarPorId(id);
        existente.setNome(novaEmpresa.getNome());
        existente.setCnpj(novaEmpresa.getCnpj());
        existente.setEmail(novaEmpresa.getEmail());
        existente.setSenha(novaEmpresa.getSenha());
        existente.setCep(novaEmpresa.getCep());
        existente.setEstado(novaEmpresa.getEstado());
        existente.setCidade(novaEmpresa.getCidade());
        existente.setBairro(novaEmpresa.getBairro());
        existente.setRua(novaEmpresa.getRua());
        existente.setNumero(novaEmpresa.getNumero());
        existente.setComplemento(novaEmpresa.getComplemento());
        existente.setImageLogoUrl(novaEmpresa.getImageLogoUrl());
        return empresaRepository.save(existente);
    }

    public void deletar(Long id) {
        Empresa empresa = buscarPorId(id);
        empresaRepository.delete(empresa);
    }

}
