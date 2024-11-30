package com.unir.usuario_microservice.service;

import com.unir.usuario_microservice.entity.Usuario;
import com.unir.usuario_microservice.entity.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Optional<Usuario> getFindBy(Integer id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
