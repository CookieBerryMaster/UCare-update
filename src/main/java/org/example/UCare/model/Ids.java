// Paquete que contiene la clase Ids
package org.example.UCare.model;

// Importaciones necesarias
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Hidden;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

// Clase base (superclase mapeada) para proporcionar un identificador común en otras entidades
@MappedSuperclass
@Getter
@Setter
public class Ids {

    @Id

    // Anotación que especifica la generación automática del valor del identificador
    @GeneratedValue(generator = "system-uuid")

    // Configuración del generador de identificadores
    @GenericGenerator(name = "system-uuid", strategy = "uuid")

    // Configuración de la columna asociada al identificador en la base de datos
    @Column(length = 32)

    // Anotación que indica que este campo debe estar oculto en la interfaz de usuario
    @Hidden
    private String id;
}
