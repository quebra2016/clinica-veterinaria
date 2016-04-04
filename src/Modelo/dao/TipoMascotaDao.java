/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.dao;

import Modelo.conexion.Conexion;
import Modelo.vo.ClienteVo;
import Modelo.vo.TipoMascotaVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory;
import javax.xml.transform.Result;

/**
 *
 * @author Juan
 */
public class TipoMascotaDao extends Conexion implements GenericoDao<TipoMascotaVo>{

    @Override
    public void insertar(TipoMascotaVo object) {
        PreparedStatement sentencia = null;
        try {
            conectar();
         //Crear consulta de insercion
            String sql = "insert into tipo_mascota(nombre, estado) values(?, ?;)";
            sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, object.getNombre());
            sentencia.setBoolean(2, object.isEstado());
            //ejecutar la insercion
            sentencia.executeUpdate();
            //obtener la llave de registro del tipo mascota
            ResultSet rs= sentencia.getGeneratedKeys();
            if (rs.next()){
                object.setNombre(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
            desconectar();
        }
    }

    @Override
    public void editar(TipoMascotaVo object) {
          PreparedStatement sentencia;
          try {
            conectar();
            //crear el string del sql de la actualizcion
            String sql = "update tipo_mascota set id_tipo_mascota=?, nombre=?, estado=? where id_tipo_mascota=?";
            sentencia =cnn.prepareStatement(sql);
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, object.getIdTipoMascota());
            sentencia.setString(2, object.getNombre());
            sentencia.setBoolean(3, object.isEstado());
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
              desconectar();
          }
    }

    @Override
    public List<TipoMascotaVo> consultar() {
        PreparedStatement sentencia;
        List<TipoMascotaVo> lista= new ArrayList<>();
        try {
            conectar();
            //consulta de todos los registros de la tabla
            String sql = "select * from tipo_mascota";
            sentencia = cnn.prepareStatement(sql);
            //obtener los registros de la tabla.
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()){
                TipoMascotaVo tipo_mascota = new TipoMascotaVo();
                //obtener el id de la tipo_mascota el cursor
                //asignarlo el atributo idTipoMascota de un objeto de lña clase TipoMascotaVo
                tipo_mascota.setIdTipoMascota(rs.getInt("id_tipo_mascota"));
                tipo_mascota.setNombre(rs.getString("nombre"));
                tipo_mascota.setEstado(rs.getBoolean("estado"));
                lista.add(tipo_mascota);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
            desconectar();
        }
        return lista;
    }

    @Override
    public TipoMascotaVo consultar(int id) {
        PreparedStatement sentencia;
        TipoMascotaVo obj = new TipoMascotaVo();
        try {
            conectar();
            //consulta de un registro de la tabla segun la llave primaria
            String sql = "select * from tipo_mascota where id_tipo_mascota=?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
             //obtener los registros de la tabla.
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()){
                 //obtener el id de tipo mascota del cursor
                //asignarlo el atributo idtipomascota de un objeto de lña clase tipoMascota
            obj.setIdTipoMascota(rs.getInt("id_tipo_mascota"));
            obj.setNombre(rs.getString("nombre"));
            obj.setEstado(rs.getBoolean("estado"));
            
            }   
         
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
            desconectar();
        }
        return obj;
    }
    
}
