/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.dao.TipoMascotaDao;
import Modelo.vo.TipoMascotaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Capacitaciones
 */
public class TipoMascotaDelegado {
    private final JPanel contenedor;
    private final TipoMascotaDao tipomascotaDao;

    public TipoMascotaDelegado(JPanel contenedor) {
        this.contenedor = contenedor;
        this.tipomascotaDao = new TipoMascotaDao();
    }
    
    public void insertarTipoMascota (TipoMascotaVo tipomascotaVo){
        try {
            this.tipomascotaDao.insertar(tipomascotaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
            
        }
       
    }
    public void editarTipoMascota(TipoMascotaVo tipomascotaVo){
        try {
            this.tipomascotaDao.editar(tipomascotaVo);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
    }
    public List<TipoMascotaVo> consultartipomascota(){
        List<TipoMascotaVo> listaTipoMascotas;
        try {
            listaTipoMascotas = this.tipomascotaDao.consultar();
        } catch (Exception e) {
            listaTipoMascotas = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
        return listaTipoMascotas;
    }
    public TipoMascotaVo consultaTipoMascota(int id){
        TipoMascotaVo tipomascotaVo;
        try {
            tipomascotaVo = this.tipomascotaDao.consultar(id);
        } catch (Exception e) {
            tipomascotaVo = new TipoMascotaVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
         return tipomascotaVo;
    
    }
}


