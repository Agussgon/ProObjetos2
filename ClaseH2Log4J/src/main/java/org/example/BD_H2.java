package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;



public class BD_H2 {
    private static final String DRIVER="org.h2.Driver";
    private static final String URL_BD="jdbc:h2:~/comercio";
    private static final String USER_BD="usuario";
    private static final String PASS_BD="usuario";

    //
    private static final String INSERT_CLIENTE ="INSERT INTO CLIENTES VALUES(?,?,?);";
    private static final String DELETE_CLIENTE ="DELETE FROM CLIENTES WHERE ID=? ;";
    private static final String UPDATE_CLIENTE ="UPDATE CLIENTES SET DEUDA=? WHERE NOMBRE=? ;";


    //
    private static Logger log= LogManager.getLogger("BD");

    public static void main(String[] args) throws SQLException {


        Cliente cliente1= new Cliente("Macarena Rinaldi",1500.75 );


        Connection con = null;


        try{
            Class.forName(DRIVER);
            con= DriverManager.getConnection(URL_BD,USER_BD,PASS_BD);
            String consulta="DROP TABLE IF EXISTS CLIENTES; " +
                    "CREATE TABLE CLIENTES(ID INT PRIMARY KEY, NOMBRE VARCHAR(50), DEUDA FLOAT ) ;";

            Statement stmt= con.createStatement();
            stmt.execute(consulta);
            //
            PreparedStatement pstm= con.prepareStatement(INSERT_CLIENTE)   ;
            pstm.setInt(1, 1 );
            pstm.setString(2, cliente1.getNombre());
            pstm.setDouble(3,cliente1.getDeuda());
            if(pstm.executeUpdate() > 0 ){
                System.out.println("el registro fue exitoso " + pstm);
            }

            PreparedStatement pstmUpdate2 = con.prepareStatement(UPDATE_CLIENTE);
            pstmUpdate2.setDouble(1, 3000.0);
            pstmUpdate2.setString(2, cliente1.getNombre());
            pstmUpdate2.execute(); // resultados

          // transacción

         try {

              con.setAutoCommit(false);
              //primera mdificacion
              PreparedStatement pstmUpdate = con.prepareStatement(UPDATE_CLIENTE);
              pstmUpdate.setDouble(1, 1000.0);
              pstmUpdate.setString(2, "Macarena Rinaldi"); // forma correcta -- en la bd son ''
              //pstmUpdate.setInt(2, 1); // pasamos un int en lugar de string
              int registroEncontrado= pstmUpdate.executeUpdate(); // se ejecuta
             log.info("si no surge un conflicto en las consultas siguientes la operacion debería realizarse "+
                     pstmUpdate);

             if( registroEncontrado < 1 ){
                 throw new RuntimeException("no se encontro un registro con ese nombre");
                 // generamos la e para registros no encontrados
             }

              //int excep= 4/0; // error

                //id repetido
                PreparedStatement pstmInsert= con.prepareStatement(INSERT_CLIENTE);
                pstmInsert.setInt(1,1);
                pstmInsert.setString(2,"Juana");
                pstmInsert.setDouble(3,200.0);
                pstmInsert.execute();

            con.commit();// si falla algo retorna a un estado anterior

            con.setAutoCommit(true);

        }catch ( SQLException |  RuntimeException e){
            con.rollback();
            log.error(e.getMessage());

        }




            String select= "SELECT * FROM CLIENTES; ";
            Statement stmtSelect= con.createStatement();
            ResultSet resultados= stmtSelect.executeQuery(select);

            while(resultados.next()){
                System.out.println("cliente con id"+ resultados.getInt(1)
                        +" tiene una deuda de "+ resultados.getDouble(3)  );
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            try{
                if(con != null){
                    con.close();}
            }
            catch (SQLException e){ e.printStackTrace(); }
        }
    }


/*
Podríamos generar una función por cada tipo de consulta
public static void main
 private static void insertarCliente(Connection connection, int id, String nombre, Double deuda) throws SQLException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENTE);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nombre);
            preparedStatement.setDouble(3,deuda)
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar cliente: " + e.getMessage());
        }
    }


* */

    /*
     * Si bien en algunos casos no es necesario un rollback() explícito después de una excepción,
     * es una buena práctica realizarlo para asegurarse de revertir explícitamente cualquier cambio no deseado en la base de datos
    importante delete y update solo retornan las filas modificadas... no resultados
     */

}
