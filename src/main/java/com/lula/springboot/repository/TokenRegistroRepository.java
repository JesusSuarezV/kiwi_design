package com.lula.springboot.repository;

import com.lula.springboot.entity.TokenRegistro;
import org.antlr.v4.runtime.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRegistroRepository extends JpaRepository<TokenRegistro, String> {

}
