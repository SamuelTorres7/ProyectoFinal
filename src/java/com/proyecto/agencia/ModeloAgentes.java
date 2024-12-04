/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.agencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author edreh
 */
public class ModeloAgentes {
    private DataSource source;

    public ModeloAgentes(DataSource source) {
        this.source = source;
    }
    public Agente getAgente(int id)throws SQLException{
        Agente a = new Agente();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = source.getConnection();
            String query = "SELECT * from agente WHERE ag_id=?;";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                a.setId(rs.getInt("ag_id"));
                a.setNombre(rs.getString("ag_nombre"));
                a.setApellido(rs.getString("ag_apellido"));
                a.setTel(rs.getString("ag_tel"));
                a.setCp(rs.getInt("ag_cp"));
            }
        }finally{
            try{if(rs != null)rs.close();}
            catch(SQLException ex){}
            try{if(ps != null)ps.close();}
            catch(SQLException ex){}
            try{if(conn != null)conn.close();}
            catch(SQLException ex){}
        }
        return a;
    }
    public ArrayList<Agente> getAgentes()throws SQLException{
        ArrayList<Agente> agentes = new ArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = source.getConnection();
            String query = "SELECT * from agente;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Agente c = new Agente();
                c.setId(rs.getInt("ag_id"));
                c.setNombre(rs.getString("ag_nombre"));
                c.setApellido(rs.getString("ag_apellido"));
                c.setTel(rs.getString("ag_tel"));
                c.setCp(rs.getInt("ag_cp"));
                agentes.add(c);
            }
        }finally{
            try{if(rs != null)rs.close();}
            catch(SQLException ex){}
            try{if(ps != null)ps.close();}
            catch(SQLException ex){}
            try{if(conn != null)conn.close();}
            catch(SQLException ex){}
        }
        return agentes;
    }
}
