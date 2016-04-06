/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.dao;

import Modelo.conexion.Conexion;
import Modelo.vo.HistoriaVo;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Capacitaciones
 */
public class HistoriaDao extends Conexion implements  GenericoDao<HistoriaVo>{

    public HistoriaDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar(HistoriaVo object) {
        PreparedStatement sentencia = null;
        try {
            conectar ();
            String sql= "insert into historia (fecha_apertura, fecha_cierre, estado, id_mascota) values(?, ?, ?, ?; ";
            sentencia = (PreparedStatement) cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            sentencia.setDate(1, object.getFechaApertura());
            sentencia.setDate(2, object.getFechaCierre());
            sentencia.setBoolean(3, object.isEstado());
            sentencia.setInt(4, object.getIdMascota());
            //ejecutar la insercion
            sentencia.executeUpdate();
            //obtener la llave de registro d ela mascota
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()){
                object.setIdMascota(rs.getInt(1));
            }
        } catch (Exception e) {
         e.printStackTrace(System.err);
        }finally{
            desconectar();
        }
        
    }

    @Override
    public void editar(HistoriaVo object) {
           //una consulta de base de datos empiza por update
        PreparedStatement sentencia;
        try {
            conectar();
            //crear el string del sql de la actualizcion
            String sql ="update historia set id_historia=?, fecha_apertura=?, fecha_cierre=?, estado=?, id_mascota=?, where id_historia=?";
            sentencia = (PreparedStatement) cnn.prepareStatement(sql);
            sentencia.setInt(1, object.getIdHistoria());
            sentencia.setDate(2, object.getFechaApertura());
            sentencia.setDate(3, object.getFechaCierre());
            sentencia.setBoolean(4, object.isEstado());
            sentencia.setInt(5, object.getIdMascota());
            sentencia.setInt(6, object.getIdHistoria());
            //ejecutar la sentencia
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
            desconectar();
        }
    }

    @Override
    public List<HistoriaVo> consultar() {
       PreparedStatement sentencia;
       List<HistoriaVo> lista= new ArrayList<>();
        try {
            conectar();
          //consulta de todos los registros de la tabla
            String sql ="select * from Historia";
            sentencia = (PreparedStatement) cnn.prepareStatement(sql);
            //obtener los registros de la tabla.
            ResultSet rs=sentencia.executeQuery();
            while (rs.next()){
                HistoriaVo historia = new HistoriaVo();
                //obtener el id de la histotria del cursor
                //asignarlo el atributo idHistoria de un objeto de lña clase historiaVo
                historia.setIdHistoria(rs.getInt("id_historia"));
                historia.setFechaApertura(rs.getDate("fecha_apertura"));
                historia.setFechaCierre(rs.getDate("fecha_cierre"));
                historia.setEstado(rs.getBoolean("estado"));
                historia.setIdMascota(rs.getInt("id_mascota"));
                lista.add(historia);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally
        {
        }
             return lista;
    }

    @Override
    public HistoriaVo consultar(int id) {
        PreparedStatement sentencia;
        HistoriaVo obj= new HistoriaVo();
        try {
            conectar();
            //consulta de un registro de la tabla segun la llave primaria
            String sql = "select *from historia where id_historia =?";
            sentencia = (PreparedStatement) cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            //obtener los registros de la tabla.
            ResultSet rs=sentencia.executeQuery();
            if (rs.next()){
             //obtener el id de la historia del cursor
                //asignarlo el atributo idMascota de un objeto de lña clase HistoriaVo
             obj.setIdHistoria(rs.getInt("id_historia"));
             obj.setFechaApertura(rs.getDate("fecha_apertura"));
             obj.setFechaCierre(rs.getDate("fecha_cierre"));
             obj.setEstado(rs.getBoolean("estado"));
             obj.setIdMascota(rs.getInt("id_mascota"));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);           
        }finally
        {
            return obj;
        }
        
    }
    
}
