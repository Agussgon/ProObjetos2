package org.example.dao;

import org.example.model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOH2 implements IDao<Estudiante> {

    private static final String Driver_JDBC="org.h2.Driver";
    private static final String URL_Driver="jdbc:h2:~/estudiantes";
    private static final String User_Driver="usuario";
    private static final String Pass_Driver="usuario";




    @Override
    public Estudiante guardar(Estudiante estudiante) {
        try{
        Class.forName(Driver_JDBC);
        Connection con= DriverManager.getConnection(URL_Driver,User_Driver,Pass_Driver);

            String insert= "INSERT INTO estudiantes(NOMBRE, APELLIDO)VALUES(?,?) ; ";
        PreparedStatement pstm= con.prepareStatement(insert);
        // pstm.setLong(1,estudiante.getId());
        pstm.setString(1,estudiante.getNombre());
        pstm.setString(2, estudiante.getApellido());
        pstm.executeUpdate();
        pstm.close();

        // el id es auto incremental en la tabla   id INT AUTO_INCREMENT PRIMARY KEY,
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        try{
            Class.forName(Driver_JDBC);
            Connection con= DriverManager.getConnection(URL_Driver,User_Driver,Pass_Driver);
            String delete= "DELETE FROM estudiantes WHERE ID=?";
            PreparedStatement pstm= con.prepareStatement(delete);
            pstm.setLong(1,id);
            pstm.executeUpdate();
            pstm.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Estudiante buscar(Long id) {
        Estudiante estudiante=null;
        try{
            Class.forName(Driver_JDBC);
            Connection con= DriverManager.getConnection(URL_Driver,User_Driver,Pass_Driver);
            String buscar= "SELECT * FROM estudiantes WHERE ID=?";
            PreparedStatement pstm= con.prepareStatement(buscar);
            ResultSet result= pstm.executeQuery();
            while(result.next()){
                Long id2=result.getLong(1);
                String nombre= result.getString(2);
                String apellido= result.getString(3);
                estudiante= new Estudiante(nombre,apellido);
                estudiante.setId(id2);

            }

            pstm.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return estudiante;
    }

    @Override
    public List<Estudiante> buscarTodos() {
        List <Estudiante> estudiantes = new ArrayList<>();

        try{
            Class.forName(Driver_JDBC);
            Connection con= DriverManager.getConnection(URL_Driver,User_Driver,Pass_Driver);
            String buscar= "SELECT * FROM estudiantes ;";
            PreparedStatement pstm= con.prepareStatement(buscar);
            ResultSet result= pstm.executeQuery();
            while(result.next()){
                Long id=result.getLong(1);
                String nombre= result.getString(2);
                String apellido= result.getString(3);
                Estudiante estudiante= new Estudiante(nombre,apellido);
                estudiante.setId(id);
                estudiantes.add(estudiante);

            }

            pstm.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return estudiantes;

    }

}
