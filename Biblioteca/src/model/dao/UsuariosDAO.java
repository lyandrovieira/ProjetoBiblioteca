/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Usuarios;

/**
 *
 * @author lyand
 */
public class UsuariosDAO {
    
    public void create(Usuarios u) {
    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO tbl_users (nome,dataNasc,telefone,sexo,tipo,serie,endereco)VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getDataNasc());
            stmt.setString(3, u.getTelefone());
            stmt.setString(4, u.getSexo());
            stmt.setString(5, u.getTipo());
            stmt.setString(6, u.getSerie());
            stmt.setString(7, u.getEndereco());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar:" + ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }      
        
    }
    
}
