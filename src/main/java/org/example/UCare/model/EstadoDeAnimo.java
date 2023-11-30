package org.example.UCare.model;

import lombok.Getter;
import lombok.Setter;
import org.example.UCare.calculators.CalcularEstudiante;
import org.example.UCare.calculators.FechaCalculator;
import org.example.UCare.filter.FilterEstudiante;
import org.openxava.annotations.*;
import org.openxava.calculators.CurrentDateCalculator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.AssertTrue;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@View(members = "estadoDeAnimo[" + " estado;" +" comentario;" + " fecha;" + " ];" + "Ucare{estudiantes}")
@Tab(properties = "estado,estudiantes.nombre",
        baseCondition = "${estudiantes.cif}=?",filter= FilterEstudiante.class)
public class EstadoDeAnimo extends Ids {
    public enum ESTADO{Feliz,Bien,Cansado,Estresado,Triste};
    @Required
    private ESTADO estado;
    @Column(length = 240)
    private String comentario;
    @DefaultValueCalculator(CurrentDateCalculator.class)
    @ReadOnly
    private Date fecha;
    @ManyToOne
    @Required
    @DefaultValueCalculator(value= CalcularEstudiante.class)
    private Estudiantes estudiantes;

    @Hidden
    @AssertTrue(message = "El campo solo acepta 240 caracteres!")
    public boolean iscomentariovalido() {
        return comentario != null && comentario.length() < 239;
    }
    @Hidden
    @AssertTrue(message = "Solo se puede registrar un estado de ánimo después de las 8 PM y antes de las 12 AM")
    public boolean isHoraValida() {
        // Obtener la hora actual
        Calendar cal = Calendar.getInstance();

        // Verificar si la hora está entre las 8 PM y las 12 AM
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        return hour >= 20 && hour < 24;
    }
}



