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
    public ResponseEntity<Object> listarProductos() {
        List<Producto> productos = productoService.getAll();
        if (productos.isEmpty()) {
            return ResponseEntity.status(404).body("No hay productos disponibles");
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Object> buscarProducto(@PathVariable(name = "id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("Falta el id");
        }
        Optional<Producto> producto = productoService.getFindById(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.status(404).body("Producto no encontrado");
        }
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<Object> buscarProductoPorNombre(@PathVariable(name = "nombre") String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Hace falta que le pasen el nombre");
        }
        return ResponseEntity.ok(productoService.findByNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Producto producto) {
        if (producto == null) {
            return ResponseEntity.badRequest().body("Falta el producto");
        }
        try {
            return ResponseEntity.ok(productoService.save(producto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar el producto");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Object> update(@RequestBody Producto producto) {
        if (producto == null) {
            return ResponseEntity.badRequest().body("Falta el producto");
        }
        Optional<Producto> productoOptional = productoService.getFindById(producto.getId());
        if (productoOptional.isPresent()) {
            return ResponseEntity.ok(productoService.update(producto));
        } else {
            return ResponseEntity.badRequest().body("El producto no existe");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("Falta el id");
        }
        Optional<Producto> producto = productoService.getFindById(id);
        if (producto.isPresent()) {
            productoService.delete(id);
            return ResponseEntity.ok("Producto eliminado");
        } else {
            return ResponseEntity.status(404).body("Producto no encontrado");
        }
    }
}
