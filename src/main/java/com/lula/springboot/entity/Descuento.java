package com.lula.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int descuento;

    private boolean envioGratis;

    private LocalDate inicio;

    private LocalDate fin;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private boolean enabled;


}
