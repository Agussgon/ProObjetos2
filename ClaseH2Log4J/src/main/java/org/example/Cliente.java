package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cliente {



    private static Logger log= LogManager.getLogger( "prueba" );

    private Long id;
    private String nombre;
    private Double deuda;


    public Cliente(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // constructor para bd


    public Cliente(String nombre, Double deuda) {
        this.nombre = nombre;
        this.deuda = deuda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        log.info("se ha modificado el nombre "+ nombre);
        this.nombre = nombre;
    }

    public Double getDeuda() {
        return deuda;
    }

    public void setDeuda(Double deuda) {
        this.deuda = deuda;
    }
}
