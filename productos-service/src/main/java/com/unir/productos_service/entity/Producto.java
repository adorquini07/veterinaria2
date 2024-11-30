package com.unir.productos_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El campo nombre no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "El campo nombre solo puede contener letras y números")
    private String nombre;

    private String descripcion;
    @NotNull(message = "El campo precio no puede ser nulo")
    @Pattern(regexp = "^[0-9]+", message = "El campo precio solo puede contener números")
    private Integer precio;
    @NotNull(message = "El campo cantidad no puede ser nulo")
    @Pattern(regexp = "^[0-9]*$", message = "El campo cantidad solo puede contener números")
    private Integer cantidad;
}
