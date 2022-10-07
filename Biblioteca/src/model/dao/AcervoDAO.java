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
import model.bean.Acervo;

/**
 *
 * @author lyand
 */
public class AcervoDAO {
    
    public void create(Acervo a) {
    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO tbl_books (titulo,autor,exemplar,volume,editora,ano_publi,chamada)VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1, a.getTitulo());
            stmt.setString(2, a.getAutor());
            stmt.setInt(3, a.getExemplar());
            stmt.setInt(4, a.getVolume());
            stmt.setString(5, a.getEditora());
            stmt.setInt(6, a.getAno_publi());
            stmt.setString(7, a.getChamada());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar:" + ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
               
    }
    
}
