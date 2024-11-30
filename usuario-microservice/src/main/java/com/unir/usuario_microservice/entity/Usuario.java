package com.unir.usuario_microservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El campo nombre no puede ser nulo")
    private String nombre;
    @NotNull(message = "El campo apellido no puede ser nulo")
    private String apellido;
    @NotNull(message = "El campo email no puede ser nulo")
    @Email
    private String email;
    @NotNull(message = "El campo password no puede ser nulo")
    private String password;
    @Pattern(regexp = "ADMIN|USER", message = "El campo rol solo puede ser ADMIN o USER")
    private String rol;
    @Pattern(regexp = "ACTIVO|INACTIVO", message = "El campo estado solo puede ser ACTIVO o INACTIVO")
    private String estado;

}
