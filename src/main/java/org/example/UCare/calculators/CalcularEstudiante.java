package org.example.UCare.calculators;

import org.example.UCare.model.Estudiantes;
import org.example.UCare.util.Global;
import org.openxava.calculators.ICalculator;
import static org.openxava.jpa.XPersistence.getManager;


public class CalcularEstudiante implements ICalculator {
    @Override
    public Object calculate() throws Exception {
        final Estudiantes estudiante = getManager() // getManager() de XPersistence
                .find(Estudiantes.class, Global.userName); // Busca el producto
        return estudiante;

    }
}
