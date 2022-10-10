package formularios;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Usuarios;
import model.dao.UsuariosDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

/**
 *
 * @author lyand
 */
public class UsuariosCadastrados extends javax.swing.JInternalFrame {

    public UsuariosCadastrados() {
        initComponents();
        readJTable();
        setClosable(true);
    }

    public void setPosicao() { //Posiciona o JInternalFrame no centro do JFrame Principal.
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    public void readJTable() { //Exibe os usuários cadastrados na JTable.
        DefaultTableModel tblUsers = (DefaultTableModel) tblConsultUser.getModel();
        tblUsers.setNumRows(0);
        
        UsuariosDAO u_dao = new UsuariosDAO();
        for(Usuarios u: u_dao.read()) {
            tblUsers.addRow(new Object[]{
                u.getId(),
                u.getNome(),
                u.getDataNasc(),
                u.getTelefone(),
                u.getSexo(),
                u.getTipo(),
                u.getSerie(),
                u.getEndereco()
            });
        }
    }
    
    public void readJTableName(String name) { //Exibe o resultado da pesquisa de usuários por nome.
        DefaultTableModel tblUsers = (DefaultTableModel) tblConsultUser.getModel();
        tblUsers.setNumRows(0);
        
        UsuariosDAO u_dao = new UsuariosDAO();
        for(Usuarios u: u_dao.readNome(name)) {
            tblUsers.addRow(new Object[]{
                u.getId(),
                u.getNome(),
                u.getDataNasc(),
                u.getTelefone(),
                u.getSexo(),
                u.getTipo(),
                u.getSerie(),
                u.getEndereco()
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    
    //NÃO APAGAR "private void initComponents().
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPesqUsuario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cancelarUser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsultUser = new javax.swing.JTable();
        excluirUsuario = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 600));

        txtPesqUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cancelarUser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cancelarUser.setText("Cancelar");
        cancelarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarUserActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Usuários Cadastrados");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Nome do usuário");

        tblConsultUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Data Nascimento", "Telefone", "Sexo", "TIpo", "Série", "Endereço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblConsultUser);

        excluirUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        excluirUsuario.setText("Excluir");
        excluirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPesqUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(272, 272, 272)
                                .addComponent(jLabel1)))
                        .addGap(0, 135, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(excluirUsuario)
                .addGap(39, 39, 39)
                .addComponent(cancelarUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPesqUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarUser)
                    .addComponent(excluirUsuario))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarUserActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_cancelarUserActionPerformed

    //Exclui usuários.
    private void excluirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirUsuarioActionPerformed
        if (tblConsultUser.getSelectedRow() != -1) {
            Usuarios users = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO();

            users.setId((int) tblConsultUser.getValueAt(tblConsultUser.getSelectedRow(), 0));

            dao.delete(users);

            readJTable();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um usuário para excluir.");
        }
    }//GEN-LAST:event_excluirUsuarioActionPerformed

    //Realiza pesquisa de usuário por nome.
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (txtPesqUsuario.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Digite o Título desejado no campo de pesquisa.");
            } else {
                readJTableName(txtPesqUsuario.getText());
            }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarUser;
    private javax.swing.JButton excluirUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblConsultUser;
    private javax.swing.JTextField txtPesqUsuario;
    // End of variables declaration//GEN-END:variables
}