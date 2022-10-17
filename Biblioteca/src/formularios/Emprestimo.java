package formularios;

import connection.ConnectionFactory;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Emprestimos;
import model.dao.EmprestimoDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
/**
 *
 * @author lyand
 */
public class Emprestimo extends javax.swing.JInternalFrame {

    private String chamada;
    private int decremento;
    private int qtdExemp;

    public Emprestimo() {
        initComponents();
        setDate();
        selecionarSituacao.setSelectedItem("Em Circulação");
        selecionarSituacao.setEnabled(false);
        readJTable();
        setClosable(true);
    }

    public void setPosicao() { //Posiciona o JInternalFrame no centro do JFrame Principal.      
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void readJTable() { //Exibe os dados de Empréstimo na JTable.
        DefaultTableModel tblEmp = (DefaultTableModel) tabelaEmprestimo.getModel();
        tblEmp.setNumRows(0);

        EmprestimoDAO emp_dao = new EmprestimoDAO();
        for (Emprestimos emp : emp_dao.read()) {
            tblEmp.addRow(new Object[]{
                emp.getId(),
                emp.getNumChamada(),
                emp.getUsuario(),
                emp.getDataEmp(),
                emp.getDataDev(),
                emp.getSituacao(),
                emp.getIdLivro(),
                emp.getIdUsuario()
            });
        }
    }

    public void pesquisarUsuarioNome() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id, nome, serie FROM tbl_users WHERE nome LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + usuarioEmprestimo.getText() + "%");
            rs = stmt.executeQuery();

