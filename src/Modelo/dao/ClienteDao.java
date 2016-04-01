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
            String sql = "insert into cliente(nombre, edad, estado, id_tipo_mascota, id_cliente) values(?,?,?,?,?)";
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
            conectar ();
            //crear el string del sql de la actualizcion
            String sql ="update mascota set id_cliente=?, nombre=?, edad=?, estado=?, id_tipo_mascota=?, id_tipo_cliente=?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, object.getIdMascota());
            sentencia.setString(2, object.getNombre());
            sentencia.setInt(3, object.getEdad());
            sentencia.setBoolean(4, object.isEstado());
            sentencia.setInt(5, object.getIdMascota());
            sentencia.setInt(6, object.getIdCliente());
            sentencia.setInt(7, object.getIdMascota());
            //ejecutar la sentencia
            sentencia.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace(System.err);
        }finally {
            desconectar ();
        }
              
    }

    @Override
    public List<MascotaVo> consultar() {
        PreparedStatement sentencia;
        List<MascotaVo> lista= new ArrayList<>();
        try {
            conectar();
         //consulta de todos los registros de la tabla
            String sql ="select * from mascota";
            sentencia = cnn.prepareStatement(sql);
            //obtener los registros de la tabla.
            ResultSet rs=sentencia.executeQuery();
            while (rs.next()) {                
                MascotaVo mascota= new MascotaVo();
                //obtener el id de la mascota del cursor
                //asih¡gnarlo el atributo idMascota de un objeto de lña clase MascotaVo
                mascota.setIdMascota (rs.getInt("id_mascota"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setEstado(rs.getBoolean("estado"));
                mascota.setIdTipoMascota(rs.getInt("id_tipo_mascota"));
                mascota.setIdCliente(rs.getInt("id_cliente"));
                lista.add(mascota);  
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
       
        }finally
        {
        
        }
        return lista;
    }

    }

    @Override
    public List<ClienteVo> consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClienteVo consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
