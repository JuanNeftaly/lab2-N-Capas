package Models.Entities;

import jakarta.persistence.*;

import java.util.HashSet;

@Entity
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precioBase;

    @OneToMany(mappedBy = "area")
    private Set<Proyecto> proyectos = new HashSet<>();

    @OneToMany(mappedBy = "area")
    private Set<Categoria> categorias = new HashSet<>();
}
