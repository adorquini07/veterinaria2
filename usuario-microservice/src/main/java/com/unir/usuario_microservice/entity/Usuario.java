package com.unir.usuario_microservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;


    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;
    private String estado;

}
