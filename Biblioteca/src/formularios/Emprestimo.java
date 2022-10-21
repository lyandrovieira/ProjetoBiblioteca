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

    private int decremento;
    private int qtdExemp;
    private int qtdTotExemp;
    private int idLiv;
    private int idUse;
    private int idEmp;
    private String dataDev;
    private LocalDate dataDevol;
    private LocalDate hj;

    public Emprestimo() {
        initComponents();
        setDate();
        situacaoEmprestimo.setText("Em Circulação");
        getData();
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
                emp.getDevolvido(),
                emp.getSituacao(),
                emp.getIdLivro(),
                emp.getIdUsuario()
            });
        }
    }

    //Pesquisa de usuário, por nome, para preencher tabela auxiliar.
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

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar usuário: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    //Pesquisa de exemplar, por número de chamada, para preencher tabela auxiliar.
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

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar exemplar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    //Obtém a quantidade de exemplares disponíveis para empréstimo.
    public int pegarQtdExempDisp() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmtQtdExemp = null;
        ResultSet resultado = null;
        String sqlQtdExemp = "SELECT exempDisponiveis FROM tbl_books WHERE id LIKE ?";

        try {
            stmtQtdExemp = con.prepareStatement(sqlQtdExemp);
            stmtQtdExemp.setString(1, idBook.getText());
            resultado = stmtQtdExemp.executeQuery();

            if (resultado != null && resultado.next()) {
                qtdExemp = resultado.getInt("exempDisponiveis");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao obter quantidade de exemplares: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmtQtdExemp, resultado);
        }
        return qtdExemp;
    }

    //Obtém a quantidade total de exemplares registrados para o livro pesquisado.
    public int pegarQtdTotalExemp() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmtQtdExemp = null;
        ResultSet resultado = null;
        String sqlQtdExemp = "SELECT exemplar FROM tbl_books WHERE id LIKE ?";

        try {
            stmtQtdExemp = con.prepareStatement(sqlQtdExemp);
            stmtQtdExemp.setString(1, idBook.getText());
            resultado = stmtQtdExemp.executeQuery();

            if (resultado != null && resultado.next()) {
                qtdTotExemp = resultado.getInt("exemplar");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao obter quantidade de exemplares: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmtQtdExemp, resultado);
        }
        return qtdTotExemp;
    }

    //Altera a quantidade de exemplares disponíveis ao efetuar um empréstimo.
    public void alterarQtdExemplares() {
        decremento = qtdExemp - 1;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE tbl_books SET exempDisponiveis=? WHERE id LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, decremento);
            stmt.setString(2, idBook.getText());
            stmt.executeUpdate();

            readJTable();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao subtrair exemplar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //Atualiza, ao clicar na tabela principal do formulário de cadastro de empréstimo, os índices de usuário e exemplar.
    public void ajustarIndicesEmp() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tbl_emp WHERE id LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idEmprestimo.getText()));
            rs = stmt.executeQuery();

            if (rs != null && rs.next()) {
                idLiv = rs.getInt("idLivro");
                idUse = rs.getInt("idUsuario");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar ID do exemplar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        idBook.setText(Integer.toString(idLiv));
        idUsuario.setText(Integer.toString(idUse));
    }

    //Atribui situação "Em Atraso" para empréstimos atrasados.
    public void alterarSitEmprestimoAtraso() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE tbl_emp SET situacao=? WHERE id LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "Em Atraso");
            stmt.setInt(2, idEmp);
            stmt.executeUpdate();

            readJTable();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar situação do empréstimo: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //Obtém e analisa a data de empréstimo e a data atual para verificar se existe atraso.
    public void getData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        hj = LocalDate.now();
        hj.format(formatter);

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet data = null;
        String sql = "SELECT id,dataEmp,dataDev FROM tbl_emp WHERE situacao LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "Em Circulação");
            data = stmt.executeQuery();

            while (data != null && data.next()) {
                idEmp = data.getInt("id");
                dataDev = data.getString("dataDev");
                dataDevol = LocalDate.parse(dataDev, formatter);

                if (hj.compareTo(dataDevol) > 0) {
                    alterarSitEmprestimoAtraso();
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar e analisar datas: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, data);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUserEmp = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChamadaEmp = new javax.swing.JTable();
        idBook = new javax.swing.JTextField();
        idUsuario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        situacaoEmprestimo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        idEmprestimo = new javax.swing.JTextField();

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
                "ID", "Nº de Chamada", "Usuário", "Data Empréstimo", "Data Devolução", "Devolvido em", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        idBook.setEnabled(false);

        idUsuario.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Situação");

        situacaoEmprestimo.setEditable(false);
        situacaoEmprestimo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        situacaoEmprestimo.setEnabled(false);
        situacaoEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                situacaoEmprestimoActionPerformed(evt);
            }
        });
        situacaoEmprestimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                situacaoEmprestimoKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("ID");

        idEmprestimo.setEditable(false);
        idEmprestimo.setEnabled(false);

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
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(numChamada, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idBook, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(confirmarEmprestimo)
                                        .addGap(44, 44, 44)
                                        .addComponent(cancelarEmprestimo)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(situacaoEmprestimo))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(56, 56, 56)
                        .addComponent(usuarioEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
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
                        .addComponent(idBook, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(situacaoEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(idEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmarEmprestimo)
                    .addComponent(cancelarEmprestimo))
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
        pegarQtdTotalExemp();
        pegarQtdExempDisp();
        if ((numChamada.getText().isBlank()) || (usuarioEmprestimo.getText().isBlank())) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else {
            if ((qtdExemp > 0) && (qtdExemp <= qtdTotExemp)) {
                int input = JOptionPane.showConfirmDialog(null, "Confirmar empréstimo de exemplar?", "Empréstimo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (input == 0) {
                    Emprestimos emprestimo = new Emprestimos();
                    EmprestimoDAO dao = new EmprestimoDAO();

                    emprestimo.setNumChamada(numChamada.getText());
                    emprestimo.setUsuario(usuarioEmprestimo.getText());
                    emprestimo.setDataEmp(dataEmprestimo.getText());
                    emprestimo.setDataDev(dataDevolucao.getText());
                    emprestimo.setDevolvido("");
                    emprestimo.setSituacao(situacaoEmprestimo.getText());
                    emprestimo.setIdLivro(Integer.parseInt(idBook.getText()));
                    emprestimo.setIdUsuario(Integer.parseInt(idUsuario.getText()));

                    dao.create(emprestimo);

                    alterarQtdExemplares();

                    numChamada.setText(null);
                    usuarioEmprestimo.setText(null);
                    idBook.setText(null);
                    idUsuario.setText(null);

                    ((DefaultTableModel) tblChamadaEmp.getModel()).setRowCount(0);
                    ((DefaultTableModel) tblUserEmp.getModel()).setRowCount(0);

                    readJTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Empréstimo Cancelado!");
                    numChamada.setText(null);
                    usuarioEmprestimo.setText(null);
                    idBook.setText(null);
                    idUsuario.setText(null);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não é possível realizar o empréstimo. Verifique o número de exemplares disponíveis.");
            }
        }
    }//GEN-LAST:event_confirmarEmprestimoActionPerformed

    //Seleciona linha da JTable.
    private void tabelaEmprestimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaEmprestimoKeyReleased
        if (tabelaEmprestimo.getSelectedRow() != -1) {
            idEmprestimo.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 0).toString());
            numChamada.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 1).toString());
            usuarioEmprestimo.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 2).toString());
            dataEmprestimo.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 3).toString());
            dataDevolucao.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 4).toString());
            situacaoEmprestimo.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 5).toString());
        }
        ajustarIndicesEmp();
    }//GEN-LAST:event_tabelaEmprestimoKeyReleased

    //Seleciona linha da JTable.
    private void tabelaEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEmprestimoMouseClicked
        if (tabelaEmprestimo.getSelectedRow() != -1) {
            idEmprestimo.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 0).toString());
            numChamada.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 1).toString());
            usuarioEmprestimo.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 2).toString());
            dataEmprestimo.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 3).toString());
            dataDevolucao.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 4).toString());
            situacaoEmprestimo.setText(tabelaEmprestimo.getValueAt(tabelaEmprestimo.getSelectedRow(), 6).toString());
        }
        ajustarIndicesEmp();
    }//GEN-LAST:event_tabelaEmprestimoMouseClicked

    //Confirma o empréstimo de exemplar ao clicar ENTER.
    private void usuarioEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioEmprestimoActionPerformed
        //chamada = numChamada.getText();
        if ((numChamada.getText().isBlank()) || (usuarioEmprestimo.getText().isBlank())) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else {
            Emprestimos emprestimo = new Emprestimos();
            EmprestimoDAO dao = new EmprestimoDAO();

            emprestimo.setNumChamada(numChamada.getText());
            emprestimo.setUsuario(usuarioEmprestimo.getText());
            emprestimo.setDataEmp(dataEmprestimo.getText());
            emprestimo.setDataDev(dataDevolucao.getText());
            emprestimo.setDevolvido(dataDevolucao.getText());
            emprestimo.setSituacao(situacaoEmprestimo.getText());
            emprestimo.setIdLivro(Integer.parseInt(idBook.getText()));
            emprestimo.setIdUsuario(Integer.parseInt(idUsuario.getText()));

            dao.create(emprestimo);

            pegarQtdExempDisp();
            alterarQtdExemplares();

            numChamada.setText(null);
            usuarioEmprestimo.setText(null);
            idBook.setText(null);
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
            idBook.setText(tblChamadaEmp.getValueAt(tblChamadaEmp.getSelectedRow(), 0).toString());
            numChamada.setText(tblChamadaEmp.getValueAt(tblChamadaEmp.getSelectedRow(), 5).toString());
        }
    }//GEN-LAST:event_tblChamadaEmpMouseClicked

    private void tblUserEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserEmpMouseClicked
        if (tblUserEmp.getSelectedRow() != -1) {
            idUsuario.setText(tblUserEmp.getValueAt(tblUserEmp.getSelectedRow(), 0).toString());
            usuarioEmprestimo.setText(tblUserEmp.getValueAt(tblUserEmp.getSelectedRow(), 1).toString());
        }
    }//GEN-LAST:event_tblUserEmpMouseClicked

    private void situacaoEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_situacaoEmprestimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_situacaoEmprestimoActionPerformed

    private void situacaoEmprestimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_situacaoEmprestimoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_situacaoEmprestimoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarEmprestimo;
    private javax.swing.JButton confirmarEmprestimo;
    private javax.swing.JTextField dataDevolucao;
    private javax.swing.JTextField dataEmprestimo;
    private javax.swing.JTextField idBook;
    private javax.swing.JTextField idEmprestimo;
    private javax.swing.JTextField idUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTextField situacaoEmprestimo;
    private javax.swing.JTable tabelaEmprestimo;
    private javax.swing.JTable tblChamadaEmp;
    private javax.swing.JTable tblUserEmp;
    private javax.swing.JTextField usuarioEmprestimo;
    // End of variables declaration//GEN-END:variables
}
