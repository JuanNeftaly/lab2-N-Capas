package com.innova.Models.Entities;
//
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private LocalDate fechaIngreso;
    private String puesto;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Evaluacion> evaluaciones = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "mentoria",
            joinColumns = @JoinColumn(name = "mentoreado_id"),
            inverseJoinColumns = @JoinColumn(name = "mentor_id")
    )
    private Set<Empleado> mentores = new HashSet<>();

    @ManyToMany(mappedBy = "mentores")
    private Set<Empleado> mentoreados = new HashSet<>();

    // Métodos getter y setter para la clase Empleado
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    // Métodos getter y setter para la relación de mentores
    public Set<Empleado> getMentores() {
        return mentores;
    }

    public void setMentores(Set<Empleado> mentores) {
        this.mentores = mentores;
    }
}
