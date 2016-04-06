/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.dao;

import Modelo.conexion.Conexion;
import Modelo.vo.HistoriaVo;
import Modelo.vo.MedicoVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class MedicoDao extends Conexion implements GenericoDao<MedicoVo>{

    @Override
    public void insertar(MedicoVo object) {
        PreparedStatement sentencia = null;
        try {
            conectar();
            String sql= "insert into medico(nombre, correo, documento, tarjeta_profesional, estado) values(?,?,?,?)";
            sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, object.getNombre());
            sentencia.setString(2, object.getCorreo());
            sentencia.setString(3, object.getDocumento());
            sentencia.setString(4, object.getTarjetaProfesional());
            sentencia.setBoolean(5, object.isEstado());
            //ejecutar la insercion
            sentencia.executeUpdate();
            //obtener la llave de registro de la medico
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()){
                object.setIdMedico(rs.getInt(1));              
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
            desconectar();
        }
    }

    @Override
    public void editar(MedicoVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
             //crear el string del sql de la actualizcion
            String sql = "update cliente set id_medico=?, correo=?, documento=?, tarjeta_profesional=?, estado=?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setString(1, object.getNombre());
            sentencia.setString(2, object.getCorreo());
            sentencia.setString(3, object.getDocumento());
            sentencia.setString(4, object.getTarjetaProfesional());
            sentencia.setBoolean(5, object.isEstado());
             //ejecutar la sentencia
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
            desconectar();
        }
    }

    @Override
    public List<MedicoVo> consultar() {
        PreparedStatement sentencia;
        List<MedicoVo> lista = new  ArrayList<>();
        try {
            conectar();
            
            String sql = "select * from medico";
            sentencia = cnn.prepareStatement(sql);
            
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()){
                MedicoVo medico= new MedicoVo();
                medico.setIdMedico(rs.getInt("id medico"));
                medico.setNombre(rs.getString("nombre"));
                medico.setCorreo(rs.getString("correo"));
                medico.setDocumento(rs.getString("documento"));
                medico.setTarjetaProfesional(rs.getString("tarjeta_profesional"));
                medico.setEstado(rs.getBoolean("estado"));
                
                lista.add(medico);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
            desconectar();
        }
        return lista;
    }

    @Override
    public MedicoVo consultar(int id) {
        PreparedStatement sentencia;
        MedicoVo obj = new MedicoVo();
        try {
            conectar();
            String sql = "select * from medico where id_medico";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet rs= sentencia.executeQuery();
            if (rs.next()){
                
                obj.setIdMedico(rs.getInt("id_cliente"));
                obj.setNombre(rs.getString("nombre"));
                obj.setCorreo(rs.getString("correo"));
                obj.setDocumento(rs.getString("documento"));
                obj.setTarjetaProfesional(rs.getString("tarjeta_profesional"));
                obj.setEstado(rs.getBoolean("estado"));
                
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
            desconectar();
        }
        return obj;
    }

    public void insertar(HistoriaVo historiaVo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void editar(HistoriaVo historiaVo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
