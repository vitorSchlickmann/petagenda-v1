package com.petagenda.agendamentosaas.repository;

import com.petagenda.agendamentosaas.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByEmpresaId(Long empresaId);
    List<Agendamento> findByEmpresaIdAndData(Long empresaId, LocalDate data);
}
