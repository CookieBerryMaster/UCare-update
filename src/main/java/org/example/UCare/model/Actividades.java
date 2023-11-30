package org.example.UCare.model;

import lombok.Getter;
import lombok.Setter;
import org.example.UCare.calculators.CalcularEstudiante;
import org.example.UCare.filter.FilterEstudiante;
import org.openxava.annotations.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Tab(properties = "nombreDeActividad,fecha,realizado,estudiantes.nombre",
      baseCondition = "${estudiantes.cif}=?",filter= FilterEstudiante.class)
@View(members = "nuevaActividad[nombreDeActividad, fecha, realizado];" + "Ucare{estudiantes}")
public class Actividades extends Ids{

    @Column(length = 50) @Required
    private String nombreDeActividad;
    @Required
    private LocalDate fecha;
    private boolean realizado;

    @ManyToOne
    @Required
    @DefaultValueCalculator(value= CalcularEstudiante.class)
    private Estudiantes estudiantes;

    @Hidden
    @AssertTrue(message = "El campo debe tener más de 3 caracteres")
    public boolean isnombreactlenghtvalida() {
        return nombreDeActividad != null && nombreDeActividad.length() > 3;
    }

    @Hidden
    @AssertTrue(message = "La fecha debe ser mayor o igual a la actual")
    public boolean isFechaMayorOIgual() {
        return fecha != null && (fecha.isAfter(LocalDate.now()) || fecha.isEqual(LocalDate.now()));
    }

}