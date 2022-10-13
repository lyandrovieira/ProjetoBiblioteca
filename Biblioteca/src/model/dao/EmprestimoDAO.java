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
import model.bean.Emprestimos;
import model.bean.Usuarios;

/**
 *
 * @author lyand
 */
public class EmprestimoDAO {

    public void create(Emprestimos emps) { //Salva os empréstimos realizados.

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("INSERT INTO tbl_emp (numChamada,usuario,dataEmp,dataDev)VALUES(?,?,?,?)");
            stmt.setString(1, emps.getNumChamada());
            stmt.setString(2, emps.getUsuario());
            stmt.setString(3, emps.getDataEmp());
            stmt.setString(4, emps.getDataDev());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Emprestimos> read() { //Lê os dados de empréstimo.

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Emprestimos> emp = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_emp");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Emprestimos emps = new Emprestimos();

                emps.setId(rs.getInt("id"));
                emps.setNumChamada(rs.getString("numChamada"));
                emps.setUsuario(rs.getString("usuario"));
                emps.setDataEmp(rs.getString("dataEmp"));
                emps.setDataDev(rs.getString("dataDev"));
                
                emp.add(emps);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir dados em tabela: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return emp;
    }
    
    public List<Emprestimos> readNome(String name) { //Lê os empréstimos com base na pesquisa por nome.

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Emprestimos> emp = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_emp WHERE usuario LIKE ?");
            stmt.setString(1, "%"+name+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Emprestimos emps = new Emprestimos();

                emps.setId(rs.getInt("id"));
                emps.setNumChamada(rs.getString("numChamada"));
                emps.setUsuario(rs.getString("usuario"));
                emps.setDataEmp(rs.getString("dataEmp"));
                emps.setDataDev(rs.getString("dataDev"));
                
                emp.add(emps);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir dados em tabela: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return emp;
    }
    
    public List<Emprestimos> readChamada(String code) { //Lê os empréstimos com base na pesquisa por numChamada.

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Emprestimos> emp = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_emp WHERE numChamada LIKE ?");
            stmt.setString(1, "%"+code+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Emprestimos emps = new Emprestimos();

                emps.setId(rs.getInt("id"));
                emps.setNumChamada(rs.getString("numChamada"));
                emps.setUsuario(rs.getString("usuario"));
                emps.setDataEmp(rs.getString("dataEmp"));
                emps.setDataDev(rs.getString("dataDev"));
                
                emp.add(emps);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir dados em tabela: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return emp;
    }
    
    public void update(Emprestimos empre) { //Atualiza os dados de empréstimo no DB.

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("UPDATE tbl_emp SET numChamada=?,usuario=?,dataEmp=?,dataDev=? WHERE id=?");
            stmt.setString(1, empre.getNumChamada());
            stmt.setString(2, empre.getUsuario());
            stmt.setString(3, empre.getDataEmp());
            stmt.setString(4, empre.getDataDev());
            stmt.setInt(5, empre.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public void delete(Emprestimos empre) { //Deleta dados de empréstimo no DB.

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("DELETE FROM tbl_emp WHERE id=?");
            stmt.setInt(1, empre.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
