package org.example.UCare.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class L
{
    @Id
    private int numero;
}
