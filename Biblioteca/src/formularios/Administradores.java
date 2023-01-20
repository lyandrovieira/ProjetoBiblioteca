package formularios;

import connection.ConnectionFactory;
import java.awt.Dimension;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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

    private boolean autenticacao;
    private boolean existe;
    private String senhaDigitada;
    private String usuarioDigitado;
    private String hashSenhaVerificacao;
    private String hashSenhaRegistro;
    private String senha;
    private String confSenha;
    private String password;
    JPasswordField pass = new JPasswordField();
    JTextField txtUser = new JTextField();

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

    public boolean existeUsuario() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tbl_admins";

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            existe = rs.next();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao validar usuário: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return existe;
    }

    public boolean verificarSenha() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tbl_admins WHERE nome LIKE ? AND senha LIKE ? AND (ocupacao LIKE ? OR ocupacao LIKE ?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuarioDigitado);
            stmt.setString(2, hashSenhaVerificacao);
            stmt.setString(3, "Direção");
            stmt.setString(4, "Suporte");
            rs = stmt.executeQuery();

            if (rs.next()) {
                autenticacao = true;
            } else {
                JOptionPane.showMessageDialog(null, "Apenas usuários com nivel de autorização 'Direção' podem validar a ação solicitada.");
                autenticacao = false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao validar usuário: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return autenticacao;
    }

    public String criarHashVerificacao() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        password = senhaDigitada;

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }

        hashSenhaVerificacao = hexString.toString();

        return hashSenhaVerificacao;
    }

    public String criarHashRegistro() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        password = senha;

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }

        hashSenhaRegistro = hexString.toString();

        return hashSenhaRegistro;
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
        cadastrarAdmin.setMaximumSize(new java.awt.Dimension(84, 24));
        cadastrarAdmin.setMinimumSize(new java.awt.Dimension(84, 24));
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
        selecionarOcupacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Professor(a)", "Administrativo", "Direção", "Suporte" }));
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
        excluirAdmin.setMaximumSize(new java.awt.Dimension(84, 24));
        excluirAdmin.setMinimumSize(new java.awt.Dimension(84, 24));
        excluirAdmin.setPreferredSize(new java.awt.Dimension(84, 24));
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addGap(8, 8, 8)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(confirmarSenha)
                                    .addComponent(senhaAdmin)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(selecionarOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(usuarioAdmin))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(232, 232, 232))))
            .addGroup(layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(cadastrarAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(excluirAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(cancelarAdmin)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(284, 284, 284))
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
                    .addComponent(cadastrarAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarAdmin)
                    .addComponent(excluirAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        existeUsuario();

        if (existe) {
            senha = new String(senhaAdmin.getPassword());
            confSenha = new String(confirmarSenha.getPassword());
            if ((usuarioAdmin.getText().isBlank()) || (senhaAdmin.getText().isBlank()) || (confirmarSenha.getText().isBlank()) || (selecionarOcupacao.getSelectedItem() == "Selecione")) {
                JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
            } else {
                if (senha.equals(confSenha)) {
                    int input = JOptionPane.showConfirmDialog(null, "Para incluir um novo Administrador, é necessário a autenticação de Administrador com ocupação 'Direção'. Continuar?", "Incluir Administrador", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (input == 0) {
                        JLabel digUser = new JLabel("Digite seu nome de usuário:");
                        JOptionPane.showConfirmDialog(null,
                                new Object[]{digUser, txtUser}, "Usuário",
                                JOptionPane.OK_CANCEL_OPTION);
                        usuarioDigitado = txtUser.getText();

                        JLabel digSenha = new JLabel("Digite sua senha de usuário:");
                        JOptionPane.showConfirmDialog(null,
                                new Object[]{digSenha, pass}, "Senha",
                                JOptionPane.OK_CANCEL_OPTION);
                        senhaDigitada = new String(pass.getPassword());

                        try {
                            criarHashVerificacao();
                        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                            Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        verificarSenha();

                        if (autenticacao == true) {
                            Admins admin = new Admins();
                            AdminsDAO dao = new AdminsDAO();

                            try {
                                criarHashRegistro();
                            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                                Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            admin.setNome(usuarioAdmin.getText());
                            admin.setSenha(hashSenhaRegistro);
                            admin.setOcupacao(selecionarOcupacao.getSelectedItem().toString());

                            dao.create(admin);

                            usuarioAdmin.setText(null);
                            senhaAdmin.setText(null);
                            confirmarSenha.setText(null);
                            selecionarOcupacao.setSelectedItem("Selecione");

                            readJTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Autorização inválida. Verifique os dados inseridos.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "As senhas digitadas são diferentes.");
                    }
                }
            }
        } else {
            senha = new String(senhaAdmin.getPassword());
            confSenha = new String(confirmarSenha.getPassword());
            if ((usuarioAdmin.getText().isBlank()) || (senhaAdmin.getText().isBlank()) || (confirmarSenha.getText().isBlank()) || (selecionarOcupacao.getSelectedItem() == "Selecione")) {
                JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
            } else {
                if (senha.equals(confSenha)) {
                    Admins admin = new Admins();
                    AdminsDAO dao = new AdminsDAO();

                    try {
                        criarHashRegistro();
                    } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                        Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    admin.setNome(usuarioAdmin.getText());
                    admin.setSenha(hashSenhaRegistro);
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
            }
        }
    }//GEN-LAST:event_cadastrarAdminActionPerformed

// Exclui adminitradores registrados.
    private void excluirAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirAdminActionPerformed
        if (tabelaAdmin.getSelectedRow() != -1) {
            int input = JOptionPane.showConfirmDialog(null, "Para excluir um exemplar, é necessário confirmação com senha", "Excluir Exemplar", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (input == 0) {
                JLabel digUser = new JLabel("Digite seu nome de usuário:");
                JOptionPane.showConfirmDialog(null,
                        new Object[]{digUser, txtUser}, "Usuário",
                        JOptionPane.OK_CANCEL_OPTION);
                usuarioDigitado = txtUser.getText();

                JLabel digSenha = new JLabel("Digite sua senha de usuário:");
                JOptionPane.showConfirmDialog(null,
                        new Object[]{digSenha, pass}, "Senha",
                        JOptionPane.OK_CANCEL_OPTION);
                senhaDigitada = new String(pass.getPassword());

                try {
                    criarHashVerificacao();
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                    Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex);
                }

                verificarSenha();

                if (autenticacao == true) {
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
                    JOptionPane.showMessageDialog(null, "Autorização inválida. Verifique os dados inseridos.");
                }
            }
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
