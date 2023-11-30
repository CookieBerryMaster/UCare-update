package org.example.UCare.model;

import lombok.Getter;
import lombok.Setter;
import org.example.UCare.filter.FilterEstudiante;
import org.openxava.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
// La anotación @Tab especifica cómo se debe mostrar y filtrar la entidad en la interfaz de usuario.
@Tab(
        properties = "fechaHora, realizado, actividad.nombreDeActividad",
        baseCondition = "${actividad.estudiantes.cif}=?",
        filter = FilterEstudiante.class
)
// La anotación @View define la apariencia de la vista de la entidad en la interfaz de usuario.
@View(
        members = "nuevoRecordatorio[fechaHora, realizado, actividad];")
public class Recordatorios extends Ids {

    // La anotación @Required indica que el campo no puede ser nulo.
    @Required
    // La anotación @Stereotype especifica el tipo de dato y la presentación del campo en la interfaz de usuario.
    @Stereotype("DATETIME_SQL")
    private Timestamp fechaHora;

    private boolean realizado;

    // La anotación @ManyToOne indica una relación muchos a uno con la entidad Actividades.
    @ManyToOne
    @Required
    private Actividades actividad;

    // La anotación @Hidden oculta el campo en la interfaz de usuario.
    @Hidden
    // La anotación @AssertTrue realiza una validación personalizada.
    @AssertTrue(message = "La fecha y hora deben ser mayores o iguales a la actual")
    // Este método verifica si la fecha y hora son mayores o iguales a la fecha y hora actuales.
    public boolean isFechaHoraMayorOIgual() {
        return fechaHora != null && (fechaHora.toLocalDateTime().isAfter(LocalDateTime.now()) || fechaHora.toLocalDateTime().isEqual(LocalDateTime.now()));
    }
}
