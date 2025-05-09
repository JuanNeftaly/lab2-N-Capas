package Models.Entities;
//
import jakarta.persistence.*;
import java.util.Set; // :v
import java.util.HashSet;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(mappedBy = "categoria")
    private Set<Proyecto> proyectos = new HashSet<>();
}