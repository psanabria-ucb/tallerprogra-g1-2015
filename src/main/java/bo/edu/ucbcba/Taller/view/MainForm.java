package bo.edu.ucbcba.Taller.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Usuario on 18/05/2016.
 */
public class MainForm extends JFrame {
    private JButton stockButton;
    private JButton facturacionButton;
    private JPanel rootPanel;

    private JButton ventasButton;
    private JButton salirButton;
    private JButton mantenimientoButton;

    public MainForm() {
        super("TALLER DE MANTENIMIENTO");
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

        mantenimientoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });


        ventasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventasregister();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
    }

    private void exit() {
        setVisible(false);
        dispose();
    }

    private void ventasregister() {
        RegisterSaleForm form = new RegisterSaleForm(this);
        form.setVisible(true);
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