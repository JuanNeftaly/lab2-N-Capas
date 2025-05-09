package Models.Entities;
//
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "departamento")
    private Set<Empleado> empleados = new HashSet<>();

    // Método getter para la relación empleados
    public Set<Empleado> getEmpleados() {
        return empleados;
    }
}
