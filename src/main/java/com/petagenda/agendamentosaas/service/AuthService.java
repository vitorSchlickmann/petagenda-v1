package com.petagenda.agendamentosaas.service;

import com.petagenda.agendamentosaas.model.Cliente;
import com.petagenda.agendamentosaas.model.Empresa;
import com.petagenda.agendamentosaas.repository.ClienteRepository;
import com.petagenda.agendamentosaas.repository.EmpresaRepository;
import com.petagenda.agendamentosaas.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final EmpresaRepository empresaRepo;
    private final ClienteRepository clienteRepo;
    private final JwtUtil jwtUtil;

    public AuthService(EmpresaRepository empresaRepo, ClienteRepository clienteRepo, JwtUtil jwtUtil) {
        this.empresaRepo = empresaRepo;
        this.clienteRepo = clienteRepo;
        this.jwtUtil = jwtUtil;
    }

    public String login(String email, String senha) {
        Optional<Empresa> empresaOpt = empresaRepo.findByEmail(email);
        if (empresaOpt.isPresent() && empresaOpt.get().getSenha().equals(senha)) {
            return jwtUtil.generateToken(email, "EMPRESA");
        }

        Optional<Cliente> clienteOpt = clienteRepo.findByEmail(email);
        if (clienteOpt.isPresent() && clienteOpt.get().getSenha().equals(senha)) {
            return jwtUtil.generateToken(email, "CLIENTE");
        }

        throw new RuntimeException("Login inválido");
    }
}
