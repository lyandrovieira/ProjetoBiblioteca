package formularios;

import connection.ConnectionFactory;
import java.awt.Dimension;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.bean.Usuarios;
import model.dao.UsuariosDAO;
import net.proteanit.sql.DbUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
/**
 *
 * @author lyand
 */
public class UsuariosCadastrados extends javax.swing.JInternalFrame {

    private boolean autenticacao;
    private String senhaDigitada;
    private String usuarioDigitado;
    private String hashSenhaVerificacao;
    private String password;
    JPasswordField pass = new JPasswordField();
    JTextField txtUser = new JTextField();

    public UsuariosCadastrados() {
        initComponents();
        pesquisaTotalUsuarios();
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
        for (Usuarios u : u_dao.read()) {
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
        for (Usuarios u : u_dao.readNome(name)) {
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

    //Pesquisa usuário por nome.
    public void pesquisaGeralUsuario() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id,nome,dataNasc,telefone,sexo,tipo,serie,endereco FROM tbl_users WHERE nome LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + txtPesqUsuario.getText() + "%");
            rs = stmt.executeQuery();

            tblConsultUser.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar usuário: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
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

    public boolean verificarSenha() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tbl_admins WHERE nome LIKE ? AND senha LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuarioDigitado);
            stmt.setString(2, hashSenhaVerificacao);
            rs = stmt.executeQuery();

            if (rs.next()) {
                autenticacao = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao validar senha de usuário: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return autenticacao;
    }

    public void pesquisaTotalUsuarios() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(nome) FROM tbl_users";

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs != null && rs.next()) {
                totUsuarios.setText(String.valueOf(rs.getInt(1)));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular o total de usuários cadastrados: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    @SuppressWarnings("unchecked")

    //NÃO APAGAR "private void initComponents().
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPesqUsuario = new javax.swing.JTextField();
        cancelarUser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsultUser = new javax.swing.JTable();
        excluirUsuario = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        totUsuarios = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(700, 600));

        txtPesqUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPesqUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesqUsuarioKeyReleased(evt);
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

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Usuários Cadastrados");

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
        excluirUsuario.setMaximumSize(new java.awt.Dimension(84, 24));
        excluirUsuario.setMinimumSize(new java.awt.Dimension(84, 24));
        excluirUsuario.setPreferredSize(new java.awt.Dimension(84, 24));
        excluirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirUsuarioActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Nome do usuário");

        totUsuarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        totUsuarios.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesqUsuario))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(excluirUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(cancelarUser)
                .addGap(240, 240, 240))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesqUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(totUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarUser)
                    .addComponent(excluirUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            int input = JOptionPane.showConfirmDialog(null, "Para excluir um usuário, é necessário confirmação com senha", "Excluir Usuário", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

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
                    Usuarios users = new Usuarios();
                    UsuariosDAO dao = new UsuariosDAO();

                    users.setId((int) tblConsultUser.getValueAt(tblConsultUser.getSelectedRow(), 0));

                    dao.delete(users);

                    readJTable();
                    pesquisaTotalUsuarios();
                } else {
                    JOptionPane.showMessageDialog(null, "Autorização inválida. Verifique os dados inseridos.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um usuário para excluir.");
        }
    }//GEN-LAST:event_excluirUsuarioActionPerformed

    private void txtPesqUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqUsuarioKeyReleased
        pesquisaGeralUsuario();
    }//GEN-LAST:event_txtPesqUsuarioKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarUser;
    private javax.swing.JButton excluirUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblConsultUser;
    private javax.swing.JTextField totUsuarios;
    private javax.swing.JTextField txtPesqUsuario;
    // End of variables declaration//GEN-END:variables
}
