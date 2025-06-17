package com.petagenda.agendamentosaas.repository;

import com.petagenda.agendamentosaas.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByEmail(String email);
}
