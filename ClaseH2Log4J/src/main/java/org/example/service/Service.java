package org.example.service;

import org.example.dao.IDao;
import org.example.model.Estudiante;

import java.util.List;

public class Service {
    private IDao<Estudiante> estudianteDAO;

    public IDao<Estudiante> getEstudianteDAO() {
        return estudianteDAO;
    }

    public void setEstudianteDAO(IDao<Estudiante> estudianteDAO) {
        this.estudianteDAO = estudianteDAO;
    }
    public Estudiante guardar(Estudiante e){
        // la responsabilidad es del DAO
        return estudianteDAO.guardar(e);

    }
    public void eliminar(Long id){
        estudianteDAO.eliminar(id);
    }

    public Estudiante buscarEstudiante(Long id){
        return estudianteDAO.buscar(id);
    }
    public List<Estudiante> buscarTodos(){
        return estudianteDAO.buscarTodos();
    }
}