            tblUserEmp.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void pesquisarLivroChamada() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id, titulo, autor, volume, edicao, chamada FROM tbl_books WHERE chamada LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + numChamada.getText() + "%");
            rs = stmt.executeQuery();

            tblChamadaEmp.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public int pegarQtdExempDisp() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmtQtdExemp = null;
        ResultSet resultado = null;
        String sqlQtdExemp = "SELECT exempDisponiveis FROM tbl_books WHERE id LIKE ?";

        try {
            stmtQtdExemp = con.prepareStatement(sqlQtdExemp);
            stmtQtdExemp.setString(1, idEmprestimo.getText());
            resultado = stmtQtdExemp.executeQuery();

            if (resultado != null && resultado.next()) {
                qtdExemp = resultado.getInt("exempDisponiveis");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao obter quantidade de exemplares: " + ex);
        }
        return qtdExemp;
    }

    public void alterarQtdExemplares() {
        decremento = qtdExemp - 1;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE tbl_books SET exempDisponiveis=? WHERE id LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, decremento);
            stmt.setString(2, idEmprestimo.getText());
            stmt.executeUpdate();

            readJTable();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao subtrair exemplar: " + ex);
        }
    }

    public void setDate() {//Configura a exibição das datas de empréstimo e devolução de exemplares.

        LocalDate dEmp = LocalDate.now();
        LocalDate dDev = dEmp.plusDays(7);
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataEmprestimo.setText(dEmp.format(formatador));
        dataDevolucao.setText(dDev.format(formatador));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numChamada = new javax.swing.JTextField();
        usuarioEmprestimo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        confirmarEmprestimo = new javax.swing.JButton();
        cancelarEmprestimo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEmprestimo = new javax.swing.JTable();
        dataDevolucao = new javax.swing.JTextField();
        dataEmprestimo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        editarEmprestimo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUserEmp = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChamadaEmp = new javax.swing.JTable();
        idEmprestimo = new javax.swing.JTextField();
        idUsuario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        selecionarSituacao = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Empréstimos");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Usuário *");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Nº de Chamada *");

        numChamada.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        numChamada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numChamadaKeyReleased(evt);
            }
        });

        usuarioEmprestimo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        usuarioEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioEmprestimoActionPerformed(evt);
            }
        });
        usuarioEmprestimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usuarioEmprestimoKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Data Empréstimo");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Data Devolução");

        confirmarEmprestimo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        confirmarEmprestimo.setText("Confirmar");
        confirmarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarEmprestimoActionPerformed(evt);
            }
        });

        cancelarEmprestimo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cancelarEmprestimo.setText("Cancelar");
        cancelarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarEmprestimoActionPerformed(evt);
            }
        });

        tabelaEmprestimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nº de Chamada", "Usuário", "Data Empréstimo", "Data Devolução", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaEmprestimoMouseClicked(evt);
            }
        });
        tabelaEmprestimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaEmprestimoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaEmprestimo);

        dataDevolucao.setEditable(false);
        dataDevolucao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        dataDevolucao.setEnabled(false);

        dataEmprestimo.setEditable(false);
        dataEmprestimo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        dataEmprestimo.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Campos marcados com  *  são obrigatórios");

        editarEmprestimo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        editarEmprestimo.setText("Editar");
        editarEmprestimo.setMaximumSize(new java.awt.Dimension(84, 24));
        editarEmprestimo.setMinimumSize(new java.awt.Dimension(84, 24));
        editarEmprestimo.setPreferredSize(new java.awt.Dimension(84, 24));
        editarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarEmprestimoActionPerformed(evt);
            }
        });

        tblUserEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Data Nascimento", "Série"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUserEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserEmpMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblUserEmp);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("ID");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("ID");

        tblChamadaEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Título", "Autor", "Volume", "Edição", "Nº Chamada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChamadaEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChamadaEmpMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblChamadaEmp);
        if (tblChamadaEmp.getColumnModel().getColumnCount() > 0) {
            tblChamadaEmp.getColumnModel().getColumn(0).setResizable(false);
        }

        idEmprestimo.setEnabled(false);

        idUsuario.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Situação");

        selecionarSituacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        selecionarSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Em Circulação", "Em Atraso", "Devolvido" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel1))
                    .addComponent(jLabel6))
                .addGap(240, 240, 240))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selecionarSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(numChamada, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(usuarioEmprestimo)
                                .addGap(105, 105, 105)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(confirmarEmprestimo)
                .addGap(47, 47, 47)
                .addComponent(editarEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(cancelarEmprestimo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numChamada, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(idEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(usuarioEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel8)
                        .addComponent(idUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(dataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(selecionarSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmarEmprestimo)
                    .addComponent(cancelarEmprestimo)
                    .addComponent(editarEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarEmprestimoActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_cancelarEmprestimoActionPerformed

    //Confirma o empréstimo de exemplar ao clicar no botão de confirmação.
    private void confirmarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarEmprestimoActionPerformed
        chamada = numChamada.getText();
        if ((chamada.isBlank()) || (usuarioEmprestimo.getText().isBlank())) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else {
            Emprestimos emprestimo = new Emprestimos();
            EmprestimoDAO dao = new EmprestimoDAO();

            emprestimo.setNumChamada(numChamada.getText());
            emprestimo.setUsuario(usuarioEmprestimo.getText());
            emprestimo.setDataEmp(dataEmprestimo.getText());
            emprestimo.setDataDev(dataDevolucao.getText());
            emprestimo.setSituacao(selecionarSituacao.getSelectedItem().toString());
            emprestimo.setIdLivro(Integer.parseInt(idEmprestimo.getText()));
            emprestimo.setIdUsuario(Integer.parseInt(idUsuario.getText()));

            dao.create(emprestimo);

            pegarQtdExempDisp();
            alterarQtdExemplares();

            numChamada.setText(null);
            usuarioEmprestimo.setText(null);
            idEmprestimo.setText(null);
            idUsuario.setText(null);

            ((DefaultTableModel) tblChamadaEmp.getModel()).setRowCount(0);
            ((DefaultTableModel) tblUserEmp.getModel()).setRowCount(0);

            readJTable();
        }
    }//GEN-LAST:event_confirmarEmprestimoActionPerformed

    //Edita os dados de empréstimo da linha selecionado no JTable.
    private void editarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarEmprestimoActionPerformed
        if (tabelaEmprestimo.getSelectedRow() != -1) {
            Emprestimos emprestimo = new Emprestimos();
            EmprestimoDAO dao = new EmprestimoDAO();

            emprestimo.setNumChamada(numChamada.getText());
            emprestimo.setUsuario(usuarioEmprestimo.getText());
            emprestimo.setDataEmp(dataEmprestimo.getText());
            emprestimo.setDataDev(dataDevolucao.getText());
            emprestimo.setSituacao(selecionarSituacao.getSelectedItem().toString());
            emprestimo.setIdLivro(Integer.parseInt(idEmprestimo.getText()));
            emprestimo.setIdUsuario(Integer.parseInt(idUsuario.getText()));
            emprestimo.setId((int) tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 0));

            dao.update(emprestimo);

            numChamada.setText(null);
            usuarioEmprestimo.setText(null);

            readJTable();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Empréstimo para atualizar.");
        }
    }//GEN-LAST:event_editarEmprestimoActionPerformed

    //Seleciona linha da JTable.
    private void tabelaEmprestimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaEmprestimoKeyReleased
        if (tabelaEmprestimo.getSelectedRow() != -1) {

            numChamada.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 1).toString());
            usuarioEmprestimo.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 2).toString());
            dataEmprestimo.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 3).toString());
            dataDevolucao.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 4).toString());
            selecionarSituacao.setSelectedItem(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 5).toString());

        }
    }//GEN-LAST:event_tabelaEmprestimoKeyReleased

    //Seleciona linha da JTable.
    private void tabelaEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEmprestimoMouseClicked
        if (tabelaEmprestimo.getSelectedRow() != -1) {

            numChamada.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 1).toString());
            usuarioEmprestimo.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 2).toString());
            dataEmprestimo.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 3).toString());
            dataDevolucao.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 4).toString());
            selecionarSituacao.setSelectedItem(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 5).toString());

        }
    }//GEN-LAST:event_tabelaEmprestimoMouseClicked

    //Confirma o empréstimo de exemplar ao clicar ENTER.
    private void usuarioEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioEmprestimoActionPerformed
        chamada = numChamada.getText();
        if ((chamada.isBlank()) || (usuarioEmprestimo.getText().isBlank())) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else {
            Emprestimos emprestimo = new Emprestimos();
            EmprestimoDAO dao = new EmprestimoDAO();

            emprestimo.setNumChamada(numChamada.getText());
            emprestimo.setUsuario(usuarioEmprestimo.getText());
            emprestimo.setDataEmp(dataEmprestimo.getText());
            emprestimo.setDataDev(dataDevolucao.getText());
            emprestimo.setSituacao(selecionarSituacao.getSelectedItem().toString());
            emprestimo.setIdLivro(Integer.parseInt(idEmprestimo.getText()));
            emprestimo.setIdUsuario(Integer.parseInt(idUsuario.getText()));

            dao.create(emprestimo);

            pegarQtdExempDisp();
            alterarQtdExemplares();

            numChamada.setText(null);
            usuarioEmprestimo.setText(null);
            idEmprestimo.setText(null);
            idUsuario.setText(null);

            ((DefaultTableModel) tblChamadaEmp.getModel()).setRowCount(0);
            ((DefaultTableModel) tblUserEmp.getModel()).setRowCount(0);

            readJTable();
        }
    }//GEN-LAST:event_usuarioEmprestimoActionPerformed

    private void usuarioEmprestimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioEmprestimoKeyReleased
        pesquisarUsuarioNome();
    }//GEN-LAST:event_usuarioEmprestimoKeyReleased

    private void numChamadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numChamadaKeyReleased
        pesquisarLivroChamada();
    }//GEN-LAST:event_numChamadaKeyReleased

    private void tblChamadaEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChamadaEmpMouseClicked
        if (tblChamadaEmp.getSelectedRow() != -1) {
            idEmprestimo.setText(tblChamadaEmp.getValueAt(tblChamadaEmp.getSelectedRow(), 0).toString());
            numChamada.setText(tblChamadaEmp.getValueAt(tblChamadaEmp.getSelectedRow(), 5).toString());
        }
    }//GEN-LAST:event_tblChamadaEmpMouseClicked

    private void tblUserEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserEmpMouseClicked
        if (tblUserEmp.getSelectedRow() != -1) {
            idUsuario.setText(tblUserEmp.getValueAt(tblUserEmp.getSelectedRow(), 0).toString());
            usuarioEmprestimo.setText(tblUserEmp.getValueAt(tblUserEmp.getSelectedRow(), 1).toString());
        }
    }//GEN-LAST:event_tblUserEmpMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarEmprestimo;
    private javax.swing.JButton confirmarEmprestimo;
    private javax.swing.JTextField dataDevolucao;
    private javax.swing.JTextField dataEmprestimo;
    private javax.swing.JButton editarEmprestimo;
    private javax.swing.JTextField idEmprestimo;
    private javax.swing.JTextField idUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField numChamada;
    private javax.swing.JComboBox<String> selecionarSituacao;
    private javax.swing.JTable tabelaEmprestimo;
    private javax.swing.JTable tblChamadaEmp;
    private javax.swing.JTable tblUserEmp;
    private javax.swing.JTextField usuarioEmprestimo;
    // End of variables declaration//GEN-END:variables
}
