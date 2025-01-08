package com.unir.productos_service.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unir.productos_service.entity.Producto;
import com.unir.productos_service.service.ProductoService;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Object> buscarProducto(@PathVariable(name = "id", required=true) Integer id) {
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
    public ResponseEntity<Object> buscarProductoPorNombre(@PathVariable(name = "nombre", required=true) String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Hace falta que le pasen el nombre");
        }
        List<Producto> producto = productoService.findByNombre(nombre);
        if (producto != null && !producto.isEmpty()) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.status(404).body("Producto no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(
            @RequestParam("nombre") String nombre,
            @RequestParam("cantidad") Integer cantidad,
            @RequestParam("precio") Integer precio,
            @RequestParam("descripcion") String descripcion,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) {
        try {
            String imagePath = null;
            if (imagen != null && !imagen.isEmpty()) {
                String uploadDir = "uploads/";
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }


                imagePath = uploadDir + imagen.getOriginalFilename();
                imagen.transferTo(new File(imagePath));
            }


            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setCantidad(cantidad);
            producto.setPrecio(precio);
            producto.setDescripcion(descripcion);
            producto.setImagePath(imagePath);

            return ResponseEntity.ok(productoService.save(producto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar el producto: " + e.getMessage());
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
