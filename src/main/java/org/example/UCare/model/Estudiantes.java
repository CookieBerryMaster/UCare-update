package org.example.UCare.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name="Estudiantes.findByUser",query="select e " +
                "from Estudiantes  e where  e.nombre = ?1 and e.contrasenia = ?2")
    })@NamedQuery(name="Estudiantes.findByEstudiantes",query="select e from Estudiantes e where e.nombre like ?1")
public class Estudiantes {
    @Id
    private int cif;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenia;

}
