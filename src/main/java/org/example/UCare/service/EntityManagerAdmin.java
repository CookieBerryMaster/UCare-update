// Paquete que contiene la clase EntityManagerAdmin
package org.example.UCare.service;

// Importaciones necesarias
import org.openxava.jpa.XPersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// Clase que administra la creación y obtención de instancias de EntityManager
public class EntityManagerAdmin {

    // Instancia única de EntityManager compartida por la aplicación
    private static EntityManager instance;

    // Nombre de la unidad de persistencia utilizada (en este caso, se llama "default")
    private static final String UNIDAD = "default";

    // Método estático para obtener la instancia de EntityManager
    public static EntityManager getInstance() {
        // Intentar obtener un EntityManager existente
        EntityManager em = XPersistence.getManager();

        // Si no hay un EntityManager existente, crear uno nuevo
        if (em == null) {
            em = XPersistence.createManager();
        }

        // Devolver la instancia de EntityManager (puede ser la existente o la recién creada)
        return em;
    }

    // Constructor privado para evitar la creación de instancias adicionales de la clase
    private EntityManagerAdmin() {}
}
