package org.example.dao;

import org.example.model.Estudiante;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteDAOH2Test {

    private  static IDao<Estudiante> estudianteDao = new EstudianteDAOH2();

    @BeforeAll
    public static void cargarEstudiantes() {
        estudianteDao.guardar(new Estudiante( "Juan", "Perez"));
        estudianteDao.guardar(new Estudiante("Camila", "Rodriguez"));
        estudianteDao.guardar(new Estudiante("Luis", "Diaz"));
    }
    @Test
    public  void buscarEstudianteTest() {

        Estudiante estudiante = estudianteDao.buscar(1L);
        assertNotNull(estudiante);
        assertEquals(estudiante.getNombre(), "Juan");

    }

    @Test
    public  void traerTodosLosEstudiantesTest() {
        List<Estudiante> estudiantes = estudianteDao.buscarTodos();
        assertTrue(estudiantes.size() > 0);
    }

    @Test
    public void eliminarEstudiantesTest() {
        estudianteDao.eliminar(1L);
        assertEquals(estudianteDao.buscar(1L),null);

    }

}