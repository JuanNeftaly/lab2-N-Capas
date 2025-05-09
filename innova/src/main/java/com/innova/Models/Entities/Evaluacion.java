package Models.Entities;
//
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private int puntaje;
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    // Método getter para la relación empleado
    public Empleado getEmpleado() {
        return empleado;
    }

    // Método setter para la relación empleado
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}