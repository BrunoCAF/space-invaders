package views;

public class MenuDificuldade extends javax.swing.JFrame {

    
    MenuPause mp;
    Fase fs;
    TelaHome th;
    private int mode;
    
    public MenuDificuldade(MenuPause pai) {
        this.mp = pai;
        initComponents();
    }
    
    public MenuDificuldade(Fase pai){
        this.fs = pai;
        initComponents();
    }

    public MenuDificuldade(TelaHome pai){
        this.th = pai;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnOk = new javax.swing.JButton();
        rbtnDificil = new javax.swing.JRadioButton();
        rbtnFacil = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnDificil);
        rbtnDificil.setText("Difícil");

        buttonGroup1.add(rbtnFacil);
        rbtnFacil.setText("Fácil");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnFacil)
                    .addComponent(rbtnDificil))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rbtnFacil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnDificil))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnOk)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        if(rbtnFacil.isSelected() || rbtnDificil.isSelected()){
            if(rbtnFacil.isSelected()) mode = 1;
            else mode = 2;
            if(mp != null){
                this.mp.setEnabled(true);
                this.mp.trocaDificuldade(mode);
                this.dispose();
            }else if(fs != null){
                this.fs.setMode(mode);
                this.fs.inicializarFlags();
                this.fs.pause(false);
                this.fs.getPai().setEnabled(true);
                this.fs.getPai().setVisible(true);
                this.dispose();
            }else if(th != null){
                this.th.setMode(mode);
                this.th.unloggedStart();
                this.th.setEnabled(true);
                this.th.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_btnOkActionPerformed

    
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton rbtnDificil;
    private javax.swing.JRadioButton rbtnFacil;
    // End of variables declaration//GEN-END:variables
}
