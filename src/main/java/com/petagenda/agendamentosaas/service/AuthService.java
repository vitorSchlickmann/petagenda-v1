package com.petagenda.agendamentosaas.service;

import com.petagenda.agendamentosaas.model.Cliente;
import com.petagenda.agendamentosaas.model.Empresa;
import com.petagenda.agendamentosaas.repository.ClienteRepository;
import com.petagenda.agendamentosaas.repository.EmpresaRepository;
import com.petagenda.agendamentosaas.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        System.out.println("Tentando login com Email: " + email + ", Senha: " + senha);

        Optional<Empresa> empresaOpt = empresaRepo.findByEmail(email);
        if (empresaOpt.isPresent()) {
            System.out.println("Empresa encontrada: " + empresaOpt.get().getEmail());
            System.out.println("Senha no banco: " + empresaOpt.get().getSenha());
            if (empresaOpt.get().getSenha().equals(senha)) {
                System.out.println("Login válido como EMPRESA");
                return jwtUtil.generateToken(email, "EMPRESA");
            } else {
                System.out.println("Senha da empresa não bate.");
            }
        }

        Optional<Cliente> clienteOpt = clienteRepo.findByEmail(email);
        if (clienteOpt.isPresent()) {
            System.out.println("Cliente encontrado: " + clienteOpt.get().getEmail());
            System.out.println("Senha no banco: " + clienteOpt.get().getSenha());
            if (clienteOpt.get().getSenha().equals(senha)) {
                System.out.println("Login válido como CLIENTE");
                return jwtUtil.generateToken(email, "CLIENTE");
            } else {
                System.out.println("Senha do cliente não bate.");
            }
        }

        System.out.println("Login falhou: Email ou senha inválidos.");
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos");
    }
}
