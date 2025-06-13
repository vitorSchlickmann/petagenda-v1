package com.petagenda.agendamentosaas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.petagenda.agendamentosaas.model.Servico;
import com.petagenda.agendamentosaas.repository.ServicoRepository;

@Service
public class ServicoService {
    
    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public List<Servico> listarPorEmpresa(Long empresaId) {
        return servicoRepository.findByEmpresaId(empresaId);
    }

    public void deletar(Long id) {
        servicoRepository.deleteById(id);
    }
}
