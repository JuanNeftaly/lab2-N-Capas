package Models.Entities;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String industria;
    private String emailContacto;

    @OneToMany(mappedBy = "cliente")
    private Set<Proyecto> proyectos = new HashSet<>();
}
