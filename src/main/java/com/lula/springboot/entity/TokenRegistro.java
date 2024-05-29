package com.lula.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenRegistro {
    @Id
    public String token;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    public  Usuario usuario;

    public boolean enabled;
}
