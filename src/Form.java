import java.awt.Dimension;
import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.*;


public class Form extends javax.swing.JFrame {

    private boolean divisione,somma,sottrazione,moltiplicazione;

    public Form() {
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 200));
        this.setResizable(false);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e) {
            System.out.println("\n" + e.getMessage());
        }
        String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/GNOME_Calculator_icon_2021.svg/2048px-GNOME_Calculator_icon_2021.svg.png";
        String destinationFile = "image.png";

        try {
            saveImage(imageUrl, destinationFile);
        }catch(Exception ex)
        {
            System.out.println("\n" + ex.getMessage());
        }

        ImageIcon img = new ImageIcon(new ImageIcon("image.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
        this.setIconImage(img.getImage());
        this.pack();
        this.setVisible(true);
        txtInput1.setMaximumSize(new Dimension(60,20));
        txtInput2.setMaximumSize(new Dimension(60,20));
        lblRisultato.setMaximumSize(new Dimension(60,20));
        btnCalcola.setFocusPainted(false);
        btnDivisione.setFocusPainted(false);
        btnSottrazione.setFocusPainted(false);
        btnSomma.setFocusPainted(false);
        btnMoltiplicazione.setFocusPainted(false);
    }


    private static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }

    private void initComponents() {

        JPanel jPanel1 = new JPanel();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        txtInput1 = new javax.swing.JTextField();
        txtInput2 = new javax.swing.JTextField();
        btnCalcola = new javax.swing.JButton();
        lblRisultato = new javax.swing.JLabel();
        btnSomma = new javax.swing.JButton();
        btnSottrazione = new javax.swing.JButton();
        btnMoltiplicazione = new javax.swing.JButton();
        btnDivisione = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("num. 1:");
        jLabel1.setToolTipText("");

        jLabel2.setText("num. 2:");

        txtInput2.setToolTipText("");

        btnCalcola.setText("Calcola");
        btnCalcola.addActionListener(evt -> btnCalcolaActionPerformed());

        lblRisultato.setText("Risultato: ");
        lblRisultato.setToolTipText("");

        btnSomma.setText("Somma");
        btnSomma.addActionListener(this::btnSommaActionPerformed);

        btnSottrazione.setText("Sottrazione");
        btnSottrazione.addActionListener(this::btnSottrazioneActionPerformed);

        btnMoltiplicazione.setText("Moltiplicazione");
        btnMoltiplicazione.addActionListener(this::btnMoltiplicazioneActionPerformed);

        btnDivisione.setText("Divisione");
        btnDivisione.addActionListener(this::btnDivisioneActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtInput1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtInput2))
                                                        .addComponent(btnCalcola, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btnMoltiplicazione, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnSomma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btnSottrazione, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnDivisione, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(lblRisultato))
                                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtInput1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSomma)
                                        .addComponent(btnSottrazione))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtInput2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnMoltiplicazione)
                                        .addComponent(btnDivisione))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCalcola)
                                .addGap(18, 18, 18)
                                .addComponent(lblRisultato)
                                .addContainerGap(166, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void btnCalcolaActionPerformed() {
        lblRisultato.setText("Risultato: ");
        double ris=Math.PI;
        boolean error1=false,error2=false;

        try{

            if (txtInput1.getText().matches( "^[a-zA-Z]*$") && txtInput1.getText()!=null)
            {
                error1=true;
            }

            if (txtInput2.getText().matches( "^[a-zA-Z]*$") && txtInput2.getText()!=null)
            {
                error2=true;
            }

            if(error1 && error2)
            {
                throw new Exception("Gli input non sono validi");
            }else if(error1)
                throw new Exception("Il primo input non è valido");
            else if(error2)
                throw new Exception("Il secondo input non è valido");

            if(somma)
                ris = Double.parseDouble(txtInput1.getText()) + Double.parseDouble(txtInput2.getText());

            if(divisione)
                if(!txtInput2.getText().equals("0"))
                    ris = Double.parseDouble(txtInput1.getText()) / Double.parseDouble(txtInput2.getText());
                else
                    throw new Exception("IMPOSSIBILE/INDEFINITO");

            if(moltiplicazione)
                ris = Double.parseDouble(txtInput1.getText()) * Double.parseDouble(txtInput2.getText());

            if(sottrazione)
                ris = Double.parseDouble(txtInput1.getText()) - Double.parseDouble(txtInput2.getText());

            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ITALIAN);
            DecimalFormat df = new DecimalFormat("#.####", otherSymbols);
            if(ris!=Math.PI)
                lblRisultato.setText(lblRisultato.getText() + df.format(ris));
            else
                throw new Exception("Selezionare prima un operando (somma, sottrazione, moltiplicazione o divisione)");

        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
            txtInput1.setText("");
            txtInput2.setText("");
        }
    }//GEN-LAST:event_btnCalcolaActionPerformed

    private void btnDivisioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDivisioneActionPerformed
        // TODO add your handling code here:
        divisione=true;
        moltiplicazione=false;
        somma=false;
        sottrazione=false;
    }//GEN-LAST:event_btnDivisioneActionPerformed

    private void btnSommaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSommaActionPerformed
        // TODO add your handling code here:
        divisione=false;
        moltiplicazione=false;
        somma=true;
        sottrazione=false;
    }

    private void btnMoltiplicazioneActionPerformed(java.awt.event.ActionEvent evt) {
        divisione=false;
        moltiplicazione=true;
        somma=false;
        sottrazione=false;
    }

    private void btnSottrazioneActionPerformed(java.awt.event.ActionEvent evt) {
        divisione=false;
        moltiplicazione=false;
        somma=false;
        sottrazione=true;
    }

    private javax.swing.JButton btnCalcola;
    private javax.swing.JButton btnDivisione;
    private javax.swing.JButton btnMoltiplicazione;
    private javax.swing.JButton btnSomma;
    private javax.swing.JButton btnSottrazione;
    private javax.swing.JLabel lblRisultato;
    private javax.swing.JTextField txtInput1;
    private javax.swing.JTextField txtInput2;
    // End of variables declaration//GEN-END:variables
}
