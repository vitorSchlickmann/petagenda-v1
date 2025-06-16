package com.petagenda.agendamentosaas.controller;

import com.petagenda.agendamentosaas.model.Cliente;
import com.petagenda.agendamentosaas.model.Empresa;
import com.petagenda.agendamentosaas.repository.ClienteRepository;
import com.petagenda.agendamentosaas.repository.EmpresaRepository;
import com.petagenda.agendamentosaas.security.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final JwtUtil jwtUtil;
    private final EmpresaRepository empresaRepo;
    private final ClienteRepository clienteRepo;

    public UserController(JwtUtil jwtUtil, EmpresaRepository empresaRepo, ClienteRepository clienteRepo) {
        this.jwtUtil = jwtUtil;
        this.empresaRepo = empresaRepo;
        this.clienteRepo = clienteRepo;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getLoggedUser(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Token JWT não enviado.");
        }

        String token = authHeader.substring(7);
        Claims claims = jwtUtil.parseToken(token);
        String email = claims.getSubject();
        String role = (String) claims.get("role");

        if ("EMPRESA".equals(role)) {
            Optional<Empresa> empresaOpt = empresaRepo.findByEmail(email);
            if (empresaOpt.isPresent()) {
                return ResponseEntity.ok(empresaOpt.get());
            } else {
                return ResponseEntity.status(404).body("Empresa não encontrada.");
            }
        } else if ("CLIENTE".equals(role)) {
            Optional<Cliente> clienteOpt = clienteRepo.findByEmail(email);
            if (clienteOpt.isPresent()) {
                return ResponseEntity.ok(clienteOpt.get());
            } else {
                return ResponseEntity.status(404).body("Cliente não encontrado.");
            }
        }

        return ResponseEntity.status(400).body("Tipo de usuário inválido.");
    }
}
