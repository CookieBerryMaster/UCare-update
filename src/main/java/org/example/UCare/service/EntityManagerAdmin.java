package org.example.UCare.service;

import org.openxava.jpa.XPersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerAdmin {
    private static EntityManager instance;
    private static final String UNIDAD = "default";
    public static EntityManager getInstance() {
        EntityManager emf = XPersistence.getManager();
        if(emf == null){
            emf = XPersistence.createManager();
        }
        return emf;
    }

    private EntityManagerAdmin(){}
}
