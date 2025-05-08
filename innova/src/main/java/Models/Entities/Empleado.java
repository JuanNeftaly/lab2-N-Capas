package Models.Entities;

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

    // Evaluaciones
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Evaluacion> evaluaciones = new HashSet<>();

    // Mentoría (relación reflexiva muchos a muchos)
    @ManyToMany
    @JoinTable(
            name = "mentoria",
            joinColumns = @JoinColumn(name = "mentoreado_id"),
            inverseJoinColumns = @JoinColumn(name = "mentor_id")
    )
    private Set<Empleado> mentores = new HashSet<>();

    @ManyToMany(mappedBy = "mentores")
    private Set<Empleado> mentoreados = new HashSet<>();

    // Getters y setters
}
