package com.habitos.sistema_habitos_saudaveis.controller;

import com.habitos.sistema_habitos_saudaveis.model.Usuario;
import com.habitos.sistema_habitos_saudaveis.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // 1. CREATE: POST /usuarios
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.save(usuario); // Assumindo que o Service tem um método save
        return ResponseEntity.ok(novoUsuario);
    }

    // 2. READ ALL: GET /usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll(); // Assumindo que o Service tem um método findAll
        return ResponseEntity.ok(usuarios);
    }

    // 3. READ BY ID: GET /usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id); // Reutilizando o método buscarPorId do Service
        return ResponseEntity.ok(usuario);
    }

    // 4. UPDATE: PUT /usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario detalhesUsuario) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, detalhesUsuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    // 5. DELETE: DELETE /usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // Rota de NEGÓCIO (Cálculo de IMC)
    @GetMapping("/{id}/imc")
    public ResponseEntity<Double> calcularIMC(@PathVariable Long id) {
        double imc = usuarioService.calcularIMC(id);
        return ResponseEntity.ok(imc);
    }
}