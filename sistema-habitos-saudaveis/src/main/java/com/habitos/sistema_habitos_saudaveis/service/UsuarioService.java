package com.habitos.sistema_habitos_saudaveis.service;

import com.habitos.sistema_habitos_saudaveis.model.Usuario;
import com.habitos.sistema_habitos_saudaveis.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario buscarPorId(Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado com ID: " + usuarioId);
        }
        return usuario.get();
    }

    // LÓGICA DE NEGÓCIO IMPLEMENTADA PELO SEU COLEGA
    public double calcularIMC(Long usuarioId) {
        Usuario usuario = buscarPorId(usuarioId);

        // Validação da lógica do seu colega
        if (usuario.getAltura() <= 0 || usuario.getPeso() <= 0) {
            throw new IllegalArgumentException("Peso e Altura devem ser valores positivos para calcular o IMC.");
        }

        double altura = usuario.getAltura();
        double peso = usuario.getPeso();

        double imc = peso / (altura * altura);
        return imc;
    }

    // Métodos CRUD básicos para compilar
    public Usuario atualizarUsuario(Long id, Usuario detalhesUsuario) {
        Usuario usuarioExistente = buscarPorId(id);
        // Implementação real da atualização
        return detalhesUsuario;
    }

    public void deletarUsuario(Long id) {
        buscarPorId(id);
        usuarioRepository.deleteById(id);
    }
}