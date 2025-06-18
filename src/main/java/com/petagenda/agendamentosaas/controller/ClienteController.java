package com.petagenda.agendamentosaas.controller;

import com.petagenda.agendamentosaas.dto.ClienteRequest;
import com.petagenda.agendamentosaas.model.Cliente;
import com.petagenda.agendamentosaas.model.Pet;
import com.petagenda.agendamentosaas.repository.ClienteRepository;
import com.petagenda.agendamentosaas.repository.PetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")  // Permite requisições de qualquer origem
public class ClienteController {

    private final ClienteRepository clienteRepo;
    private final PetRepository petRepo;

    public ClienteController(ClienteRepository clienteRepo, PetRepository petRepo) {
        this.clienteRepo = clienteRepo;
        this.petRepo = petRepo;
    }

    // ✅ Criar Cliente
    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody ClienteRequest req) {
        Cliente cliente = Cliente.builder()
                .nome(req.getNome())
                .cpf(req.getCpf())
                .email(req.getEmail())
                .senha(req.getSenha())
                .telefone(req.getTelefone())
                .build();

        Cliente clienteSalvo = clienteRepo.save(cliente);

        Pet pet = Pet.builder()
                .nome(req.getNomePet())
                .raca(req.getRaca())
                .especie(req.getEspecie())
                .cliente(clienteSalvo)
                .build();

        petRepo.save(pet);

        return ResponseEntity.ok(clienteSalvo);
    }

    // ✅ Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteRepo.findAll();
        return ResponseEntity.ok(clientes);
    }

    // ✅ Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> clienteOpt = clienteRepo.findById(id);
        return clienteOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Buscar cliente por e-mail
    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
        Optional<Cliente> clienteOpt = clienteRepo.findByEmail(email);
        return clienteOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Atualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody ClienteRequest req) {
        Optional<Cliente> clienteOpt = clienteRepo.findById(id);
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cliente cliente = clienteOpt.get();
        cliente.setNome(req.getNome());
        cliente.setCpf(req.getCpf());
        cliente.setEmail(req.getEmail());
        cliente.setSenha(req.getSenha());
        cliente.setTelefone(req.getTelefone());

        clienteRepo.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    // ✅ Excluir cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        if (!clienteRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        clienteRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
