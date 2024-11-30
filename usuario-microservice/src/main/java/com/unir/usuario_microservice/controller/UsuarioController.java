package com.unir.usuario_microservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unir.usuario_microservice.entity.Usuario;
import com.unir.usuario_microservice.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return usuarioService.getAll();
    }

    @GetMapping("/buscar/{id}")
    public Optional<Usuario> buscarUsuario(@PathVariable(name = "id") Integer id) {
        return usuarioService.getFindBy(id);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public List<Usuario> buscarUsuarioPorNombre(@PathVariable(name = "nombre") String nombre) {
        return usuarioService.findByNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioService.save(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
