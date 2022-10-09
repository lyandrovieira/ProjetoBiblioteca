
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
public class CadastrarUsuarios extends javax.swing.JInternalFrame {

    public CadastrarUsuarios() {
        initComponents();
        readJTable();
        selecionarSerie.setEnabled(false);
        setClosable(true);
    }

    public void setPosicao() { //Posiciona o JInternalFrame no centro da JFrame Principal.
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void readJTable() { //Exibe os dados do DB na JTable.
        DefaultTableModel tblUsers = (DefaultTableModel) tabelaUsuarios.getModel();
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

    @SuppressWarnings("unchecked")
    
    //NÃO APAGAR "private void initComponents()".
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nomeUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        enderecoUsuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        selecionarSerie = new javax.swing.JComboBox<>();
        labelSerie = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cadastrarUsuario = new javax.swing.JButton();
        cancelarUsuario = new javax.swing.JButton();
        dataNascimento = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaUsuarios = new javax.swing.JTable();
        selecionarTipo = new javax.swing.JComboBox<>();
        telefone = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        selecionarSexo = new javax.swing.JComboBox<>();
        atualizarUser = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Cadastrar Usuários");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Nome *");

        nomeUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Data Nascimento");

        enderecoUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        enderecoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enderecoUsuarioActionPerformed(evt);
            }
        });

        selecionarSerie.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        selecionarSerie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "6", "7", "8", "9", "1", "2", "3", "Não se Aplica" }));
        selecionarSerie.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                selecionarSerieComponentHidden(evt);
            }
        });
        selecionarSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionarSerieActionPerformed(evt);
            }
        });

        labelSerie.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelSerie.setText("Série");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Endereço");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Telefone");

        cadastrarUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cadastrarUsuario.setText("Cadastrar");
        cadastrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarUsuarioActionPerformed(evt);
            }
        });
        cadastrarUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cadastrarUsuarioKeyPressed(evt);
            }
        });

        cancelarUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cancelarUsuario.setText("Cancelar");
        cancelarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarUsuarioActionPerformed(evt);
            }
        });

        try {
            dataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataNascimento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        dataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataNascimentoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Tipo");

        tabelaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Data Nascimento", "Telefone", "Sexo", "Tipo", "Série", "Endereço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaUsuariosMouseClicked(evt);
            }
        });
        tabelaUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaUsuariosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaUsuarios);

        selecionarTipo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        selecionarTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Estudante", "Servidor", "Externo" }));
        selecionarTipo.setPreferredSize(new java.awt.Dimension(104, 23));
        selecionarTipo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                selecionarTipoComponentHidden(evt);
            }
        });
        selecionarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionarTipoActionPerformed(evt);
            }
        });

        try {
            telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        telefone.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Campos marcados com  *  são obrigatórios");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Sexo");

        selecionarSexo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        selecionarSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Feminino", "Masculino" }));
        selecionarSexo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                selecionarSexoComponentHidden(evt);
            }
        });
        selecionarSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionarSexoActionPerformed(evt);
            }
        });

        atualizarUser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        atualizarUser.setText("Atualizar");
        atualizarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(282, 282, 282))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(128, 128, 128)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enderecoUsuario)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(dataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(selecionarSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(selecionarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(labelSerie)
                                .addGap(31, 31, 31)
                                .addComponent(selecionarSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(nomeUsuario))))
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(cadastrarUsuario)
                        .addGap(42, 42, 42)
                        .addComponent(atualizarUser)
                        .addGap(46, 46, 46)
                        .addComponent(cancelarUsuario)))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selecionarSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(selecionarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSerie)
                    .addComponent(selecionarSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enderecoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(218, 218, 218))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancelarUsuario)
                            .addComponent(cadastrarUsuario)
                            .addComponent(atualizarUser))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selecionarSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecionarSerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selecionarSerieActionPerformed

    private void selecionarSerieComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_selecionarSerieComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_selecionarSerieComponentHidden

    private void cancelarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarUsuarioActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_cancelarUsuarioActionPerformed

    private void cadastrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarUsuarioActionPerformed
        //Realiza o cadastro dos dados de Usuário no DB.
        if (nomeUsuario.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else {
            Usuarios users = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO();

            users.setNome(nomeUsuario.getText());
            users.setDataNasc(dataNascimento.getText());
            users.setTelefone(telefone.getText());
            users.setSexo(selecionarSexo.getSelectedItem().toString());
            users.setTipo(selecionarTipo.getSelectedItem().toString());
            users.setSerie(selecionarSerie.getSelectedItem().toString());
            users.setEndereco(enderecoUsuario.getText());

            dao.create(users);

            nomeUsuario.setText(null);
            dataNascimento.setText(null);
            telefone.setText(null);
            enderecoUsuario.setText(null);
            selecionarTipo.setSelectedItem("Selecione");
            selecionarSerie.setSelectedItem("Selecione");
            selecionarSerie.setEnabled(false);

            readJTable();
        }

    }//GEN-LAST:event_cadastrarUsuarioActionPerformed

    private void selecionarTipoComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_selecionarTipoComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_selecionarTipoComponentHidden

    private void selecionarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecionarTipoActionPerformed
        //Gerencia a atribuição automática de valores dos campos "Tipo" e "Série".
        if (selecionarTipo.getSelectedItem() == "Selecione") {
            selecionarSerie.setSelectedItem("Selecione");
            selecionarSerie.setEnabled(false);
        } else if (selecionarTipo.getSelectedItem() == "Estudante") {
            selecionarSerie.setSelectedItem("Selecione");
            selecionarSerie.setEnabled(true);
        } else {
            selecionarSerie.setSelectedItem("Não se Aplica");
            selecionarSerie.setEnabled(false);
        }
    }//GEN-LAST:event_selecionarTipoActionPerformed

    private void dataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataNascimentoActionPerformed

    private void selecionarSexoComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_selecionarSexoComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_selecionarSexoComponentHidden

    private void selecionarSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecionarSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selecionarSexoActionPerformed

    private void atualizarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarUserActionPerformed
        //Atualiza os dados do usuário selecionado na JTable.
        if (tabelaUsuarios.getSelectedRow() != -1) {
            Usuarios users = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO();

            users.setNome(nomeUsuario.getText());
            users.setDataNasc(dataNascimento.getText());
            users.setTelefone(telefone.getText());
            users.setSexo(selecionarSexo.getSelectedItem().toString());
            users.setTipo(selecionarTipo.getSelectedItem().toString());
            users.setSerie(selecionarSerie.getSelectedItem().toString());
            users.setEndereco(enderecoUsuario.getText());
            users.setId((int) tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 0));

            dao.update(users);

            nomeUsuario.setText(null);
            dataNascimento.setText(null);
            telefone.setText(null);
            enderecoUsuario.setText(null);
            selecionarTipo.setSelectedItem("Selecione");
            selecionarSerie.setSelectedItem("Selecione");
            selecionarSerie.setEnabled(false);

            readJTable();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um usuário para atualizar.");
        }
    }//GEN-LAST:event_atualizarUserActionPerformed

    private void tabelaUsuariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaUsuariosKeyReleased
        //Seleciona um usuário na JTable.
        if (tabelaUsuarios.getSelectedRow() != -1) {

            nomeUsuario.setText(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 1).toString());
            dataNascimento.setText(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 2).toString());
            telefone.setText(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 3).toString());
            selecionarSexo.setSelectedItem(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 4).toString());
            selecionarTipo.setSelectedItem(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 5).toString());
            selecionarSerie.setSelectedItem(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 6).toString());
            enderecoUsuario.setText(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 7).toString());

        }
    }//GEN-LAST:event_tabelaUsuariosKeyReleased

    private void tabelaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaUsuariosMouseClicked
        //Seleciona um usuário na JTable.
        if (tabelaUsuarios.getSelectedRow() != -1) {

            nomeUsuario.setText(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 1).toString());
            dataNascimento.setText(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 2).toString());
            telefone.setText(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 3).toString());
            selecionarSexo.setSelectedItem(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 4).toString());
            selecionarTipo.setSelectedItem(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 5).toString());
            selecionarSerie.setSelectedItem(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 6).toString());
            enderecoUsuario.setText(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 7).toString());

        }
    }//GEN-LAST:event_tabelaUsuariosMouseClicked

    private void enderecoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enderecoUsuarioActionPerformed
        //Conclui o registro de usuário ao clicar "Enter" com o campo de endereço selecionado.
        if (nomeUsuario.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else {
            Usuarios users = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO();

            users.setNome(nomeUsuario.getText());
            users.setDataNasc(dataNascimento.getText());
            users.setTelefone(telefone.getText());
            users.setSexo(selecionarSexo.getSelectedItem().toString());
            users.setTipo(selecionarTipo.getSelectedItem().toString());
            users.setSerie(selecionarSerie.getSelectedItem().toString());
            users.setEndereco(enderecoUsuario.getText());

            dao.create(users);

            nomeUsuario.setText(null);
            dataNascimento.setText(null);
            telefone.setText(null);
            enderecoUsuario.setText(null);
            selecionarTipo.setSelectedItem("Selecione");
            selecionarSerie.setSelectedItem("Selecione");
            selecionarSerie.setEnabled(false);

            readJTable();
        }
    }//GEN-LAST:event_enderecoUsuarioActionPerformed

    private void cadastrarUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cadastrarUsuarioKeyPressed
        //Conclui o registro de usuário ao clicar no botão de cadastro.
        if (nomeUsuario.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else {
            Usuarios users = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO();

            users.setNome(nomeUsuario.getText());
            users.setDataNasc(dataNascimento.getText());
            users.setTelefone(telefone.getText());
            users.setSexo(selecionarSexo.getSelectedItem().toString());
            users.setTipo(selecionarTipo.getSelectedItem().toString());
            users.setSerie(selecionarSerie.getSelectedItem().toString());
            users.setEndereco(enderecoUsuario.getText());

            dao.create(users);

            nomeUsuario.setText(null);
            dataNascimento.setText(null);
            telefone.setText(null);
            enderecoUsuario.setText(null);
            selecionarTipo.setSelectedItem("Selecione");
            selecionarSerie.setSelectedItem("Selecione");
            selecionarSerie.setEnabled(false);

            readJTable();
        }
    }//GEN-LAST:event_cadastrarUsuarioKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizarUser;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cadastrarUsuario;
    private javax.swing.JButton cancelarUsuario;
    private javax.swing.JFormattedTextField dataNascimento;
    private javax.swing.JTextField enderecoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelSerie;
    private javax.swing.JTextField nomeUsuario;
    private javax.swing.JComboBox<String> selecionarSerie;
    private javax.swing.JComboBox<String> selecionarSexo;
    private javax.swing.JComboBox<String> selecionarTipo;
    private javax.swing.JTable tabelaUsuarios;
    private javax.swing.JFormattedTextField telefone;
    // End of variables declaration//GEN-END:variables
}
