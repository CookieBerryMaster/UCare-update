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
@Tab(
        properties = "fechaHora, realizado, actividad.nombreDeActividad",
        baseCondition = "${actividad.estudiantes.cif}=?",
        filter = FilterEstudiante.class
)
@View(
        members = "nuevoRecordatorio[fechaHora, realizado, actividad];")
public class Recordatorios extends Ids {

    @Required
    @Stereotype("DATETIME_SQL")
    private Timestamp fechaHora;

    private boolean realizado;

    @ManyToOne
    @Required
    private Actividades actividad;

    @Hidden
    @AssertTrue(message = "La fecha y hora deben ser mayores o iguales a la actual")
    public boolean isFechaHoraMayorOIgual() {
        return fechaHora != null && (fechaHora.toLocalDateTime().isAfter(LocalDateTime.now()) || fechaHora.toLocalDateTime().isEqual(LocalDateTime.now()));
    }
}
