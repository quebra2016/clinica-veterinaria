/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.vo;

/**
 *
 * @author Capacitaciones
 */
public class TipoMascotaVo {
    private int idTipoMascota;
    private String nombre;
    private boolean estado;

    /**
     * @return the idTipoMascota
     */
    public int getIdTipoMascota() {
        return idTipoMascota;
    }

    /**
     * @param idTipoMascota the idTipoMascota to set
     */
    public void setIdTipoMascota(int idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Object getisEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
