/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.dao;

import java.util.List;

/**
 *
 * @author Capacitaciones
 */
public interface GenericoDao <T> {
    public void insertar(T o);
    public void editar(T object);
    public List<T> consultar();
    public T consultar (int id);
}
