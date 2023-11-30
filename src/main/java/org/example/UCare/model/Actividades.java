// Paquete que contiene la clase Actividades
package org.example.UCare.model;

// Importaciones necesarias
import lombok.Getter;
import lombok.Setter;
import org.example.UCare.calculators.CalcularEstudiante;
import org.example.UCare.filter.FilterEstudiante;
import org.openxava.annotations.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;

// Clase que representa la entidad Actividades, que extiende la superclase Ids
@Getter
@Setter

// Anotación que indica que esta clase es una entidad JPA (Java Persistence API)
@Entity

// Anotación que configura la pestaña en la interfaz de usuario de OpenXava
@Tab(
        // Propiedades: Especifica las propiedades que se mostrarán en la pestaña y su orden.
        properties = "nombreDeActividad, fecha, realizado, estudiantes.nombre",

        // baseCondition: Define la condición base que se aplicará al recuperar datos para la pestaña.
        // En este caso, se utiliza una condición parametrizada que compara el valor de 'estudiantes.cif' con un parámetro sin nombrar.
        // La notación "${estudiantes.cif}=?" indica que se espera un parámetro para 'estudiantes.cif' en la consulta SQL subyacente.
        baseCondition = "${estudiantes.cif}=?",

        // filter: Especifica la clase del filtro a aplicar a la pestaña.
        filter = FilterEstudiante.class
)

// Anotación que configura la vista en la interfaz de usuario de OpenXava
@View(
        // Definición de los miembros que se mostrarán en la vista
        members = "nuevaActividad[nombreDeActividad, fecha, realizado];" + "Ucare{estudiantes}"
)

public class Actividades extends Ids {

    // Columna en la base de datos que almacena el nombre de la actividad
    @Column(length = 50)
    @Required
    private String nombreDeActividad;

    // Atributo que representa la fecha de la actividad
    @Required
    private LocalDate fecha;

    // Atributo que indica si la actividad ha sido realizada o no
    private boolean realizado;

    // Relación muchos a uno con la entidad Estudiantes
    @ManyToOne
    @Required
    // Especifica el calculador por defecto que se utilizará para inicializar este campo
    @DefaultValueCalculator(value = CalcularEstudiante.class)
    private Estudiantes estudiantes;

    // Método de validación para la longitud del nombre de la actividad
    @Hidden
    @AssertTrue(message = "El campo debe tener más de 3 caracteres")
    public boolean isnombreactlenghtvalida() {
        return nombreDeActividad != null && nombreDeActividad.length() > 3;
    }

    // Método de validación para la fecha de la actividad
    @Hidden
    @AssertTrue(message = "La fecha debe ser mayor o igual a la actual")
    public boolean isFechaMayorOIgual() {
        return fecha != null && (fecha.isAfter(LocalDate.now()) || fecha.isEqual(LocalDate.now()));
    }
}
