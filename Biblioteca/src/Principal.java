/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author lyand
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktoppanel = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuAcervo = new javax.swing.JMenu();
        subMenuCadExemplar = new javax.swing.JMenuItem();
        subMenuConsultAcervo = new javax.swing.JMenuItem();
        menuUsuarios = new javax.swing.JMenu();
        subMenuCadUser = new javax.swing.JMenuItem();
        subMenuConsultarUser = new javax.swing.JMenuItem();
        subMenuAdmin = new javax.swing.JMenuItem();
        menuEmpDev = new javax.swing.JMenu();
        subMenuEmp = new javax.swing.JMenuItem();
        subMenuDev = new javax.swing.JMenuItem();
        subMenuConsultExemp = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuSobre = new javax.swing.JMenu();
        subMenuSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(790, 469));

        javax.swing.GroupLayout desktoppanelLayout = new javax.swing.GroupLayout(desktoppanel);
        desktoppanel.setLayout(desktoppanelLayout);
        desktoppanelLayout.setHorizontalGroup(
            desktoppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 766, Short.MAX_VALUE)
        );
        desktoppanelLayout.setVerticalGroup(
            desktoppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
        );

        menuAcervo.setText("Acervo");

        subMenuCadExemplar.setText("Cadastrar Exemplar");
        subMenuCadExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuCadExemplarActionPerformed(evt);
            }
        });
        menuAcervo.add(subMenuCadExemplar);

        subMenuConsultAcervo.setText("Consultar Acervo");
        subMenuConsultAcervo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuConsultAcervoActionPerformed(evt);
            }
        });
        menuAcervo.add(subMenuConsultAcervo);

        jMenuBar1.add(menuAcervo);

        menuUsuarios.setText("Usuários");

        subMenuCadUser.setText("Cadastrar Usuários");
        subMenuCadUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuCadUserActionPerformed(evt);
            }
        });
        menuUsuarios.add(subMenuCadUser);

        subMenuConsultarUser.setText("Usuários Cadastrados");
        subMenuConsultarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuConsultarUserActionPerformed(evt);
            }
        });
        menuUsuarios.add(subMenuConsultarUser);

        subMenuAdmin.setText("Administradores");
        subMenuAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuAdminActionPerformed(evt);
            }
        });
        menuUsuarios.add(subMenuAdmin);

        jMenuBar1.add(menuUsuarios);

        menuEmpDev.setText("Empréstimo/Devolução");

        subMenuEmp.setText("Empréstimo");
        subMenuEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuEmpActionPerformed(evt);
            }
        });
        menuEmpDev.add(subMenuEmp);

        subMenuDev.setText("Devolução");
        subMenuDev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuDevActionPerformed(evt);
            }
        });
        menuEmpDev.add(subMenuDev);

        subMenuConsultExemp.setText("Consultar Empréstimos");
        subMenuConsultExemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuConsultExempActionPerformed(evt);
            }
        });
        menuEmpDev.add(subMenuConsultExemp);

        jMenuItem1.setText("Consultar Devolução");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuEmpDev.add(jMenuItem1);

        jMenuBar1.add(menuEmpDev);

        menuSobre.setText("Sobre");
        menuSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSobreActionPerformed(evt);
            }
        });

        subMenuSobre.setText("Sobre");
        subMenuSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuSobreActionPerformed(evt);
            }
        });
        menuSobre.add(subMenuSobre);

        jMenuBar1.add(menuSobre);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktoppanel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktoppanel)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Chama a tela de cadastro de exemplar.
    private void subMenuCadExemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuCadExemplarActionPerformed
        CadastroExemplar cadastroexemplar = new CadastroExemplar();
        desktoppanel.add(cadastroexemplar);
        cadastroexemplar.setPosicao();
        cadastroexemplar.setVisible(true);
    }//GEN-LAST:event_subMenuCadExemplarActionPerformed

    //Chama a tela de consulta de acervo.
    private void subMenuConsultAcervoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuConsultAcervoActionPerformed
        ConsultarAcervo consultaacervo = new ConsultarAcervo();
        desktoppanel.add(consultaacervo);
        consultaacervo.setPosicao();
        consultaacervo.setVisible(true);
    }//GEN-LAST:event_subMenuConsultAcervoActionPerformed

    //Chama a tela de cadastro de usuário.
    private void subMenuCadUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuCadUserActionPerformed
        CadastrarUsuarios cadusers = new CadastrarUsuarios();
        desktoppanel.add(cadusers);
        cadusers.setPosicao();
        cadusers.setVisible(true);
    }//GEN-LAST:event_subMenuCadUserActionPerformed

    //Chama a tela de consulta de usuário.
    private void subMenuConsultarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuConsultarUserActionPerformed
        UsuariosCadastrados userscad = new UsuariosCadastrados();
        desktoppanel.add(userscad);
        userscad.setPosicao();
        userscad.setVisible(true);
    }//GEN-LAST:event_subMenuConsultarUserActionPerformed

    ////Chama a tela de consulta de administradores.
    private void subMenuAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuAdminActionPerformed
        Administradores adms = new Administradores();
        desktoppanel.add(adms);
        adms.setPosicao();
        adms.setVisible(true);
    }//GEN-LAST:event_subMenuAdminActionPerformed

    ////Chama a tela de empréstimo.
    private void subMenuEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuEmpActionPerformed
        Emprestimo emp = new Emprestimo();
        desktoppanel.add(emp);
        emp.setPosicao();
        emp.setVisible(true);
    }//GEN-LAST:event_subMenuEmpActionPerformed

    //Chama a tela de devolução.
    private void subMenuDevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuDevActionPerformed
        Devolucao dev = new Devolucao();
        desktoppanel.add(dev);
        dev.setPosicao();
        dev.setVisible(true);
    }//GEN-LAST:event_subMenuDevActionPerformed

    //Chama a tela de consulta de empréstimo.
    private void subMenuConsultExempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuConsultExempActionPerformed
        ConsultarEmprestimo consultempres = new ConsultarEmprestimo();
        desktoppanel.add(consultempres);
        consultempres.setPosicao();
        consultempres.setVisible(true);
    }//GEN-LAST:event_subMenuConsultExempActionPerformed

    private void menuSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSobreActionPerformed

    }//GEN-LAST:event_menuSobreActionPerformed

    ////Chama a tela de informações sobre o software.
    private void subMenuSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuSobreActionPerformed
        Sobre sobre = new Sobre();
        desktoppanel.add(sobre);
        sobre.setPosicao();
        sobre.setVisible(true);
    }//GEN-LAST:event_subMenuSobreActionPerformed

    //Chama a tela de consulta de devoluções.
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ConsultarDevolucao consultdev = new ConsultarDevolucao();
        desktoppanel.add(consultdev);
        consultdev.setPosicao();
        consultdev.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktoppanel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu menuAcervo;
    private javax.swing.JMenu menuEmpDev;
    private javax.swing.JMenu menuSobre;
    private javax.swing.JMenu menuUsuarios;
    private javax.swing.JMenuItem subMenuAdmin;
    private javax.swing.JMenuItem subMenuCadExemplar;
    private javax.swing.JMenuItem subMenuCadUser;
    private javax.swing.JMenuItem subMenuConsultAcervo;
    private javax.swing.JMenuItem subMenuConsultExemp;
    private javax.swing.JMenuItem subMenuConsultarUser;
    private javax.swing.JMenuItem subMenuDev;
    private javax.swing.JMenuItem subMenuEmp;
    private javax.swing.JMenuItem subMenuSobre;
    // End of variables declaration//GEN-END:variables
}
