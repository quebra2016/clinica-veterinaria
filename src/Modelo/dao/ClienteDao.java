/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.dao;

import Modelo.conexion.Conexion;
import Modelo.vo.ClienteVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Capacitaciones
 */
public class ClienteDao extends Conexion implements GenericoDao<ClienteVo> {

    @Override
    public void insertar(ClienteVo object) {
        PreparedStatement sentencia = null;
        try {
            conectar();
            String sql = "insert into cliente(nombre, correo, telefono, estado) values(?,?,?,?)";
            sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, object.getNombre());
            sentencia.setString(2, object.getCorreo());
            sentencia.setString(3, object.getTelefono());
            sentencia.setBoolean(4, object.isEstado());
            //ejecutar la insercion
            sentencia.executeUpdate();
            //obtener la llave de registro d ela mascota
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                object.setIdCliente(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }
    }

    @Override
    public void editar(ClienteVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
            //crear el string del sql de la actualizcion
            String sql = "update cliente set id_cliente=?, nombre=?, correo=?, telefono=?, estado=? where id_cliente=?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, object.getIdCliente());
            sentencia.setString(2, object.getNombre());
            sentencia.setString(3, object.getCorreo());
            sentencia.setString(4, object.getTelefono());
            sentencia.setBoolean(5, object.isEstado());
            sentencia.setInt(6, object.getIdCliente());
            //ejecutar la sentencia
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }

    }

    @Override
    public List<ClienteVo> consultar() {
        PreparedStatement sentencia;
        List<ClienteVo> lista = new ArrayList<>();
        try {
            conectar();
            //consulta de todos los registros de la tabla
            String sql = "select * from cliente";
            sentencia = cnn.prepareStatement(sql);
            //obtener los registros de la tabla.
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                ClienteVo cliente = new ClienteVo();
                //obtener el id de la mascota del cursor
                //asih¡gnarlo el atributo idMascota de un objeto de lña clase MascotaVo
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEstado(rs.getBoolean("estado"));

                lista.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);

        } finally {
            desconectar();
        }
        return lista;
    }

    @Override
    public ClienteVo consultar(int id) {
        PreparedStatement sentencia;
        ClienteVo obj = new ClienteVo();
        try {
            conectar();
            //consulta de un registro de la tabla segun la llave primaria
            String sql = "select * from cliente where id_cliente = ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            //obtener los registros de la tabla.
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                //obtener el id de la mascota del cursor
                //asignarlo el atributo idCliente de un objeto de lña clase ClienteVo

                obj.setIdCliente(rs.getInt("id_cliente"));
                obj.setNombre(rs.getString("nombre"));
                obj.setCorreo(rs.getString("correo"));
                obj.setTelefono(rs.getString("telefono"));
                obj.setEstado(rs.getBoolean("estado"));

            }

        } catch (Exception e) {
            e.printStackTrace(System.err);

        } finally {
                
            desconectar();
        }
        return obj;
    }

}
