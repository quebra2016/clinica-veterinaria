/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Capacitaciones
 */
public class Conexion {
    protected static Connection cnn;
    protected static void conectar (){
        try { 
        Class.forName("com.mysql.jdbc.Driver"); 
        cnn = DriverManager.getConnection("jdbc:mysql://192.168.7.200:3306/veterinaria", "root",""); 
                        
        } catch (Exception e){
       e.printStackTrace(System.err);
       }
    }
    
    protected static void desconectar (){
        try {
           cnn.close();  
        } catch (Exception e) {
            e.printStackTrace(System.err);
            
        }
            
     }         
}
