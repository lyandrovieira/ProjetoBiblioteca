package formularios;

import connection.ConnectionFactory;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Devolucoes;
import model.dao.DevolucaoDAO;
import net.proteanit.sql.DbUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
/**
 *
 * @author lyand
 */
public class Devolucao extends javax.swing.JInternalFrame {

    private int incremento;
    private int qtdExemp;
    private String devolvido;

    public Devolucao() {
        initComponents();
        readJTable();
        setDate();
        setClosable(true);
    }

    public void setPosicao() { //Posiciona o JInternalFrame no centro da JFrame Principal.
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void readJTable() { //Exibe os dados de devolução na JTable.
        DefaultTableModel tblUsers = (DefaultTableModel) tabelaDevolucao.getModel();
        tblUsers.setNumRows(0);

        DevolucaoDAO dev_dao = new DevolucaoDAO();
        for (Devolucoes dev : dev_dao.read()) {
            tblUsers.addRow(new Object[]{
                dev.getId(),
                dev.getNumChamada(),
                dev.getUsuario(),
                dev.getDataDev(),
                dev.getSituacao()
            });
        }
    }

    //Efeuta pesquisa de empréstimo para devolução.
    public void pesquisarEmprestimo() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id, usuario, numChamada, idLivro, situacao FROM tbl_emp WHERE usuario LIKE ? AND numChamada LIKE ? AND (situacao LIKE ? OR situacao LIKE ?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + usuarioDevolucao.getText() + "%");
            stmt.setString(2, "%" + codigoDevolucao.getText() + "%");
            stmt.setString(3, "Em Circulação");
            stmt.setString(4, "Em Atraso");
            rs = stmt.executeQuery();

            tblDev.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar empréstimo: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

    }

    //Obtém a qtd de exemplares disponíveis para empréstimo.
    public int pegarQtdExempDisp() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmtQtdExemp = null;
        ResultSet resultado = null;
        String sqlQtdExemp = "SELECT exempDisponiveis FROM tbl_books WHERE id LIKE ?";

