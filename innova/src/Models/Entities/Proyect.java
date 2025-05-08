package Models.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class Proyect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private double costoBase;

    private String area;

    private String categoria;

    // Relación con Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Relación con Empleado como líder
    @ManyToOne
    @JoinColumn(name = "lider_id")
    private Empleado liderProyecto;

    // Relación con Empleados asignados al proyecto
    @OneToMany(mappedBy = "proyecto")
    private List<EmpleadoProyecto> empleados;

    // Relación con Tecnologías
    @OneToMany(mappedBy = "proyecto")
    private List<ProyectoTecnologia> tecnologias;

    // Getters y setters

    // Constructor vacío
    public Proyect() {}

    // Constructor con parámetros puede ir también si es útil
}
