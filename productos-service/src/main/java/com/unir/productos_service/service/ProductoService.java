package com.unir.productos_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.productos_service.entity.Producto;
import com.unir.productos_service.entity.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getFindById(Integer id) {
        return productoRepository.findById(id);
    }

    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto update(Producto producto) {
        return productoRepository.save(producto);
    }

    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }
}
