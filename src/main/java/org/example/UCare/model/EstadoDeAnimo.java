package org.example.UCare.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class EstadoDeAnimo extends Ids {
    public enum ESTADO{Feliz,Bien,Cansado,Estresado,Triste};
    private ESTADO estado;
    private String comentario;
}
