package bo.edu.ucbcba.Taller.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Usuario on 18/05/2016.
 */
public class MainForm extends JFrame {
    private JButton stockButton;
    private JButton userButton;
    private JButton maintenanceButton;
    private JButton facturacionButton;
    private JPanel rootPanel;

    public MainForm() {
        super("TALLER DE MATENIMIENTO");
        setContentPane(rootPanel);
        setSize(600, 400);

        facturacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register();
            }
        });

        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchRegister();
            }
        });

        maintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });
    }

    private void launchRegister() {
        RegisterStockForm form = new RegisterStockForm(this);
        form.setVisible(true);
    }

    private void populateTable() {
        RegisterMaintenance f = new RegisterMaintenance(this);
        f.setVisible(true);
    }
    private void Register() {
        RegisterFact f = new RegisterFact(this);
        f.setVisible(true);
    }


}
