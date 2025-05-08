package Models.Entities;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "lider_id")
    private Empleado lider;

    @ManyToMany
    @JoinTable(
            name = "empleado_proyecto",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private Set<Empleado> empleados = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "proyecto_tecnologia",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "tecnologia_id")
    )
    private Set<Tecnologia> tecnologias = new HashSet<>();
}