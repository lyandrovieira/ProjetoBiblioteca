package formularios;

import connection.ConnectionFactory;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Acervo;
import model.dao.AcervoDAO;
import net.proteanit.sql.DbUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
/**
 *
 * @author lyand
 */
public class ConsultarAcervo extends javax.swing.JInternalFrame {

    public ConsultarAcervo() {
        initComponents();
        readJTable();
        setClosable(true);

    }

    public void setPosicao() { //Posiciona o JInternalFrame no centro da JFrame.
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void readJTable() { //Exibe os exemplares salvos na JTable.
        DefaultTableModel tblConsultExemp = (DefaultTableModel) tabelaConsulExemplares.getModel();
        tblConsultExemp.setNumRows(0);

        AcervoDAO a_dao = new AcervoDAO();
        for (Acervo a : a_dao.read()) {
            tblConsultExemp.addRow(new Object[]{
                a.getId(),
                a.getTitulo(),
                a.getAutor(),
                a.getExemplar(),
                a.getExempDisp(),
                a.getVolume(),
                a.getEdicao(),
                a.getEditora(),
                a.getAno_publi(),
                a.getChamada()
            });
        }
    }

    public void readJTableTitulo(String title) { //Exibe na Jtable o resultado da pesquisa por Título.
        DefaultTableModel tblConsultExemp = (DefaultTableModel) tabelaConsulExemplares.getModel();
        tblConsultExemp.setNumRows(0);

        AcervoDAO a_dao = new AcervoDAO();
        for (Acervo a : a_dao.readTitulo(title)) {
            tblConsultExemp.addRow(new Object[]{
                a.getId(),
                a.getTitulo(),
                a.getAutor(),
                a.getExemplar(),
                a.getExempDisp(),
                a.getVolume(),
                a.getEdicao(),
                a.getEditora(),
                a.getAno_publi(),
                a.getChamada()
            });
        }
    }

    public void readJTableAutor(String author) { //Exibe na Jtable o resultado da pesquisa por Autor.
        DefaultTableModel tblConsultExemp = (DefaultTableModel) tabelaConsulExemplares.getModel();
        tblConsultExemp.setNumRows(0);

        AcervoDAO a_dao = new AcervoDAO();
        for (Acervo a : a_dao.readAutor(author)) {
            tblConsultExemp.addRow(new Object[]{
                a.getId(),
                a.getTitulo(),
                a.getAutor(),
                a.getExemplar(),
                a.getExempDisp(),
                a.getVolume(),
                a.getEdicao(),
                a.getEditora(),
                a.getAno_publi(),
                a.getChamada()
            });
        }
    }

    public void readJTableChamada(String code) { //Exibe na Jtable o resultado da pesquisa por Nº de Chamada.
        DefaultTableModel tblConsultExemp = (DefaultTableModel) tabelaConsulExemplares.getModel();
        tblConsultExemp.setNumRows(0);

        AcervoDAO a_dao = new AcervoDAO();
        for (Acervo a : a_dao.readChamada(code)) {
            tblConsultExemp.addRow(new Object[]{
                a.getId(),
                a.getTitulo(),
                a.getAutor(),
                a.getExemplar(),
                a.getExempDisp(),
                a.getVolume(),
                a.getEdicao(),
                a.getEditora(),
                a.getAno_publi(),
                a.getChamada()
            });
        }
    }

    public void pesquisaAcervoGeral() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id,titulo,autor,exemplar,exempDisponiveis,volume,edicao,editora,ano_publi,chamada FROM tbl_books WHERE titulo LIKE ? OR autor LIKE ? OR chamada LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + txtPesqExemplar.getText() + "%");
            stmt.setString(2, "%" + txtPesqExemplar.getText() + "%");
            stmt.setString(3, "%" + txtPesqExemplar.getText() + "%");
            rs = stmt.executeQuery();

            tabelaConsulExemplares.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    
    //NÃO APAGAR "private void initComponents();
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPesqExemplar = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConsulExemplares = new javax.swing.JTable();
        excluirExemplar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Consultar Acervo");

        txtPesqExemplar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPesqExemplar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesqExemplarKeyReleased(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        tabelaConsulExemplares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Título", "Autor", "Exemplares", "Exemplares Disponíveis", "Volume", "Edição", "Editora", "Ano", "Nº de Chamada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaConsulExemplares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaConsulExemplaresKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaConsulExemplares);

        excluirExemplar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        excluirExemplar.setText("Excluir");
        excluirExemplar.setMaximumSize(new java.awt.Dimension(84, 24));
        excluirExemplar.setMinimumSize(new java.awt.Dimension(84, 24));
        excluirExemplar.setPreferredSize(new java.awt.Dimension(84, 24));
        excluirExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirExemplarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Título, Autor ou Nº de Chamada");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesqExemplar)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(excluirExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPesqExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(excluirExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_jButton5ActionPerformed
    
    //Exclui exemplares do DB.
    private void excluirExemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirExemplarActionPerformed
        if (tabelaConsulExemplares.getSelectedRow() != -1) {
            Acervo acervo = new Acervo();
            AcervoDAO dao = new AcervoDAO();

            acervo.setId((int) tabelaConsulExemplares.getValueAt(tabelaConsulExemplares.getSelectedRow(), 0));

            dao.delete(acervo);

            readJTable();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um exemplar para excluir.");
        }
    }//GEN-LAST:event_excluirExemplarActionPerformed

    private void tabelaConsulExemplaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaConsulExemplaresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaConsulExemplaresKeyReleased

    private void txtPesqExemplarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqExemplarKeyReleased
        pesquisaAcervoGeral();
    }//GEN-LAST:event_txtPesqExemplarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton excluirExemplar;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaConsulExemplares;
    private javax.swing.JTextField txtPesqExemplar;
    // End of variables declaration//GEN-END:variables
}
