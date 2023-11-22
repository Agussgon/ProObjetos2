package org.example.dao;

import java.util.List;

public interface IDao <T>{
    // recibe el tipo de objeto como parametro

    public T guardar(T t);
    public void eliminar(Long id);
    public T buscar(Long id);
    public List<T> buscarTodos();




}
