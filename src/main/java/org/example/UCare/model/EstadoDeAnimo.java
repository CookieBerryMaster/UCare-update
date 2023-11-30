// Paquete y nombre de la clase para la organización del código
package org.example.UCare.model;

// Importación de las anotaciones Lombok para generar automáticamente getters y setters
import lombok.Getter;
import lombok.Setter;

// Importación de clases y utilidades necesarias
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

// Anotaciones para la generación automática de getters y setters, y marcando la clase como una entidad JPA
@Getter
@Setter
@Entity

// Anotación para definir la disposición de los campos en la vista y las pestañas
@View(members = "estadoDeAnimo[" + " estado;" + " comentario;" + " fecha;" + " ];" + "Ucare{estudiantes}")

// Anotación para definir propiedades de la tabla, como las columnas que se mostrarán y la condición base
@Tab(properties = "estado,estudiantes.nombre, comentario", baseCondition = "${estudiantes.cif}=?", filter= FilterEstudiante.class)
public class EstadoDeAnimo extends Ids {
    // Enumeración que representa los posibles estados de ánimo
    public enum ESTADO {Feliz, Bien, Cansado, Estresado, Triste};

    // Campo que representa el estado de ánimo, marcado como obligatorio
    @Required
    private ESTADO estado;

    // Campo que representa el comentario, con anotaciones para configurar su presentación
    @Stereotype("TEXT_AREA")
    @DisplaySize(10)
    @Column(length = 240)
    private String comentario;

    // Campo que representa la fecha, con un calculador automático para la fecha actual y marcado como de solo lectura
    @DefaultValueCalculator(CurrentDateCalculator.class)
    @ReadOnly
    private Date fecha;

    // Relación muchos a uno con la entidad Estudiantes, marcado como obligatorio con un calculador automático
    @ManyToOne
    @Required
    @DefaultValueCalculator(value= CalcularEstudiante.class)
    private Estudiantes estudiantes;

    // Método oculto para validar que el comentario no exceda los 240 caracteres
    @Hidden
    @AssertTrue(message = "El campo solo acepta 240 caracteres!")
    public boolean iscomentariovalido() {
        return comentario != null && comentario.length() < 239;
    }

    // Método oculto para validar que el estado de ánimo se registre después de las 8 PM y antes de las 12 AM
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
