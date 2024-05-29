package com.lula.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    private String apellido;

    private long celular;

    private String correo;

    private String clave;

    boolean enabled;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
