package Models.Entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
public class Project {
    @jakarta.persistence.Id
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

    public Project() {

    }

    public Proyect(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, double costoBase,
                    String area, String categoria, Cliente cliente, Empleado liderProyecto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costoBase = costoBase;
        this.area = area;
        this.categoria = categoria;
        this.cliente = cliente;
        this.liderProyecto = liderProyecto;
    }
}
