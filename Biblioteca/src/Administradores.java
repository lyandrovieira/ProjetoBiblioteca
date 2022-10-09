import java.awt.Dimension;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Admins;
import model.dao.AdminsDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

/**
 *
 * @author lyand
 */
public class Administradores extends javax.swing.JInternalFrame {

    public Administradores() {
        initComponents();
        selecionarOcupacao.setSelectedItem("Selecione");
        readJTable();
        setClosable(true);
    }

    public void setPosicao() { //Posiciona o JInternalFrame no centro da JFrame "Principal"
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    public void readJTable() { //Exibe os dados do DB na JTable.
        DefaultTableModel tblAdmins = (DefaultTableModel) tabelaAdmin.getModel();
        tblAdmins.setNumRows(0);

        AdminsDAO u_dao = new AdminsDAO();
        for (Admins u : u_dao.read()) {
            tblAdmins.addRow(new Object[]{
                u.getId(),
                u.getNome(),
                u.getOcupacao()
            });
        }
    }
    
    
    @SuppressWarnings("unchecked") 
    
    //NÃO ALTERAR O 'private void initComponents()'.
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usuarioAdmin = new javax.swing.JTextField();
        senhaAdmin = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        cadastrarAdmin = new javax.swing.JButton();
        cancelarAdmin = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        confirmarSenha = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        selecionarOcupacao = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAdmin = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        excluirAdmin = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Administradores");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Nome de Usuário *");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Senha *");

        cadastrarAdmin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cadastrarAdmin.setText("Cadastrar");
        cadastrarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarAdminActionPerformed(evt);
            }
        });

        cancelarAdmin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cancelarAdmin.setText("Cancelar");
        cancelarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarAdminActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Confirmar Senha *");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Ocupação *");

        selecionarOcupacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        selecionarOcupacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Professor(a)", "Administrativo" }));
        selecionarOcupacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionarOcupacaoActionPerformed(evt);
            }
        });

        tabelaAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuário", "Ocupação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaAdmin);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("Campos marcados com  *  são obrigatórios");

        excluirAdmin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        excluirAdmin.setText("Excluir");
        excluirAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(selecionarOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(confirmarSenha)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(senhaAdmin)
                                    .addComponent(usuarioAdmin)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(282, 282, 282))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(232, 232, 232))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(cadastrarAdmin)
                .addGap(44, 44, 44)
                .addComponent(excluirAdmin)
                .addGap(44, 44, 44)
                .addComponent(cancelarAdmin)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senhaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(selecionarOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadastrarAdmin)
                    .addComponent(cancelarAdmin)
                    .addComponent(excluirAdmin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarAdminActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_cancelarAdminActionPerformed

    private void selecionarOcupacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecionarOcupacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selecionarOcupacaoActionPerformed

    //Salva as informações de administradores no DB.
    private void cadastrarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarAdminActionPerformed
        char[] senha = senhaAdmin.getPassword();
        char[] confSenha = confirmarSenha.getPassword();
        if ((usuarioAdmin.getText().isBlank()) || (senhaAdmin.getText().isBlank()) || (confirmarSenha.getText().isBlank()) || (selecionarOcupacao.getSelectedItem() == "Selecione")) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else if (Arrays.equals(senha, confSenha)) {
            
            Admins admin = new Admins();
            AdminsDAO dao = new AdminsDAO();
            
            admin.setNome(usuarioAdmin.getText());
            admin.setSenha(senhaAdmin.getText());
            admin.setOcupacao(selecionarOcupacao.getSelectedItem().toString());
            
            dao.create(admin);
            
            usuarioAdmin.setText(null);
            senhaAdmin.setText(null);
            confirmarSenha.setText(null);
            selecionarOcupacao.setSelectedItem("Selecione");
            
            readJTable();

        } else {
            JOptionPane.showMessageDialog(null, "As senhas digitadas são diferentes.");
        }
    }//GEN-LAST:event_cadastrarAdminActionPerformed
    
// Exclui adminitradores registrados.
    private void excluirAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirAdminActionPerformed
        if (tabelaAdmin.getSelectedRow() != -1) {
            Admins admin = new Admins();
            AdminsDAO dao = new AdminsDAO();

            admin.setId((int) tabelaAdmin.getValueAt(tabelaAdmin.getSelectedRow(), 0));

            dao.delete(admin);

            usuarioAdmin.setText(null);
            senhaAdmin.setText(null);
            confirmarSenha.setText(null);
            selecionarOcupacao.setSelectedItem(null);

            readJTable();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um exemplar para excluir.");
        }
    }//GEN-LAST:event_excluirAdminActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cadastrarAdmin;
    private javax.swing.JButton cancelarAdmin;
    private javax.swing.JPasswordField confirmarSenha;
    private javax.swing.JButton excluirAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> selecionarOcupacao;
    private javax.swing.JPasswordField senhaAdmin;
    private javax.swing.JTable tabelaAdmin;
    private javax.swing.JTextField usuarioAdmin;
    // End of variables declaration//GEN-END:variables
}
