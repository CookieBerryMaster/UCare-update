// Paquete que contiene la clase EstadoDeAnimo
package org.example.UCare.model;

// Importaciones necesarias
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
// Clase que representa la entidad EstadoDeAnimo, que extiende la superclase Ids
public class EstadoDeAnimo extends Ids {

    // Enumeración que define los posibles estados de ánimo
    public enum ESTADO {Feliz, Bien, Cansado, Estresado, Triste};

    // Atributo que representa el estado de ánimo, utilizando la enumeración ESTADO
    private ESTADO estado;

    // Atributo que representa un comentario asociado al estado de ánimo
    private String comentario;
}
