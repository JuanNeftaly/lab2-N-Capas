package Models.Entities;

import Services.EmpleadoService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public Set<Empleado> getMentores() {
        return mentores;
    }

    public void setMentores(Set<Empleado> mentores) {
        this.mentores = mentores;
    }

    // Puedes agregar métodos auxiliares si deseas
    public void agregarMentor(Empleado mentor) {
        this.mentores.add(mentor);
    }

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable Long id) {
        Empleado empleado = empleadoService.getEmpleadoById(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Getters y setters
}
