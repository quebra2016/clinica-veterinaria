/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.dao.ClienteDao;
import Modelo.vo.ClienteVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Juan
 */
public class ClienteDelegado {
    
   private final JPanel contenedor;
   private final ClienteDao clienteDao;

    public ClienteDelegado(JPanel contenedor) {
        this.contenedor = contenedor;
        this.clienteDao = new ClienteDao();
    }
   public void insertarCliente (ClienteVo clienteVo){
        try {
            this.clienteDao.insertar(clienteVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
       }  
        
   }
    public void editarMascota(ClienteVo clienteVo){
        try {
            this.clienteDao.editar(clienteVo);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
    }    
     public List<ClienteVo> consultarcliente(){
        List<ClienteVo> listaClientes;
        try {
            listaClientes = this.clienteDao.consultar();
        } catch (Exception e) {
            listaClientes = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
        return listaClientes;
    }
    public ClienteVo consultaCliente(int id){
        ClienteVo clienteVo;
        try {
            clienteVo = this.clienteDao.consultar(id);
        } catch (Exception e) {
            clienteVo = new ClienteVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "error en insercion", JOptionPane.ERROR_MESSAGE);
        }
         return clienteVo;
    
    }
}
 