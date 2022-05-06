import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//import okhttp3.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.json.*;

import javax.swing.*;

public class FormConv extends JFrame {
    private JTextArea txtInput;
    private JTextArea txtOutput;
    private JButton btn7;
    private JButton btn4;
    private JButton btn1;
    private JButton btn8;
    private JButton btn5;
    private JButton btn2;
    private JButton btn9;
    private JButton btn6;
    private JButton btn3;
    private JButton btnAC;
    private JButton btn0;
    private JButton btnDEL;
    private JComboBox cmbSorg;
    private JComboBox cmbDest;
    private JRadioButton radioBtnInverti;
    private JPanel panel1;
    private JButton btnCalc;
    private JButton btnVirgola;

    public FormConv() {
        setTitle("Convertitore");
        setContentPane(panel1);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(270,270));


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        for(Window window: Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(window);
        }

        pack();

        try {
            jsonRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setResizable(false);
        setVisible(true);
        
        radioBtnInverti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioBtnInverti.isSelected())
                {
                    //inversione dei valori nelle combobox
                    Valuta temp = (Valuta) cmbSorg.getSelectedItem();
                    cmbSorg.setSelectedItem(cmbDest.getSelectedItem());
                    cmbDest.setSelectedItem(temp);
                    calcola();
                }
            }
        });

        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Form();
            }
        });
        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.append("0");
            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.append("1");
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.append("2");
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.append("3");
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.append("4");
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.append("5");
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.append("6");
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.append("7");
            }
        });
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.append("8");
            }
        });
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.append("9");
            }
        });

        btnAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.setText("");
            }
        });

        btnDEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = txtInput.getText();
                if(input.length() > 0)
                    txtInput.setText(input.substring(0, input.length() - 1));
            }
        });

        txtInput.getDocument().addDocumentListener(new DocumentListener() {

            public void removeUpdate(DocumentEvent e) {
                try {
                    calcola();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            public void insertUpdate(DocumentEvent e) {
                try {
                    calcola();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            public void changedUpdate(DocumentEvent e) {
                try {
                    calcola();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel1.addContainerListener(new ContainerAdapter() {
        });

        btnVirgola.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!txtInput.getText().contains("."))
                    txtInput.append(".");
            }
        });

        cmbDest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcola();
            }
        });
        cmbSorg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcola();
            }
        });
        txtInput.addComponentListener(new ComponentAdapter() {
        });
    }

    private void calcola()
    {
        try {
            if (txtInput.getText().length() > 0) {
                double input = Double.parseDouble(txtInput.getText());
                double risultato = input * ((Valuta) cmbDest.getSelectedItem()).getValore() / ((Valuta) cmbSorg.getSelectedItem()).getValore();
                NumberFormat nf = NumberFormat.getNumberInstance(new Locale("it", "IT"));
                DecimalFormat df = (DecimalFormat)nf;
                df.applyPattern("###,###.00");
                txtOutput.setText(df.format(risultato));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jsonRequest() throws Exception {
        final String MY_API_KEY = "MDF2I8M9fezctVGIAHB3apjBQbVYgAMd";
        URL url = new URL("https://ec.europa.eu/budg/inforeuro/api/public/monthly-rates");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int respcode = conn.getResponseCode();
        if (respcode != HttpURLConnection.HTTP_OK) {
            System.err.println("Got HTTP Response code: " + respcode);
            return;
        }
        InputStream in = conn.getInputStream();
        BufferedReader reader = new BufferedReader( new InputStreamReader( in ) );
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line=reader.readLine()) != null) sb.append(line);
        reader.close();
        // The response data as a String
        String data = sb.toString();
        String jsonString = data ; //assign your JSON String here
        JSONArray array = new JSONArray(jsonString);

        Valuta[] valute = new Valuta[array.length()];

        for (int i = 0; i < array.length(); i++)
        {
            String nome = array.getJSONObject(i).getString("currency");
            Double valore = array.getJSONObject(i).getDouble("value");
            String simbolo = array.getJSONObject(i).getString("isoA3Code");
            valute[i] = new Valuta(nome, valore, simbolo);
        }

        if(cmbSorg.getItemCount() == 0) {
            //cmbSorg.setPreferredSize(new Dimension(120, 20));
            //cmbDest.setPreferredSize(new Dimension(120, 20));
            cmbSorg.setModel(new DefaultComboBoxModel(valute));
            cmbDest.setModel(new DefaultComboBoxModel(valute));
        }

    }



    public static void main(String[] args) {
        new FormConv();;
    }


}
