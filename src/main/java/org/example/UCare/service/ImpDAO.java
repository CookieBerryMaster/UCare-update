// Paquete que contiene la clase ImpDAO
package org.example.UCare.service;

// Importaciones necesarias
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

// Implementación de la interfaz IDAO que proporciona operaciones CRUD (Create, Read, Update, Delete) utilizando JPA
public class ImpDAO implements IDAO {

    // Método para obtener todos los registros de una entidad utilizando una NamedQuery
    // y devolver una lista de objetos del tipo especificado
    @Override
    public <T> List<T> getAll(String namedQuery, Class<T> clazz) {
        // Obtener una instancia del EntityManager para interactuar con la persistencia
        EntityManager em = EntityManagerAdmin.getInstance();
        try {
            // Crear una consulta tipada utilizando la NamedQuery y el tipo de clase especificado
            TypedQuery<T> query = em.createNamedQuery(namedQuery, clazz);

            // Ejecutar la consulta y devolver la lista de resultados
            return query.getResultList();
        }
        catch(Exception e) {
            // Manejar cualquier excepción imprimiendo el rastreo de la pila y devolver null
            e.printStackTrace();
            return null;
        }
        finally {
            // Asegurarse de cerrar el EntityManager en la sección 'finally' para liberar recursos
            em.close();
        }
    }

    // Método para obtener registros de una entidad que cumplen ciertos parámetros utilizando una NamedQuery
    // y devolver una lista de objetos del tipo especificado
    @Override
    public <T> List<T> get(String namedQuery, Class<T> clazz, Object... param) {
        // Obtener una instancia del EntityManager para interactuar con la persistencia
        EntityManager em = EntityManagerAdmin.getInstance();
        try {
            // Crear una consulta tipada utilizando la NamedQuery y el tipo de clase especificado
            TypedQuery<T> query = em.createNamedQuery(namedQuery, clazz);

            // Establecer los parámetros de la consulta con los valores proporcionados
            int position = 1;
            for (Object obj : param){
                query.setParameter(position++, obj);
            }

            // Ejecutar la consulta y devolver la lista de resultados
            return query.getResultList();
        }
        catch(Exception e) {
            // Manejar cualquier excepción imprimiendo el rastreo de la pila y devolver null
            e.printStackTrace();
            return null;
        }
        finally {
            // Asegurarse de cerrar el EntityManager en la sección 'finally' para liberar recursos
            em.close();
        }
    }

    // Método para encontrar un registro por su ID
    @Override
    public <T> T findById(Class<T> clazz, Integer id) {
        // Obtener una instancia del EntityManager para gestionar las operaciones de persistencia
        EntityManager em = EntityManagerAdmin.getInstance();
        try {
            // Utilizar el método find de EntityManager para recuperar el registro por su ID
            T entity = em.find(clazz, id);
            return entity;
        }
        catch(Exception e) {
            // Imprimir detalles de la excepción en caso de error
            e.printStackTrace();
            return null;
        }
        finally {
            // Cerrar el EntityManager para liberar recursos
            em.close();
        }
    }

    // Método para eliminar un registro
    @Override
    public <T> void remove(T entity) {
        // Obtener una instancia del EntityManager para gestionar las operaciones de persistencia
        EntityManager em = EntityManagerAdmin.getInstance();
        try {
            // Iniciar una transacción
            em.getTransaction().begin();

            // Fusionar el objeto pasado como parámetro y luego eliminarlo de la base de datos
            em.remove(em.merge(entity));

            // Hacer un flush para sincronizar los cambios en la base de datos
            em.flush();

            // Confirmar la transacción
            em.getTransaction().commit();
        }
        catch(Exception e){
            // Imprimir detalles de la excepción en caso de error
            e.printStackTrace();

            // Deshacer la transacción en caso de error
            em.getTransaction().rollback();
        }
        finally {
            // Cerrar el EntityManager para liberar recursos
            em.close();
        }
    }

    // Método para crear un nuevo registro en la base de datos
    @Override
    public <T> void create(T entity) {
        // Obtener una instancia del EntityManager desde el administrador de entidades
        EntityManager em = EntityManagerAdmin.getInstance();
        try {
            // Iniciar una transacción
            em.getTransaction().begin();

            // Persistir la entidad en la base de datos
            em.persist(entity);

            // Sincronizar los cambios en la base de datos
            em.flush();

            // Confirmar la transacción
            em.getTransaction().commit();
        } catch (Exception e) {
            // En caso de excepción, imprimir el error y realizar un rollback de la transacción
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            // Cerrar el EntityManager para liberar recursos
            em.close();
        }
    }

    // Método para actualizar un registro existente en la base de datos
    @Override
    public <T> T update(T entity) {
        // Obtener una instancia del EntityManager desde el administrador de entidades
        EntityManager em = EntityManagerAdmin.getInstance();
        T entityUpdate = null;
        try {
            // Iniciar una transacción
            em.getTransaction().begin();

            // Fusionar la entidad con la versión persistente en la base de datos
            entityUpdate = em.merge(entity);

            // Sincronizar los cambios en la base de datos
            em.flush();

            // Confirmar la transacción
            em.getTransaction().commit();
        } catch (Exception e) {
            // En caso de excepción, imprimir el error y realizar un rollback de la transacción
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            // Cerrar el EntityManager para liberar recursos
            em.close();
        }
        // Devolver la entidad actualizada
        return entityUpdate;
    }
}