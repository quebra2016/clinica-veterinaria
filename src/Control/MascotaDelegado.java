/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.dao.MascotaDao;
import Modelo.vo.MascotaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Capacitaciones
 */
public class MascotaDelegado {

    private final JFrame contenedor;

    private final MascotaDao mascotaDao;

    public MascotaDelegado(JFrame contenedor) {
        this.contenedor = contenedor;
        this.mascotaDao = new MascotaDao();
    }
    public void insertarMascota (MascotaVo mascotaVo){
        try {
            this.mascotaDao.insertar(mascotaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
            
        }
       
    }
    public void editarMascota(MascotaVo mascotaVo){
        try {
            this.mascotaDao.editar(mascotaVo);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
    }
    public List<MascotaVo> consultarmascota(){
        List<MascotaVo> listaMacotas;
        try {
            listaMacotas = this.mascotaDao.consultar();
        } catch (Exception e) {
            listaMacotas = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
        return listaMacotas;
    }
    public MascotaVo consultaMascota(int id){
        MascotaVo mascotaVo;
        try {
            mascotaVo = this.mascotaDao.consultar(id);
        } catch (Exception e) {
            mascotaVo = new MascotaVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
         return mascotaVo;
    
    }
}
