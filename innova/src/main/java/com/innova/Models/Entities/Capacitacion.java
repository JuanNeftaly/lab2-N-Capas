package com.innova.Models.Entities;
///
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Capacitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tema;
    private LocalDate fecha;
    private int duracionHoras;

    @ManyToMany
    @JoinTable(
            name = "empleado_capacitacion",
            joinColumns = @JoinColumn(name = "capacitacion_id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private Set<Empleado> empleados = new HashSet<>();
}