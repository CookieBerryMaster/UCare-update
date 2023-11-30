// Paquete que contiene la interfaz IDAO
package org.example.UCare.service;

// Importación de la lista de Java util para usar List
import java.util.List;

// Interfaz que define las operaciones básicas de un DAO (Data Access Object)
public interface IDAO {

    // Método genérico para obtener todos los registros de una entidad mediante una consulta nombrada
    <T> List<T> getAll(String namedQuery, Class<T> clazz);

    // Método genérico para obtener registros de una entidad mediante una consulta nombrada y parámetros
    <T> List<T> get(String namedQuery, Class<T> clazz, Object... param);

    // Método genérico para encontrar un registro por su identificador único
    <T> T findById(Class<T> clazz, Integer id);

    // Método genérico para eliminar un registro de la base de datos
    <T> void remove(T entity);

    // Método genérico para crear un nuevo registro en la base de datos
    <T> void create(T entity);

    // Método genérico para actualizar un registro existente en la base de datos
    <T> T update(T entity);
}