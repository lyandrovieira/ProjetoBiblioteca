package formularios;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Devolucoes;
import model.dao.DevolucaoDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

/**
 *
 * @author lyand
 */
public class ConsultarDevolucao extends javax.swing.JInternalFrame {

    public ConsultarDevolucao() {
        initComponents();
        readJTable();
        setClosable(true);
    }

    public void setPosicao() { //Posiciona o JInternalFrame no centro do JFrame.
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    public void readJTable() { //Exibe os dados de devolução na JTable.
        DefaultTableModel tblUsers = (DefaultTableModel) tblConsultaDev.getModel();
        tblUsers.setNumRows(0);

        DevolucaoDAO dev_dao = new DevolucaoDAO();
        for (Devolucoes dev : dev_dao.read()) {
            tblUsers.addRow(new Object[]{
                dev.getId(),
                dev.getNumChamada(),
                dev.getUsuario(),
                dev.getDataDev()
            });
        }
    }
    
    public void readJTableUsuario(String name) { //Exibe o resultado da pesquisa por Usuário.
        DefaultTableModel tblUsers = (DefaultTableModel) tblConsultaDev.getModel();
        tblUsers.setNumRows(0);

        DevolucaoDAO dev_dao = new DevolucaoDAO();
        for (Devolucoes dev : dev_dao.readNome(name)) {
            tblUsers.addRow(new Object[]{
                dev.getId(),
                dev.getNumChamada(),
                dev.getUsuario(),
                dev.getDataDev()
            });
        }
    }
    
    public void readJTableChamada(String code) { //Exibe o resultado da pesquisa por número de chamada.
        DefaultTableModel tblUsers = (DefaultTableModel) tblConsultaDev.getModel();
        tblUsers.setNumRows(0);

        DevolucaoDAO dev_dao = new DevolucaoDAO();
        for (Devolucoes dev : dev_dao.readChamada(code)) {
            tblUsers.addRow(new Object[]{
                dev.getId(),
                dev.getNumChamada(),
                dev.getUsuario(),
                dev.getDataDev()
            });
        }
    }

    @SuppressWarnings("unchecked")
    
    //NÃO APAGAR "private void initComponents".
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cancelDev = new javax.swing.JButton();
        lblLupaDev = new javax.swing.JLabel();
        pesqDevolucao = new javax.swing.JTextField();
        btnPesqDevolucao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsultaDev = new javax.swing.JTable();
        selecionarCategoria = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Consultar Devolução");

        cancelDev.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cancelDev.setText("Cancelar");
        cancelDev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelDevActionPerformed(evt);
            }
        });

        lblLupaDev.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblLupaDev.setText("Categoria");

        pesqDevolucao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        btnPesqDevolucao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPesqDevolucao.setText("Pesquisar");
        btnPesqDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesqDevolucaoActionPerformed(evt);
            }
        });

        tblConsultaDev.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nº de Chamada", "Usuário", "Data Devolução"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblConsultaDev);

        selecionarCategoria.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        selecionarCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Nº de Chamada", "Usuário" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(295, 295, 295)
                .addComponent(cancelDev)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabel1)
                        .addGap(0, 277, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLupaDev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selecionarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesqDevolucao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesqDevolucao)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLupaDev)
                    .addComponent(pesqDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesqDevolucao)
                    .addComponent(selecionarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelDev)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelDevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelDevActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_cancelDevActionPerformed

    //Efetua pesquisa de devoluções no DB com base no critério de pesquisa selecionado.
    private void btnPesqDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesqDevolucaoActionPerformed
        if (selecionarCategoria.getSelectedItem() == "Usuário") {
            if (pesqDevolucao.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Digite o Usuário desejado no campo de pesquisa.");
            } else {
                readJTableUsuario(pesqDevolucao.getText());
            }
        } else if (selecionarCategoria.getSelectedItem() == "Nº de Chamada") {
            if (pesqDevolucao.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Digite o Nº de Chamada desejado no campo de pesquisa.");
            } else {
                readJTableChamada(pesqDevolucao.getText());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma categoria para efetuar pesquisa.");
        }
    }//GEN-LAST:event_btnPesqDevolucaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesqDevolucao;
    private javax.swing.JButton cancelDev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLupaDev;
    private javax.swing.JTextField pesqDevolucao;
    private javax.swing.JComboBox<String> selecionarCategoria;
    private javax.swing.JTable tblConsultaDev;
    // End of variables declaration//GEN-END:variables
}
