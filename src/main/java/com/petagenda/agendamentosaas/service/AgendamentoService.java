package com.petagenda.agendamentosaas.service;

import com.petagenda.agendamentosaas.model.Agendamento;
import com.petagenda.agendamentosaas.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public Agendamento salvar(Agendamento agendamento) {
        return repository.save(agendamento);
    }

    public List<Agendamento> listarTodos() {
        return repository.findAll();
    }

    public List<Agendamento> listarPorEmpresa(Long empresaId) {
        return repository.findByEmpresaId(empresaId);
    }

    public List<Agendamento> listarPorEmpresaEData(Long empresaId, LocalDate data) {
        return repository.findByEmpresaIdAndData(empresaId, data);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
