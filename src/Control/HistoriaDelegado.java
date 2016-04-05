/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.dao.HistoriaDao;
import Modelo.vo.HistoriaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Capacitaciones
 */
public class HistoriaDelegado {
    private final JPanel contenedor;

    private final HistoriaDao historiaDao;

    public HistoriaDelegado(JPanel contenedor) {
        this.contenedor = contenedor;
        this.historiaDao = new HistoriaDao();
    }
    public void insertarHistoria (HistoriaVo historiaVo){
        try {
            this.historiaDao.insertar(historiaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
            
        }
       
    }
    public void editarHistoria(HistoriaVo historiaVo){
        try {
            this.historiaDao.editar(historiaVo);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
    }
    public List<HistoriaVo> consultarhistoria(){
        List<HistoriaVo> listaHistorias;
        try {
            listaHistorias = this.historiaDao.consultar();
        } catch (Exception e) {
            listaHistorias = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
        return listaHistorias;
    }
}


