package org.example.service;



import org.apache.logging.log4j.core.util.Assert;
import org.example.dao.EstudianteDAOH2;
import org.example.dao.IDao;
import org.example.model.Estudiante;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    private IDao<Estudiante> estudianteDaoH2 = new EstudianteDAOH2();
    private Service estudianteService = new Service(); // debería llamarse estudiate service


    @BeforeAll
    public void cargarEstudiantesSeteandoImplementacionDAO() {

        estudianteService.setEstudianteDAO(estudianteDaoH2);
        //Siguiendo el principio de polimorfismo el método buscar se comportará de acuerdo al comportamiento indicado en la clase EstudianteDaoEnMemoria
        estudianteService.guardar(new Estudiante("Estudiante", "Apellido"));

        //Conclusión el método guardar se comporta diferente de acuerdo al objeto que estamos referenciando en cada momento.

    }
    @Test
    public void buscarEstudianteDAO(){
        Estudiante estudiante= null;
        estudiante = estudianteService.buscarEstudiante(1L);
        assertEquals(estudiante.getNombre(),"Estudiante");
        assertEquals(estudiante.getApellido(),"Apellido");

    }

}