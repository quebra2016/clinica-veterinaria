/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.dao.MedicoDao;
import Modelo.vo.MedicoVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Capacitaciones
 */
public class MedicoDelegado {
    private final JPanel contenedor;
    private final MedicoDao medicoDao;

    public MedicoDelegado(JPanel contenedor, MedicoDao medicoDao) {
        this.contenedor = contenedor;
        this.medicoDao = medicoDao;
    }
    public void insertarMedico (MedicoVo medicoVo){
        try {
            this.medicoDao.insertar(medicoVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
            
        }
       
    }
    public void editarMedico(MedicoVo medicoVo){
        try {
            this.medicoDao.editar(medicoVo);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
    }
    public List<MedicoVo> consultarmedico(){
        List<MedicoVo> listaMedicos;
        try {
            listaMedicos = this.medicoDao.consultar();
        } catch (Exception e) {
            listaMedicos = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
        return listaMedicos;
    }
    public MedicoVo consultaMedico(int id){
        MedicoVo medicoVo;
        try {
            medicoVo = this.medicoDao.consultar(id);
        } catch (Exception e) {
            medicoVo = new MedicoVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
         return medicoVo;
    
    }
}


