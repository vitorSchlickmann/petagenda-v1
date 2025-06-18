package com.petagenda.agendamentosaas.controller;

import com.petagenda.agendamentosaas.dto.EmpresaLoginRequest;
import com.petagenda.agendamentosaas.model.Empresa;
import com.petagenda.agendamentosaas.repository.EmpresaRepository;
import com.petagenda.agendamentosaas.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaAuthController {

    private final EmpresaRepository empresaRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public EmpresaAuthController(EmpresaRepository empresaRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.empresaRepo = empresaRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmpresaLoginRequest request) {
        Optional<Empresa> empresaOpt = empresaRepo.findByEmail(request.getEmail());

        if (empresaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Empresa não encontrada."));
        }

        Empresa empresa = empresaOpt.get();

        if (!passwordEncoder.matches(request.getSenha(), empresa.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Senha incorreta."));
        }

        String token = jwtService.gerarToken(empresa.getEmail());

        return ResponseEntity.ok(Map.of("token", token));
    }
}
