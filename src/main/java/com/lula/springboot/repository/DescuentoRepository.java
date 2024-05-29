package com.lula.springboot.repository;

import com.lula.springboot.entity.Descuento;
import com.lula.springboot.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {

    Page<Descuento> findByProductoAndEnabledTrueOrderByInicioDesc(Producto producto, Pageable pageable);

    @Query("SELECT d FROM Descuento d WHERE d.enabled = true AND d.inicio <= :fin AND d.fin >= :inicio AND d.producto.id = :productoId")
    List<Descuento> buscarDescuentosSuperpuestos(
            @Param("productoId") Long productoId,
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

    @Query("SELECT d FROM Descuento d " +
            "WHERE d.producto.id = :productoId " +
            "AND d.inicio <= :fecha " +
            "AND d.fin >= :fecha " +
            "AND d.enabled = true ")
    Optional<Descuento> encontrarOfertaActual(
            @Param("productoId") Long productoId,
            @Param("fecha") LocalDate fecha
    );

}
