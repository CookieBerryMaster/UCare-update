// Paquete que contiene la clase Estudiantes en el modelo UCare
package org.example.UCare.model;

// Importación de anotaciones Lombok para la generación automática de getters y setters
import lombok.Getter;
import lombok.Setter;

// Importación de clases y anotaciones de OpenXava y JPA
import org.example.UCare.filter.FilterEstudiante;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Tab;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;

// Anotación que indica que la clase es una entidad JPA
@Entity
@Getter
@Setter
// Definición de consultas nombradas para esta entidad
// Anotación @NamedQueries: Permite definir consultas JPA (Java Persistence API) y agruparlas bajo un nombre.
@NamedQueries({
        // Consulta nombrada 'Estudiantes.findByEstudent'
        @NamedQuery(
                name="Estudiantes.findByEstudent",  // Nombre de la consulta
                query="select e from Estudiantes e where e.cif = ?1 and e.contrasenia = ?2" // Consulta JPQL (Java Persistence Query Language)
        ),
        // Consulta nombrada 'Estudiantes.findByEstudiantes'
        @NamedQuery(
                name="Estudiantes.findByEstudiantes", // Nombre de la consulta
                query="select e from Estudiantes e where e.nombre like ?1" // Consulta JPQL
        )
})

// Anotación @Tab: Define la configuración de la pestaña en la interfaz de usuario de OpenXava.
@Tab(
        // Propiedades: Especifica las propiedades que se mostrarán en la pestaña y su orden.
        properties = "cif",

        // baseCondition: Define la condición base que se aplicará al recuperar datos para la pestaña.
        // En este caso, se usa una condición parametrizada que compara el valor de 'cif' con un parámetro sin nombrar.
        // La notación "${cif}=?" indica que se espera un parámetro para 'cif' en la consulta SQL subyacente.
        baseCondition = "${cif}=?",

        // filter: Especifica la clase del filtro a aplicar a la pestaña.
        filter = FilterEstudiante.class
)

// Definición de la clase Estudiantes
public class Estudiantes {
    // Identificador de la entidad
    @Id
    @Hidden // Campo oculto en la vista
    private String cif;

    @Hidden
    private String nombre;

    @Hidden
    private String apellido;

    @Hidden
    private String correo;

    @Hidden
    private String contrasenia;

    // Método validador para el campo cif
    @Hidden // La anotación @Hidden indica que este método no debe ser visible en la interfaz de usuario.
    @AssertTrue(message = "ERROR") // La anotación @AssertTrue valida que la expresión booleana en el método sea verdadera, de lo contrario, muestra el mensaje de error especificado.
    public boolean cifValidator() {
        // El método comprueba si el campo 'cif' no es nulo y tiene una longitud mayor que 1.
        // Devuelve true si la condición se cumple, indicando que el valor es válido.
        return cif != null && cif.length() > 1;
    }

    // Método validador para el campo nombre
    @Hidden
    @AssertTrue(message = "ERROR")
    public boolean nombreValidator() {
        // El nombre debe ser diferente de nulo y tener una longitud mayor que 1
        return nombre != null && nombre.length() > 1;
    }

    // Método validador para el campo apellido
    @Hidden
    @AssertTrue(message = "ERROR")
    public boolean apellidoValidator() {
        // El apellido debe ser diferente de nulo y tener una longitud mayor que 1
        return apellido != null && apellido.length() > 1;
    }

    // Método validador para el campo correo
    @Hidden
    @AssertTrue(message = "ERROR")
    public boolean correoValidator() {
        // El correo debe ser diferente de nulo y tener una longitud mayor que 1
        return correo != null && correo.length() > 1;
    }

    // Método validador para el campo contraseña
    @Hidden
    @AssertTrue(message = "ERROR")
    public boolean contraValidator() {
        // La contraseña debe ser diferente de nulo y tener una longitud mayor que 1
        return contrasenia != null && contrasenia.length() > 1;
    }
}
