package Models.Entities;
//
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tecnologia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String version;

    @ManyToMany(mappedBy = "tecnologias")
    private Set<Proyecto> proyectos = new HashSet<>();
}