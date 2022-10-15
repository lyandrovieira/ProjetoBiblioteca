package formularios;

import connection.ConnectionFactory;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Acervo;
import model.dao.AcervoDAO;
import net.proteanit.sql.DbUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
/**
 *
 * @author lyand
 */
public class CadastroExemplar extends javax.swing.JInternalFrame {

    public CadastroExemplar() {
        initComponents();
        readJTable();
        setClosable(true);
    }

    public void setPosicao() { //Posiciona o JInternalFrame no centro do JFrame.
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void readJTable() { //Insere os dados registrados na JTable.
        DefaultTableModel tblExemp = (DefaultTableModel) tabelaExemplares.getModel();
        tblExemp.setNumRows(0);

        AcervoDAO a_dao = new AcervoDAO();
        for (Acervo a : a_dao.read()) {
            tblExemp.addRow(new Object[]{
                a.getId(),
                a.getTitulo(),
                a.getAutor(),
                a.getExemplar(),
                a.getExempDisp(),
                a.getVolume(),
                a.getEdicao(),
                a.getEditora(),
                a.getAno_publi(),
                a.getChamada()
            });
        }
    }

    public void lerUsuarioEmprestimo() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id,titulo,autor,exemplar,exempDisponiveis,volume,edicao,editora,ano_publi,chamada FROM tbl_books WHERE titulo LIKE ? OR autor LIKE ? OR chamada LIKE ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + campoPesquisaExemp.getText() + "%");
            stmt.setString(2, "%" + campoPesquisaExemp.getText() + "%");
            stmt.setString(3, "%" + campoPesquisaExemp.getText() + "%");
            rs = stmt.executeQuery();

            tabelaExemplares.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")