        try {
            stmtQtdExemp = con.prepareStatement(sqlQtdExemp);
            stmtQtdExemp.setString(1, idExemplar.getText());
            resultado = stmtQtdExemp.executeQuery();

            if (resultado != null && resultado.next()) {
                qtdExemp = resultado.getInt("exempDisponiveis");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao obter quantidade de exemplares disponíveis: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmtQtdExemp, resultado);
        }
        return qtdExemp;
    }

    //Altera a qtd de exemplares disponíveis ao efetuar devolução.
    public void alterarQtdExemplares() {
        incremento = qtdExemp + 1;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE tbl_books SET exempDisponiveis=? WHERE id LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, incremento);
            stmt.setString(2, idExemplar.getText());
            stmt.executeUpdate();

            readJTable();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar exemplar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //Altera a situação do empréstimo para "Devolvido"
    public void alterarSitEmprestimoDevolucao() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        devolvido = today.format(formatador);

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE tbl_emp SET situacao=?, devolvido=? WHERE id LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "Devolvido");
            stmt.setString(2, devolvido);
            stmt.setString(3, idEmprestimo.getText());
            stmt.executeUpdate();

            readJTable();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar situação do empréstimo: " + ex);
        }
    }

    public void setDate() { //Configura a exibição da data atual e seta como data na qual o exemplar foi devolvido.

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataDevolucao.setText(today.format(formatador));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usuarioDevolucao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        codigoDevolucao = new javax.swing.JTextField();
        confirmarDevolucao = new javax.swing.JButton();
        cancelarDevolucao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDevolucao = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        dataDev = new javax.swing.JLabel();
        dataDevolucao = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDev = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        idEmprestimo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        idExemplar = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Devolução de Exemplar");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Usuário *");

        usuarioDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioDevolucaoActionPerformed(evt);
            }
        });
        usuarioDevolucao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usuarioDevolucaoKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Nº de Chamada *");

        codigoDevolucao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codigoDevolucaoKeyReleased(evt);
            }
        });

        confirmarDevolucao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        confirmarDevolucao.setText("Confirmar");
        confirmarDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarDevolucaoActionPerformed(evt);
            }
        });

        cancelarDevolucao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cancelarDevolucao.setText("Cancelar");
        cancelarDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarDevolucaoActionPerformed(evt);
            }
        });

        tabelaDevolucao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nº de Chamada", "Usuário", "Devolvido em", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaDevolucao);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Campos marcados com  *  são obrigatórios");

        dataDev.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        dataDev.setText("Data Devolução");

        dataDevolucao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        dataDevolucao.setEnabled(false);

        tblDev.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuário", "Nº Chamada", "ID Livro", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDevMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDev);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("ID");

        idEmprestimo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        idEmprestimo.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("ID Livro");

        idExemplar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        idExemplar.setEnabled(false);
        idExemplar.setPreferredSize(new java.awt.Dimension(15, 24));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1))
                    .addComponent(jLabel4))
                .addGap(233, 233, 233))
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(confirmarDevolucao)
                .addGap(18, 18, 18)
                .addComponent(cancelarDevolucao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(codigoDevolucao, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(usuarioDevolucao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dataDev)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dataDevolucao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(codigoDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(idExemplar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(60, 60, 60)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(idEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(usuarioDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dataDev)
                        .addComponent(dataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmarDevolucao)
                    .addComponent(cancelarDevolucao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarDevolucaoActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_cancelarDevolucaoActionPerformed

    //Efetua a devolução de exemplar ao clicar no botão de confirmação.
    private void confirmarDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarDevolucaoActionPerformed
        if ((usuarioDevolucao.getText().isBlank()) || (codigoDevolucao.getText().isBlank())) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else {
            String situacao = "Devolvido";

            Devolucoes dev = new Devolucoes();
            DevolucaoDAO dao = new DevolucaoDAO();

            dev.setNumChamada(usuarioDevolucao.getText());
            dev.setUsuario(codigoDevolucao.getText());
            dev.setDataDev(dataDevolucao.getText());
            dev.setSituacao(situacao);

            dao.create(dev);

            pegarQtdExempDisp();
            alterarQtdExemplares();
            alterarSitEmprestimoDevolucao();

            codigoDevolucao.setText(null);
            usuarioDevolucao.setText(null);
            idEmprestimo.setText(null);
            idExemplar.setText(null);

            ((DefaultTableModel) tblDev.getModel()).setRowCount(0);

            readJTable();
        }
    }//GEN-LAST:event_confirmarDevolucaoActionPerformed

    //Efetua a devolução de exemplar ao clicar ENTER.
    private void usuarioDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioDevolucaoActionPerformed
        if ((usuarioDevolucao.getText().isBlank()) || (codigoDevolucao.getText().isBlank())) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else {
            String situacao = "Devolvido";

            Devolucoes dev = new Devolucoes();
            DevolucaoDAO dao = new DevolucaoDAO();

            dev.setNumChamada(usuarioDevolucao.getText());
            dev.setUsuario(codigoDevolucao.getText());
            dev.setDataDev(dataDevolucao.getText());
            dev.setSituacao(situacao);

            dao.create(dev);

            pegarQtdExempDisp();
            alterarQtdExemplares();
            alterarSitEmprestimoDevolucao();

            codigoDevolucao.setText(null);
            usuarioDevolucao.setText(null);
            idEmprestimo.setText(null);
            idExemplar.setText(null);

            ((DefaultTableModel) tblDev.getModel()).setRowCount(0);

            readJTable();
        }
    }//GEN-LAST:event_usuarioDevolucaoActionPerformed

    private void codigoDevolucaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoDevolucaoKeyReleased
        pesquisarEmprestimo();
    }//GEN-LAST:event_codigoDevolucaoKeyReleased

    private void usuarioDevolucaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioDevolucaoKeyReleased
        pesquisarEmprestimo();
    }//GEN-LAST:event_usuarioDevolucaoKeyReleased

    private void tblDevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDevMouseClicked
        if (tblDev.getSelectedRow() != -1) {
            idEmprestimo.setText(tblDev.getValueAt(tblDev.getSelectedRow(), 0).toString());
            usuarioDevolucao.setText(tblDev.getValueAt(tblDev.getSelectedRow(), 1).toString());
            codigoDevolucao.setText(tblDev.getValueAt(tblDev.getSelectedRow(), 2).toString());
            idExemplar.setText(tblDev.getValueAt(tblDev.getSelectedRow(), 3).toString());
        }
    }//GEN-LAST:event_tblDevMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarDevolucao;
    private javax.swing.JTextField codigoDevolucao;
    private javax.swing.JButton confirmarDevolucao;
    private javax.swing.JLabel dataDev;
    private javax.swing.JTextField dataDevolucao;
    private javax.swing.JTextField idEmprestimo;
    private javax.swing.JTextField idExemplar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabelaDevolucao;
    private javax.swing.JTable tblDev;
    private javax.swing.JTextField usuarioDevolucao;
    // End of variables declaration//GEN-END:variables
}
