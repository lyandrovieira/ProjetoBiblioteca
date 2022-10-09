/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Admins;

/**
 *
 * @author lyand
 */
public class AdminsDAO {

    public void create(Admins adm) { //Salva os administradores no DB.

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("INSERT INTO tbl_admins (nome,senha,ocupacao)VALUES(?,?,?)");
            stmt.setString(1, adm.getNome());
            stmt.setString(2, adm.getSenha());
            stmt.setString(3, adm.getOcupacao());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public List<Admins> read() { //Lê os dados de admins salvos no DB.

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Admins> admin = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_admins");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Admins adm = new Admins();

                adm.setId(rs.getInt("id"));
                adm.setNome(rs.getString("nome"));
                adm.setOcupacao(rs.getString("ocupacao"));
                admin.add(adm);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir dados em tabela: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return admin;
    }
    
    public void delete(Admins adm) { //Deleta administradores.

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("DELETE FROM tbl_admins WHERE id=?");
            stmt.setInt(1, adm.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
