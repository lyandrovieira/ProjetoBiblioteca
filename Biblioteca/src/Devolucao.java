
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

/**
 *
 * @author lyand
 */
public class Devolucao extends javax.swing.JInternalFrame {

    /**
     * Creates new form Devolucao
     */
    public Devolucao() {
        initComponents();
        setDate();
        setClosable(true);
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    public void setDate() {
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataDevolucao.setText(today.format(formatador));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        codigoDevolucao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        usuarioDevolucao = new javax.swing.JTextField();
        confirmarDevolucao = new javax.swing.JButton();
        cancelarDevolucao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDevolucao = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        dataDev = new javax.swing.JLabel();
        dataDevolucao = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Devolução de Exemplar");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Nº de Chamada *");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Usuário *");

        confirmarDevolucao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        confirmarDevolucao.setText("Confirmar");
        confirmarDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarDevolucaoActionPerformed(evt);
            }
        });

        cancelarDevolucao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cancelarDevolucao.setText("Cancelar");
        cancelarDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarDevolucaoActionPerformed(evt);
            }
        });

        tabelaDevolucao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº de Chamada", "Usuário", "Data Devolução"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaDevolucao);

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Campos marcados com  *  são obrigatórios");

        dataDev.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        dataDev.setText("Data Devolução");

        dataDevolucao.setEditable(false);
        dataDevolucao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usuarioDevolucao))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(269, 269, 269)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(235, 235, 235)
                            .addComponent(jLabel4))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(codigoDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(215, 215, 215)
                                    .addComponent(confirmarDevolucao)))
                            .addGap(65, 65, 65)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cancelarDevolucao)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(dataDev)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dataDevolucao))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(usuarioDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(codigoDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataDev)
                    .addComponent(dataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmarDevolucao)
                    .addComponent(cancelarDevolucao))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarDevolucaoActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_cancelarDevolucaoActionPerformed

    private void confirmarDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarDevolucaoActionPerformed
        if ((codigoDevolucao.getText().isBlank()) || (usuarioDevolucao.getText().isBlank())) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório em branco!");
        } else {
            DefaultTableModel tblDevolucao = (DefaultTableModel) tabelaDevolucao.getModel();
            Object[] dadosDevolucao = {codigoDevolucao.getText(), usuarioDevolucao.getText(), dataDevolucao.getText()};
            tblDevolucao.addRow(dadosDevolucao);
            codigoDevolucao.setText(null);
            usuarioDevolucao.setText(null);
            JOptionPane.showMessageDialog(null, "A Devolução foi realizada com sucesso!");
        }
    }//GEN-LAST:event_confirmarDevolucaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarDevolucao;
    private javax.swing.JTextField codigoDevolucao;
    private javax.swing.JButton confirmarDevolucao;
    private javax.swing.JLabel dataDev;
    private javax.swing.JTextField dataDevolucao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaDevolucao;
    private javax.swing.JTextField usuarioDevolucao;
    // End of variables declaration//GEN-END:variables
}
