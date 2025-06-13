package com.petagenda.agendamentosaas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petagenda.agendamentosaas.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByEmpresaId(Long empresaId);
    
}
