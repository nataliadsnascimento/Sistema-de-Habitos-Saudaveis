package com.habitos.sistema_habitos_saudaveis.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitos.sistema_habitos_saudaveis.model.Usuario;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class UsuarioRepository {
    private static final String FILE_PATH = "usuarios.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Usuario> readAll() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Usuario>>() {});
        } catch (IOException e) {
            System.err.println("Erro ao ler usuários do arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void writeAll(List<Usuario> usuarios) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), usuarios);
        } catch (IOException e) {
            System.err.println("Erro ao escrever usuários no arquivo: " + e.getMessage());
        }
    }

    public List<Usuario> findAll() {
        return readAll();
    }

    public Usuario save(Usuario usuario) {
        List<Usuario> usuarios = readAll();

        // Define o ID: encontra o maior ID atual e adiciona 1
        Long newId = usuarios.stream()
                .map(Usuario::getId)
                .filter(java.util.Objects::nonNull)
                .max(Comparator.naturalOrder())
                .orElse(0L) + 1;

        usuario.setId(newId);
        usuarios.add(usuario);
        writeAll(usuarios);
        return usuario;
    }

    // Método auxiliar para o UsuarioService (ex: buscarPorId)
    public Optional<Usuario> findById(Long id) {
        return readAll().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public void deleteById(Long id) {
    }
}