    //NÃO APAGAR "private void initComponents()".
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cadastrarExemplar = new javax.swing.JButton();
        cancelarCadastroExemplar = new javax.swing.JButton();
        autor = new javax.swing.JTextField();
        qtdExemplares = new javax.swing.JTextField();
        volume = new javax.swing.JTextField();
        editora = new javax.swing.JTextField();
        anoPublicacao = new javax.swing.JTextField();
        numChamada = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaExemplares = new javax.swing.JTable();
        titulo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        atualizaExemplar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        edicao = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        qtdExemplaresDisponiveis = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoPesquisaExemp = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(708, 600));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Cadastrar Exemplares");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Autor *");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Título *");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Exemplares *");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Volume");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Editora");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Ano");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Nº de chamada *");

        cadastrarExemplar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cadastrarExemplar.setText("Cadastrar");
        cadastrarExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarExemplarActionPerformed(evt);
            }
        });

        cancelarCadastroExemplar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cancelarCadastroExemplar.setText("Cancelar");
        cancelarCadastroExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarCadastroExemplarActionPerformed(evt);
            }
        });

        autor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        autor.setPreferredSize(new java.awt.Dimension(320, 25));

        qtdExemplares.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        qtdExemplares.setPreferredSize(new java.awt.Dimension(320, 25));
        qtdExemplares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtdExemplaresActionPerformed(evt);
            }
        });
        qtdExemplares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtdExemplaresKeyReleased(evt);
            }
        });

        volume.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        volume.setPreferredSize(new java.awt.Dimension(320, 25));

        editora.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        editora.setPreferredSize(new java.awt.Dimension(320, 25));

        anoPublicacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        anoPublicacao.setPreferredSize(new java.awt.Dimension(320, 25));

        numChamada.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        numChamada.setPreferredSize(new java.awt.Dimension(320, 25));
        numChamada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numChamadaActionPerformed(evt);
            }
        });

        tabelaExemplares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Título", "Autor", "Exemplares", "Exemplares Disponíveis", "Volume", "Edição", "Editora", "Ano", "Nº de Chamada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaExemplares.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaExemplaresMouseClicked(evt);
            }
        });
        tabelaExemplares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaExemplaresKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaExemplares);

        titulo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        titulo.setPreferredSize(new java.awt.Dimension(320, 25));
        titulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tituloActionPerformed(evt);
            }
        });
        titulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tituloKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Campos marcados com  *  são obrigatórios");

        atualizaExemplar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        atualizaExemplar.setText("Editar");
        atualizaExemplar.setMaximumSize(new java.awt.Dimension(84, 24));
        atualizaExemplar.setMinimumSize(new java.awt.Dimension(84, 24));
        atualizaExemplar.setPreferredSize(new java.awt.Dimension(84, 24));
        atualizaExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaExemplarActionPerformed(evt);
            }
        });
        atualizaExemplar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                atualizaExemplarKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Edição");

        edicao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        edicao.setPreferredSize(new java.awt.Dimension(320, 25));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Exemplares Disponíveis");

        qtdExemplaresDisponiveis.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        qtdExemplaresDisponiveis.setEnabled(false);
        qtdExemplaresDisponiveis.setPreferredSize(new java.awt.Dimension(320, 25));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Pesquisar");

        campoPesquisaExemp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campoPesquisaExemp.setToolTipText("Digite um título, autor ou número de chamada para pesquisar");
        campoPesquisaExemp.setPreferredSize(new java.awt.Dimension(320, 25));
        campoPesquisaExemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoPesquisaExempActionPerformed(evt);
            }
        });
        campoPesquisaExemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoPesquisaExempKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(268, 268, 268)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(numChamada, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(166, 166, 166)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(edicao, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addGap(31, 31, 31)
                                        .addComponent(editora, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(volume, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(anoPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 373, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(autor, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(qtdExemplares, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(qtdExemplaresDisponiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addComponent(campoPesquisaExemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(270, 270, 270)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(238, 238, 238)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(172, 172, 172)
                                .addComponent(cadastrarExemplar)
                                .addGap(36, 36, 36)
                                .addComponent(atualizaExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(cancelarCadastroExemplar)))
                        .addGap(0, 184, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoPesquisaExemp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(autor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12)
                    .addComponent(qtdExemplaresDisponiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(qtdExemplares, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(volume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(editora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(edicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(numChamada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(anoPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadastrarExemplar)
                    .addComponent(cancelarCadastroExemplar)
                    .addComponent(atualizaExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarCadastroExemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarCadastroExemplarActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_cancelarCadastroExemplarActionPerformed

    private void cadastrarExemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarExemplarActionPerformed
        //Realiza cadastro de exemplar ao clicar no botão cadastrar.
        if ((titulo.getText().isBlank()) || (autor.getText().isBlank()) || (qtdExemplares.getText().isBlank()) || (numChamada.getText().isBlank())) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else {
            qtdExemplaresDisponiveis.setText(qtdExemplares.getText());

            Acervo acervo = new Acervo();
            AcervoDAO dao = new AcervoDAO();

            acervo.setTitulo(titulo.getText());
            acervo.setAutor(autor.getText());
            acervo.setExemplar(Integer.parseInt(qtdExemplares.getText()));
            acervo.setExempDisp(Integer.parseInt(qtdExemplaresDisponiveis.getText()));
            acervo.setVolume(volume.getText());
            acervo.setEdicao(Integer.parseInt(edicao.getText()));
            acervo.setEditora(editora.getText());
            acervo.setAno_publi(Integer.parseInt(anoPublicacao.getText()));
            acervo.setChamada(numChamada.getText());

            dao.create(acervo);

            titulo.setText(null);
            autor.setText(null);
            qtdExemplares.setText(null);
            qtdExemplaresDisponiveis.setText(null);
            volume.setText(null);
            edicao.setText(null);
            editora.setText(null);
            anoPublicacao.setText(null);
            numChamada.setText(null);

            readJTable();
        }

    }//GEN-LAST:event_cadastrarExemplarActionPerformed

    private void atualizaExemplarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_atualizaExemplarKeyReleased
    }//GEN-LAST:event_atualizaExemplarKeyReleased

    private void atualizaExemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizaExemplarActionPerformed
        //Atualizada os dados da linha selecionada no JTable.
        if (tabelaExemplares.getSelectedRow() != -1) {

            Acervo acervo = new Acervo();
            AcervoDAO dao = new AcervoDAO();

            acervo.setTitulo(titulo.getText());
            acervo.setAutor(autor.getText());
            acervo.setExemplar(Integer.parseInt(qtdExemplares.getText()));
            acervo.setExempDisp(Integer.parseInt(qtdExemplaresDisponiveis.getText()));
            acervo.setVolume(volume.getText());
            acervo.setEdicao(Integer.parseInt(edicao.getText()));
            acervo.setEditora(editora.getText());
            acervo.setAno_publi(Integer.parseInt(anoPublicacao.getText()));
            acervo.setChamada(numChamada.getText());
            acervo.setId((int) tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 0));

            dao.update(acervo);

            titulo.setText(null);
            autor.setText(null);
            qtdExemplares.setText(null);
            qtdExemplaresDisponiveis.setText(null);
            volume.setText(null);
            edicao.setText(null);
            editora.setText(null);
            anoPublicacao.setText(null);
            numChamada.setText(null);

            readJTable();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um exemplar para atualizar.");
        }
    }//GEN-LAST:event_atualizaExemplarActionPerformed

    private void tabelaExemplaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaExemplaresKeyReleased
        //Seleciona uma linha da JTable.
        if (tabelaExemplares.getSelectedRow() != -1) {

            titulo.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 1).toString());
            autor.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 2).toString());
            qtdExemplares.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 3).toString());
            qtdExemplaresDisponiveis.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 4).toString());
            volume.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 5).toString());
            edicao.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 6).toString());
            editora.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 7).toString());
            anoPublicacao.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 8).toString());
            numChamada.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 9).toString());

        }
    }//GEN-LAST:event_tabelaExemplaresKeyReleased

    private void tabelaExemplaresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaExemplaresMouseClicked
        //Seleciona uma linha da JTable.
        if (tabelaExemplares.getSelectedRow() != -1) {

            titulo.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 1).toString());
            autor.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 2).toString());
            qtdExemplares.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 3).toString());
            qtdExemplaresDisponiveis.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 4).toString());
            volume.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 5).toString());
            edicao.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 6).toString());
            editora.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 7).toString());
            anoPublicacao.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 8).toString());
            numChamada.setText(tabelaExemplares.getValueAt(tabelaExemplares.getSelectedRow(), 9).toString());

        }
    }//GEN-LAST:event_tabelaExemplaresMouseClicked

    private void numChamadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numChamadaActionPerformed
        //Realiza cadastro de exemplar ao clicar "ENTER" no campo Nº de Chamada.
        if ((titulo.getText().isBlank()) || (autor.getText().isBlank()) || (qtdExemplares.getText().isBlank()) || (numChamada.getText().isBlank())) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else {
            qtdExemplaresDisponiveis.setText(qtdExemplares.getText());

            Acervo acervo = new Acervo();
            AcervoDAO dao = new AcervoDAO();

            acervo.setTitulo(titulo.getText());
            acervo.setAutor(autor.getText());
            acervo.setExemplar(Integer.parseInt(qtdExemplares.getText()));
            acervo.setExempDisp(Integer.parseInt(qtdExemplaresDisponiveis.getText()));
            acervo.setVolume(volume.getText());
            acervo.setEdicao(Integer.parseInt(edicao.getText()));
            acervo.setEditora(editora.getText());
            acervo.setAno_publi(Integer.parseInt(anoPublicacao.getText()));
            acervo.setChamada(numChamada.getText());

            dao.create(acervo);

            titulo.setText(null);
            autor.setText(null);
            qtdExemplares.setText(null);
            qtdExemplaresDisponiveis.setText(null);
            volume.setText(null);
            edicao.setText(null);
            editora.setText(null);
            anoPublicacao.setText(null);
            numChamada.setText(null);

            readJTable();
        }
    }//GEN-LAST:event_numChamadaActionPerformed

    private void tituloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tituloKeyReleased
    }//GEN-LAST:event_tituloKeyReleased

    private void campoPesquisaExempKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPesquisaExempKeyReleased
        lerUsuarioEmprestimo();
    }//GEN-LAST:event_campoPesquisaExempKeyReleased

    private void tituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tituloActionPerformed
    }//GEN-LAST:event_tituloActionPerformed

    private void campoPesquisaExempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPesquisaExempActionPerformed
    }//GEN-LAST:event_campoPesquisaExempActionPerformed

    private void qtdExemplaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtdExemplaresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtdExemplaresActionPerformed

    private void qtdExemplaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtdExemplaresKeyReleased
        //exemplaresInicial = qtdExemplares.getText();
    }//GEN-LAST:event_qtdExemplaresKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anoPublicacao;
    private javax.swing.JButton atualizaExemplar;
    private javax.swing.JTextField autor;
    private javax.swing.JButton cadastrarExemplar;
    private javax.swing.JTextField campoPesquisaExemp;
    private javax.swing.JButton cancelarCadastroExemplar;
    private javax.swing.JTextField edicao;
    private javax.swing.JTextField editora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField numChamada;
    private javax.swing.JTextField qtdExemplares;
    private javax.swing.JTextField qtdExemplaresDisponiveis;
    private javax.swing.JTable tabelaExemplares;
    private javax.swing.JTextField titulo;
    private javax.swing.JTextField volume;
    // End of variables declaration//GEN-END:variables
}
