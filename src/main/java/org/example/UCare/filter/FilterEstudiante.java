// Paquete que contiene la clase FilterEstudiante
package org.example.UCare.filter;

// Importaciones necesarias
import org.example.UCare.util.Global;
import org.openxava.filters.BaseContextFilter;
import org.openxava.filters.FilterException;

// Clase que implementa la interfaz BaseContextFilter de OpenXava para personalizar el filtro en una pestaña
public class FilterEstudiante extends BaseContextFilter {

    // Método override que aplica la lógica de filtrado
    @Override
    public Object filter(Object o) throws FilterException {
        // Obtener el valor del nombre de usuario desde una clase utilitaria Global
        String cif = Global.userName;

        // Devolver un array de objetos que representa el filtro a aplicar en la consulta SQL subyacente
        // En este caso, se filtra por el valor del nombre de usuario ('cif') obtenido anteriormente
        return new Object[] { cif };
    }
}
