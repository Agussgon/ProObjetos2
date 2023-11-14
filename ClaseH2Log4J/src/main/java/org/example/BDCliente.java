package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class BDCliente {

    static Logger log= LogManager.getLogger(BDCliente.class);

    public static void main(String[] args) {

        Connection con=null;


        try {
            Class.forName("org.h2.Driver").newInstance();
             con = DriverManager.getConnection("jdbc:h2:~/comercio", "usuario", "usuario");


            String createStmt = "DROP TABLE IF EXISTS CLIENTES; " +
                    "CREATE TABLE CLIENTES(ID INT PRIMARY KEY,NOMBRE VARCHAR(50),APELLIDO VARCHAR(50) );";
            PreparedStatement crearTabla = con.prepareStatement(createStmt);
            crearTabla.execute();

                // a diferencia de statement evitamos utilizar más de un plan de ejecución
                String insertarRegistros = "INSERT INTO CLIENTES VALUES(?, ?,?);";

                PreparedStatement insertarRegistro = con.prepareStatement(insertarRegistros);
                try {

                    insertarRegistro.setInt(1, 1);
                    insertarRegistro.setString(2, "Juan");
                    insertarRegistro.setString(3, "Perez");
                    insertarRegistro.execute();
                } catch (SQLException e) {
                }

                try {

                    insertarRegistro.setInt(1, 1);
                    insertarRegistro.setString(2, "Juana");
                    insertarRegistro.setString(3, "Perez");
                    insertarRegistro.execute();
                } catch (SQLException e) {
                }

                try {

                    insertarRegistro.setInt(1, 2);
                    insertarRegistro.setString(2, "Juana");
                    insertarRegistro.setString(3, "Perez");
                    insertarRegistro.execute();
                } catch (SQLException e) {
                }



                Integer IDInexistente= 3;

              try{  String eliminarCliente = " DELETE FROM CLIENTES WHERE ID=?";
                PreparedStatement eliminar=con.prepareStatement(eliminarCliente);
                eliminar.setInt(1,IDInexistente);
                eliminar.execute();
                log.info("Cliente con ID " + IDInexistente + " eliminado correctamente.");
              }
              catch (SQLException e){
                  e.printStackTrace();
                  log.error("Error al intentar eliminar el cliente con ID "+ IDInexistente);
              }


              try{

                  String modificarUsuario= "UPDATE CLIENTES SET NOMBRE=? WHERE APELLIDO=?; ";
                  PreparedStatement modificar= con.prepareStatement(modificarUsuario) ;
                  modificar.setString(1,"Florencia");
                  modificar.setString(2,"Pere");
                  modificar.executeUpdate(); // nos retorna las filas afectadas
                  // como implemento el debug
              }catch (SQLException e){
                  e.printStackTrace();
              }


                String consultaClientes = " SELECT * FROM CLIENTES";
                PreparedStatement consulta = con.prepareStatement(consultaClientes);
                ResultSet resultado = consulta.executeQuery();
                while (resultado.next()) {
                    System.out.println(" el id es " + resultado.getInt(1) +
                            "nombre y apellido " + resultado.getString(2) + " " + resultado.getString(3));
                }


            }
            catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            finally {
            // cerramos la conexion
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // ver el error en detalle
                }
            }



        }
    }