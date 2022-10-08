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
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Usuarios> read() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuarios> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_users");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuarios users = new Usuarios();

                users.setId(rs.getInt("id"));
                users.setNome(rs.getString("nome"));
                users.setDataNasc(rs.getString("dataNasc"));
                users.setTelefone(rs.getString("telefone"));
                users.setSexo(rs.getString("sexo"));
                users.setTipo(rs.getString("tipo"));
                users.setSerie(rs.getString("serie"));
                users.setEndereco(rs.getString("endereco"));
                usuarios.add(users);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir dados em tabela: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return usuarios;
    }

    public void update(Usuarios u) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("UPDATE tbl_users SET nome=?,dataNasc=?,telefone=?,sexo=?,tipo=?,serie=?,endereco=? WHERE id=?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getDataNasc());
            stmt.setString(3, u.getTelefone());
            stmt.setString(4, u.getSexo());
            stmt.setString(5, u.getTipo());
            stmt.setString(6, u.getSerie());
            stmt.setString(7, u.getEndereco());
            stmt.setInt(8, u.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public void delete(Usuarios u) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("DELETE FROM tbl_users  WHERE id=?");
            stmt.setInt(1, u.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
