package com.petagenda.agendamentosaas.service;

import com.petagenda.agendamentosaas.model.Agendamento;
import com.petagenda.agendamentosaas.model.Empresa;
import com.petagenda.agendamentosaas.repository.AgendamentoRepository;
import com.petagenda.agendamentosaas.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final AgendamentoRepository agendamentoRepository;

    public EmpresaService(EmpresaRepository empresaRepository, AgendamentoRepository agendamentoRepository) {
        this.empresaRepository = empresaRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public Empresa buscarPorId(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa do " + id + " não encontrada"));
    }

    public List<Empresa> listar() {
        return empresaRepository.findAll();
    }

    public void deletar(Long id) {
        Empresa empresa = buscarPorId(id);
        empresaRepository.delete(empresa);
    }

    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Empresa atualizar(Long id, Empresa novaEmpresa) {
        Empresa empresaExistente = buscarPorId(id);

        empresaExistente.setNome(novaEmpresa.getNome());
        empresaExistente.setCnpj(novaEmpresa.getCnpj());
        empresaExistente.setEmail(novaEmpresa.getEmail());
        empresaExistente.setSenha(novaEmpresa.getSenha());
        empresaExistente.setCep(novaEmpresa.getCep());
        empresaExistente.setEstado(novaEmpresa.getEstado());
        empresaExistente.setCidade(novaEmpresa.getCidade());
        empresaExistente.setBairro(novaEmpresa.getBairro());
        empresaExistente.setRua(novaEmpresa.getRua());
        empresaExistente.setNumero(novaEmpresa.getNumero());
        empresaExistente.setComplemento(novaEmpresa.getComplemento());
        empresaExistente.setImageLogoUrl(novaEmpresa.getImageLogoUrl());

        return empresaRepository.save(empresaExistente);
    }
    

    public List<String> getHorariosDisponiveis(Long empresaId, String dataStr) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dataStr, formatter);

        List<String> horariosPossiveis = Arrays.asList(
                "09:00", "10:00", "11:00", "12:00",
                "13:00", "14:00", "15:00", "16:00",
                "17:00", "18:00"
        );

        List<Agendamento> agendamentos = agendamentoRepository.findByEmpresaIdAndData(empresaId, data);

        List<String> horariosOcupados = agendamentos.stream()
                .map(agendamento -> agendamento.getHora().toString())
                .collect(Collectors.toList());

        return horariosPossiveis.stream()
                .filter(horario -> !horariosOcupados.contains(horario))
                .collect(Collectors.toList());
    }
}
