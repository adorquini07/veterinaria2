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
    public ResponseEntity<Object> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(404).body("No hay usuarios disponibles");
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Object
            > buscarUsuario(@PathVariable(name = "id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("Falta el id");
        }
        Optional<Usuario> usuario = usuarioService.getFindBy(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<Object> buscarUsuarioPorNombre(@PathVariable(name = "nombre") String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Hace falta que le pasen el nombre");
        }
        return ResponseEntity.ok(usuarioService.findByNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.save(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Object> update(@RequestBody Usuario usuario) {
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Falta el usuario");
        }

        Optional<Usuario> usuarioExistente = usuarioService.getFindBy(usuario.getId());
        if (!usuarioExistente.isPresent()) {
            return ResponseEntity.status(404).body(null);
        }
        Usuario usuarioActualizado = usuarioService.save(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("Falta el id");
        }

        Optional<Usuario> usuario = usuarioService.getFindBy(id);
        if (!usuario.isPresent()) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

        try {
            usuarioService.delete(id);
            return ResponseEntity.ok("Usuario eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("No se pudo eliminar el usuario: " + e.getMessage());
        }
    }
}
