/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.ResultSet;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Acervo> read() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Acervo> acervoExemp = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_books");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Acervo acervo = new Acervo();

                acervo.setId(rs.getInt("id"));
                acervo.setTitulo(rs.getString("titulo"));
                acervo.setAutor(rs.getString("autor"));
                acervo.setExemplar(rs.getInt("exemplar"));
                acervo.setVolume(rs.getInt("volume"));
                acervo.setEditora(rs.getString("editora"));
                acervo.setAno_publi(rs.getInt("ano_publi"));
                acervo.setChamada(rs.getString("chamada"));
                
                acervoExemp.add(acervo);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir dados em tabela: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return acervoExemp;
    }

    public void update(Acervo a) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("UPDATE tbl_books SET titulo=?,autor=?,exemplar=?,volume=?,editora=?,ano_publi=?,chamada=? WHERE id=?");
            stmt.setString(1, a.getTitulo());
            stmt.setString(2, a.getAutor());
            stmt.setInt(3, a.getExemplar());
            stmt.setInt(4, a.getVolume());
            stmt.setString(5, a.getEditora());
            stmt.setInt(6, a.getAno_publi());
            stmt.setString(7, a.getChamada());
            stmt.setInt(8, a.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public void delete(Acervo a) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("DELETE FROM tbl_books WHERE id=?");
            stmt.setInt(1, a.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
