/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JOptionPane;

/**
 *
 * @author Bruno
 */
public class MenuPause extends javax.swing.JFrame {

    /**
     * Creates new form MenuPause
     */
    Fase pai;
    
    public MenuPause(Fase pai) {
        this.pai = pai;
        initComponents();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMsgPausado = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnTrocaDificuldade = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 0));
        setForeground(new java.awt.Color(255, 153, 0));

        lblMsgPausado.setText("PAUSADO");

        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnReiniciar.setText("Recomeçar");
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnTrocaDificuldade.setText("Trocar dificuldade");
        btnTrocaDificuldade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrocaDificuldadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTrocaDificuldade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContinuar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(lblMsgPausado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblMsgPausado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTrocaDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        pai.pause(false);
        this.pai.getPai().setEnabled(true);
        this.pai.getPai().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        pai.endGame();
        pai.inicializarFlags();
        pai.pause(false);
        this.pai.getPai().setEnabled(true);
        this.pai.getPai().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        pai.endGame();
        this.pai.getPai().setEnabled(true);
        this.pai.getPai().setVisible(true);
        pai.getPai().sairJogo();
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    MenuDificuldade md;
    
    private void btnTrocaDificuldadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrocaDificuldadeActionPerformed
        JOptionPane.showMessageDialog(this, "A fase será reiniciada");
        md = new MenuDificuldade(this);
        md.setVisible(true);
        md.setLocationRelativeTo(null);
        this.setEnabled(false);
    }//GEN-LAST:event_btnTrocaDificuldadeActionPerformed

    public void trocaDificuldade(int mode){
        pai.endGame();
        this.pai.setMode(mode);
        pai.inicializarFlags();
        pai.pause(false);
        this.pai.getPai().setEnabled(true);
        this.pai.getPai().setVisible(true);
        this.dispose();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnTrocaDificuldade;
    private javax.swing.JLabel lblMsgPausado;
    // End of variables declaration//GEN-END:variables
}
