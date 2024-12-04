/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.agencia;

import java.util.ArrayList;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author edreh
 */
public class ModeloClientes {
    private DataSource source;

    public ModeloClientes(DataSource source) {
        this.source = source;
    }
    
    public ArrayList<Cliente> getClientes()throws SQLException{
        ArrayList<Cliente> clientes = new ArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = source.getConnection();
            String query = "SELECT * from cliente,agente"+
                    " WHERE cliente.c_agente = agente.ag_id;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt("c_id"));
                c.setNombre(rs.getString("c_nombre"));
                c.setApellido(rs.getString("c_apellido"));
                c.setTel(rs.getString("c_tel"));
                Agente a = new Agente();
                a.setId(rs.getInt("agente.ag_id"));
                a.setNombre(rs.getString("agente.ag_nombre"));
                a.setApellido(rs.getString("agente.ag_apellido"));
                a.setTel(rs.getString("agente.ag_tel"));
                a.setCp(rs.getInt("agente.ag_cp"));
                c.setAgente(a);
                clientes.add(c);
            }
        }finally{
            try{if(rs != null)rs.close();}
            catch(SQLException ex){}
            try{if(ps != null)ps.close();}
            catch(SQLException ex){}
            try{if(conn != null)conn.close();}
            catch(SQLException ex){}
        }
        return clientes;
    }
    public Cliente getCliente(int id)throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cliente c = new Cliente();
        try{
            conn = source.getConnection();
            String query = "SELECT * from cliente,agente"+
                    " WHERE cliente.c_id=? AND cliente.c_agente = agente.ag_id;";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                c.setId(rs.getInt("c_id"));
                c.setNombre(rs.getString("c_nombre"));
                c.setApellido(rs.getString("c_apellido"));
                c.setTel(rs.getString("c_tel"));
                Agente a = new Agente();
                a.setId(rs.getInt("agente.ag_id"));
                a.setNombre(rs.getString("agente.ag_nombre"));
                a.setApellido(rs.getString("agente.ag_apellido"));
                a.setTel(rs.getString("agente.ag_tel"));
                a.setCp(rs.getInt("agente.ag_cp"));
                c.setAgente(a);
            }
        }finally{
            try{if(rs != null)rs.close();}
            catch(SQLException ex){}
            try{if(ps != null)ps.close();}
            catch(SQLException ex){}
            try{if(conn != null)conn.close();}
            catch(SQLException ex){}
        }
        return c;
    }
    public void addCliente(Cliente c)throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = source.getConnection();
            String query = "INSERT into cliente(c_id,c_nombre,c_apellido,c_tel,c_agente) "+
                    "VALUES(?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1,c.getId());
            ps.setString(2,c.getNombre());
            ps.setString(3,c.getApellido());
            ps.setString(4,c.getTel());
            ps.setInt(5,c.getAgente().getId());
            ps.executeUpdate();
        }finally{
            try{if(ps != null)ps.close();}
            catch(SQLException ex){}
            try{if(conn != null)conn.close();}
            catch(SQLException ex){}
        }
    }
    public void deleteCliente(int id)throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = source.getConnection();
            String query = "DELETE from cliente "+
                    " WHERE c_id = ?;";
            ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ps.execute();
        }finally{
            try{if(ps != null)ps.close();}
            catch(SQLException ex){}
            try{if(conn != null)conn.close();}
            catch(SQLException ex){}
        }
    }
    public void updateCliente(Cliente c)throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = source.getConnection();
            String query = "UPDATE cliente SET c_id=?,c_nombre=?,c_apellido=?,c_tel=?,c_agente=? WHERE c_id=?;";
            ps = conn.prepareStatement(query);
            ps.setInt(1,c.getId());
            ps.setString(2,c.getNombre());
            ps.setString(3,c.getApellido());
            ps.setString(4,c.getTel());
            ps.setInt(5,c.getAgente().getId());
            ps.setInt(6,c.getId());
            ps.executeUpdate();
        }finally{
            try{if(ps != null)ps.close();}
            catch(SQLException ex){}
            try{if(conn != null)conn.close();}
            catch(SQLException ex){}
        }
    }
}
