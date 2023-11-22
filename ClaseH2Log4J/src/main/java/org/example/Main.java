package org.example;

import org.example.dao.EstudianteDAOH2;
import org.example.model.Estudiante;
import org.example.service.Service;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Estudiante estudiante= new Estudiante("Juan","Perez");
        estudiante.setId(1L);

        Service service= new Service();
        // seteamos una estrategia de persistencia
        service.setEstudianteDAO(new EstudianteDAOH2());

        service.guardar(estudiante);









    }




}