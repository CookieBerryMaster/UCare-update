// Paquete que contiene la clase CalcularEstudiante
package org.example.UCare.calculators;

// Importaciones necesarias
import org.example.UCare.model.Estudiantes;
import org.example.UCare.util.Global;
import org.openxava.calculators.ICalculator;
import static org.openxava.jpa.XPersistence.getManager;

// Clase que implementa la interfaz ICalculator de OpenXava para calcular el objeto Estudiantes
public class CalcularEstudiante implements ICalculator {

    // Método calculate() implementado de la interfaz ICalculator
    @Override
    public Object calculate() throws Exception {
        // Obtener el EntityManager utilizando XPersistence
        final Estudiantes estudiante = getManager()
                // Buscar un objeto Estudiantes por su identificador (nombre de usuario)
                .find(Estudiantes.class, Global.userName);
        // Devolver el objeto Estudiantes encontrado
        return estudiante;
    }
}
