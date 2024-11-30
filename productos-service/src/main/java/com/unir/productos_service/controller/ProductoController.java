package com.unir.productos_service.controller;

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

import com.unir.productos_service.entity.Producto;
import com.unir.productos_service.service.ProductoService;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin("*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> listarProductos() {
        return productoService.getAll();
    }

    @GetMapping("/buscar/{id}")
    public Optional<Producto> buscarProducto(@PathVariable(name = "id") String id) {
        return productoService.getFindById(id);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public List<Producto> buscarProductoPorNombre(@PathVariable(name = "nombre") String nombre) {
        return productoService.findByNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<Producto> save(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.save(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Producto> update(@RequestBody Producto producto) {
        Producto productoActualizado = productoService.update(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
