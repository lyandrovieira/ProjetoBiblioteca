package formularios;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Acervo;
import model.dao.AcervoDAO;

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
        selecionarCategoria.setSelectedItem("Selecione");
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
                a.getVolume(),
                a.getEdicao(),
                a.getEditora(),
                a.getAno_publi(),
                a.getChamada()
            });
        }
    }

    @SuppressWarnings("unchecked")
    
    //NÃO APAGAR "private void initComponents();
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPesqExemplar = new javax.swing.JTextField();
        btnPesqExemplar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConsulExemplares = new javax.swing.JTable();
        excluirExemplar = new javax.swing.JButton();
        selecionarCategoria = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Consultar Acervo");

        txtPesqExemplar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        btnPesqExemplar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPesqExemplar.setText("Pesquisar");
        btnPesqExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesqExemplarActionPerformed(evt);
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
                "ID", "Título", "Autor", "Exemplares", "Volume", "Editora", "Ano", "Nº de Chamada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        excluirExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirExemplarActionPerformed(evt);
            }
        });

        selecionarCategoria.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        selecionarCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Título", "Autor", "Nº Chamada" }));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Categoria");

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
                        .addGap(6, 6, 6)
                        .addComponent(selecionarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesqExemplar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesqExemplar)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(excluirExemplar)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPesqExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesqExemplar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(selecionarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(excluirExemplar))
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

    private void btnPesqExemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesqExemplarActionPerformed
        //Efetua busca de exemplares no DB a partir do critério de pesquisa selecionado.
        if (selecionarCategoria.getSelectedItem() == "Título") {
            if (txtPesqExemplar.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Digite o Título desejado no campo de pesquisa.");
            } else {
                readJTableTitulo(txtPesqExemplar.getText());
            }
        } else if (selecionarCategoria.getSelectedItem() == "Autor") {
            if (txtPesqExemplar.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Digite o Autor desejado no campo de pesquisa.");
            } else {
                readJTableAutor(txtPesqExemplar.getText());
            }
        } else if (selecionarCategoria.getSelectedItem() == "Nº Chamada") {
            if (txtPesqExemplar.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Digite o Nº de Chamada desejado no campo de pesquisa.");
            } else {
                readJTableChamada(txtPesqExemplar.getText());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma categoria para efetuar pesquisa.");
        }

    }//GEN-LAST:event_btnPesqExemplarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesqExemplar;
    private javax.swing.JButton excluirExemplar;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> selecionarCategoria;
    private javax.swing.JTable tabelaConsulExemplares;
    private javax.swing.JTextField txtPesqExemplar;
    // End of variables declaration//GEN-END:variables
}
