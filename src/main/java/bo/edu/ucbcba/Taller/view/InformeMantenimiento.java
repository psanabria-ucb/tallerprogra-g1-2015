package bo.edu.ucbcba.Taller.view;

import javax.swing.*;

/**
 * Created by Osmar on 30/05/2016.
 */
public class InformeMantenimiento extends JDialog {
    private JButton ACEPTARButton;
    private JTable table1;
    private JPanel rootPanel;
    private JComboBox comboBox1;

    public InformeMantenimiento(JFrame parent){
        super(parent, "Informe de Matenimiento", true);
        setContentPane(rootPanel);

        pack();
        setSize(800, 600);
        setResizable(false);
    }
}
