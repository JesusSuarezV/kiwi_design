package com.lula.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Codigo {

    @Id
    private String codigo;

    private int descuento;

    private boolean envioGratis;

    private boolean enabled;

}
