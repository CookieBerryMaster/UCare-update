// Paquete y nombre de la clase
package org.example.UCare.model;

// Importa las anotaciones de Lombok y algunas clases de Java
import lombok.Getter;
import lombok.Setter;
import org.example.UCare.filter.FilterEstudiante;
import org.openxava.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import java.sql.Timestamp; // Importa java.sql.Timestamp
import java.time.LocalDateTime;

// Anotaciones Lombok para generar automáticamente los métodos getter y setter
@Getter
@Setter

// La clase es una entidad JPA (Java Persistence API)
@Entity

// Anotación de OpenXava para especificar las propiedades de la pestaña en la interfaz de usuario
@Tab(properties = "fechaHora, actividad.nombreDeActividad, realizado",
        baseCondition = "${estudiantes.cif}=?",filter= FilterEstudiante.class)
public class Recordatorios extends Ids {

    // Atributo de fecha y hora con anotación de OpenXava para definir un campo obligatorio
    @Required
    @Stereotype("DATETIME_SQL")
    private Timestamp fechaHora;

    // Atributo booleano que indica si el recordatorio ha sido realizado
    private boolean realizado;

    // Relación Many-to-One con la entidad Actividades
    @ManyToOne
    // Anotación de OpenXava para especificar que este campo es obligatorio
    @Required
    private Actividades actividad;

    // Relacion many to one para estudiantes
    @ManyToOne
    @Required
    private Estudiantes estudiantes;

    // Método oculto que valida si la fecha y hora son mayores o iguales a la actual
    // Se utiliza la anotación @AssertTrue de Java Bean Validation
    // Este método será invocado durante la validación de la entidad
    @Hidden
    @AssertTrue(message = "La fecha y hora deben ser mayores o iguales a la actual")
    public boolean isFechaHoraMayorOIgual() {
        return fechaHora != null && (fechaHora.toLocalDateTime().isAfter(LocalDateTime.now()) || fechaHora.toLocalDateTime().isEqual(LocalDateTime.now()));
    }
}
