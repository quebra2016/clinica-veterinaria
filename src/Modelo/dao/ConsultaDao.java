/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.dao;

import Modelo.conexion.Conexion;
import Modelo.vo.ConsultaVo;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Result;

/**
 *
 * @author Juan
 */
public class ConsultaDao extends Conexion implements GenericoDao<ConsultaVo>{

    @Override
    public void insertar(ConsultaVo object) {
        PreparedStatement sentencia = null;
        try {
            conectar();
            String sql = "insert into consulta (fecha, motivo, descripcion, estado, id_medico, id_historia) values(?, ?, ?, ?, ?, ?)";
            sentencia = (PreparedStatement) cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            sentencia.setDate(1, object.getFecha());
            sentencia.setString(2, object.getMotivo());
            sentencia.setString(3, object.getDescripcion());
            sentencia.setString(4, object.getEstado());
            sentencia.setInt(5, object.getIdMedico());
            sentencia.setInt(6, object.getIdHistoria());
            
             sentencia.executeUpdate();
             
             ResultSet  rs = sentencia.getGeneratedKeys();
             if (rs.next()) {
                 object.setIdConsulta(rs.getInt(1));
            }
             
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
            desconectar();
        }
    }

    @Override
    public void editar(ConsultaVo object) {
       PreparedStatement sentencia;
        try {
            conectar();
            String sql ="update consulta set id_consulta=?, fecha=?, motivo=?, descripcion=?, estado=?, id_medico=?, id_historia=?, where id_consulta=?";
            sentencia = (PreparedStatement) cnn.prepareStatement(sql);
            sentencia.setInt(1, object.getIdConsulta());
            sentencia.setDate(2, object.getFecha());
            sentencia.setString(3, object.getMotivo());
            sentencia.setString(4, object.getDescripcion());
            sentencia.setString(5, object.getEstado());
            sentencia.setInt(6, object.getIdMedico());
            sentencia.setInt(7, object.getIdHistoria());
            sentencia.setInt(8, object.getIdConsulta());
            
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
            desconectar();
        }
    }

    @Override
    public List<ConsultaVo> consultar() {
        PreparedStatement sentencia;
        List<ConsultaVo> lista= new ArrayList<>();
        try {
            conectar();
            String sql = "select * from Cliente";
            sentencia = (PreparedStatement) cnn.prepareStatement(sql);
            
            ResultSet rs= sentencia.executeQuery();
            
            while (rs.next()) {
                ConsultaVo consulta = new ConsultaVo();
                consulta.setIdConsulta(rs.getInt("id_consulta"));
                consulta.setFecha(rs.getDate("fecha"));
                consulta.setMotivo(rs.getString("motivo"));
                consulta.setDescripcion(rs.getString("descripcion"));
                consulta.setEstado(rs.getString("estado"));
                consulta.setIdMedico(rs.getInt("id_medico"));
                consulta.setIdHistoria(rs.getInt("id_historia"));
                lista.add(consulta);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally{
        }
        return lista;
    }

    @Override
    public ConsultaVo consultar(int id) {
        PreparedStatement sentencia;
        ConsultaVo obj= new ConsultaVo();
        try {
            conectar();
            String sql = "select * from consulta where id_consulta = ?";
            sentencia = (PreparedStatement) cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            
            ResultSet rs= sentencia.executeQuery();
            if (rs.next());
            
            obj.setIdConsulta(rs.getInt("id_consulta"));
            obj.setFecha(rs.getDate("fecha"));
            obj.setMotivo(rs.getString("motivo"));
            obj.setDescripcion(rs.getString("descripcion"));
            obj.setEstado(rs.getString("estado"));
            obj.setIdMedico(rs.getInt("id_medico"));
            obj.setIdHistoria(rs.getInt("id_historia"));
            
            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
            desconectar();
        }
        return obj;
    }
    
}
