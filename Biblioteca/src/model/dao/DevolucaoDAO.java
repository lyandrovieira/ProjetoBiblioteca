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
import model.bean.Devolucoes;

/**
 *
 * @author lyand
 */
public class DevolucaoDAO {
    public void create(Devolucoes emps) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("INSERT INTO tbl_dev (numChamada,usuario,dataDev)VALUES(?,?,?)");
            stmt.setString(1, emps.getNumChamada());
            stmt.setString(2, emps.getUsuario());
            stmt.setString(3, emps.getDataDev());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Devolucoes> read() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Devolucoes> dev = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_dev");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Devolucoes devs = new Devolucoes();

                devs.setId(rs.getInt("id"));
                devs.setNumChamada(rs.getString("numChamada"));
                devs.setUsuario(rs.getString("usuario"));
                devs.setDataDev(rs.getString("dataDev"));
                
                dev.add(devs);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir dados em tabela: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return dev;
    }
    
    public void update(Devolucoes dev) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("UPDATE tbl_dev SET numChamada=?,usuario=?,dataDev=? WHERE id=?");
            stmt.setString(1, dev.getNumChamada());
            stmt.setString(2, dev.getUsuario());
            stmt.setString(3, dev.getDataDev());
            stmt.setInt(4, dev.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public void delete(Devolucoes dev) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("DELETE FROM tbl_dev WHERE id=?");
            stmt.setInt(1, dev.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
