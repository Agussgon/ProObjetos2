package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cliente {



    private static Logger log= LogManager.getLogger( "prueba" );

    private Long id;
    private String nombre;


    public Cliente(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // constructor para bd


    public Cliente(String nombre) {
        this.nombre = nombre;
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
    /*
     * En Java, long es un tipo de dato primitivo que se utiliza para representar
     * números enteros de 64 bits con signo. El tipo de dato long tiene un rango más amplio
     *  en comparación con el tipo de dato int, que es de 32 bits. El rango de valores posibles
     * para un long va desde -9,223,372,036,854,775,808 hasta 9,223,372,036,854,775,807.
     * */

}
