/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.agencia;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author edreh
 */
public class ModeloUsuario {
    private DataSource source;

    public ModeloUsuario(DataSource source) {
        this.source = source;
    }
    public boolean validateUser(String user,String password)throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean valid = false;
        try{
            conn = source.getConnection();
            String query = "SELECT * from usuario WHERE us_nombre = ? AND us_password = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1,user);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if(rs.next()){
                valid = true;
            }
        }finally{
            try{if(rs != null)rs.close();}
            catch(SQLException ex){}
            try{if(ps != null)ps.close();}
            catch(SQLException ex){}
            try{if(conn != null)conn.close();}
            catch(SQLException ex){}
        }
        return valid;
    }
}
