package org.example.UCare.model;

import lombok.Getter;
import lombok.Setter;
import org.example.UCare.filter.FilterEstudiante;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Tab;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name="Estudiantes.findByEstudent",query="select e " +
                "from Estudiantes  e where  e.cif = ?1 and e.contrasenia = ?2"),
        @NamedQuery(name="Estudiantes.findByEstudiantes",query="select e from Estudiantes e where e.nombre like ?1")
    })
/**/
@Tab(properties = "cif",
        baseCondition = "${cif}=?",filter= FilterEstudiante.class)
public class Estudiantes {
    @Id
    @Hidden
    private String cif;
    @Hidden
    private String nombre;
    @Hidden
    private String apellido;
    @Hidden
    private String correo;
    @Hidden
    private String contrasenia;

    @Hidden
    @AssertTrue(message = "ERROR")
    public boolean cifvalidator() {
        return cif != null && cif.length() > 1;
    }
    @Hidden
    @AssertTrue(message = "ERROR")
    public boolean nombrevalidator() {
        return nombre != null && nombre.length() > 1;
    }
    @Hidden
    @AssertTrue(message = "ERROR")
    public boolean apellidovalidator() {
        return apellido != null && apellido.length() > 1;
    }
    @Hidden
    @AssertTrue(message = "ERROR")
    public boolean correovalidator() {
        return correo != null && correo.length() > 1;
    }
    @Hidden
    @AssertTrue(message = "ERROR")
    public boolean contravalidator() {
        return contrasenia != null && contrasenia.length() > 1;
    }

}
