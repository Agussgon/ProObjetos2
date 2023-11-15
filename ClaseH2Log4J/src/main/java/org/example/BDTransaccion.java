package org.example;

import java.sql.*;

public class BDTransaccion {

    //
    private static final String URL_Driver="jdbc:h2:~/comercio";
    private static final String User_Driver="usuario";
    private static final String Pass_Driver="usuario";

    //
    private static final String SQL_CREATE= "DROP TABLE IF EXISTS CLIENTES; "+
            "CREATE TABLE CLIENTES(ID INT PRIMARY KEY, NOMBRE VARCHAR(50);";
    private static final String SQL_INSERT="INSERT INTO CLIENTES VALUES(?,?)";
    private static final String SQL_UPDATE="UPDATE CLIENTES SET NOMBRE=? WHERE ID=? ";
    private static final String SQL_DELETE="DELETE FROM CLIENTES WHERE ID=?";


    public static void main(String[] args) throws Exception {

        Cliente cliente= new Cliente("Juan Perez");

        Connection con= null;

        try{

            con= getConnection();

            //
            PreparedStatement pstm= con.prepareStatement(SQL_CREATE);
            pstm.execute();

            //
            PreparedStatement pstInsert=con.prepareStatement(SQL_INSERT);
            pstm.setInt(1,1);
            pstm.setString(2,cliente.getNombre());
            pstm.execute();


            //Empezamos la transacción por lo cual inhabilitamos el commit
            con.setAutoCommit(false);

            PreparedStatement pstUpdate=con.prepareStatement(SQL_UPDATE);
            pstUpdate.setString(2,"Otro nombre");
            pstUpdate.setInt(2,1);
            pstUpdate.execute();

            // error que evita la transacción
            int a= 2/0 ;

            con.commit();

            con.setAutoCommit(true);


            String result_sql="SELECT * FROM CLIENTES;" ;

            PreparedStatement pstResult= con.prepareStatement(result_sql);
            ResultSet result=  pstResult.executeQuery() ;
            while (result.next()){
                System.out.println( result.getInt(1)+ " " + result.getString(2));
            }




        }catch (Exception e) {
            e.printStackTrace();
            // si falla algo tiene que deshacer los cambios realizados
            con.rollback();
        }  finally {
            con.close();
        }

    }


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(URL_Driver,User_Driver,Pass_Driver);

    }



}
