package bo.edu.ucbcba.Taller.view;

import bo.edu.ucbcba.Taller.controller.StockController;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterStockForm extends JDialog {
    private JPanel rootPanel;
    private JTextField cost;
    private JTextField name;
    private JTextField code;
    private JButton cancelButton;
    private JButton saveButton;
    private JTextField quantity;
    private StockController controller;

    public RegisterStockForm(JFrame parent) {
        super(parent, "Registrar Repuesto", true);
        setContentPane(rootPanel);
        pack();
        setResizable(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        controller = new StockController();
    }
    private void saveUser() {
        try {
            controller.create(name.getText(),
                              quantity.getText(),
                              cost.getText(),
                              code.getText());
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Movie created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        cancel();
    }

    private void cancel() {
        setVisible(false);
        dispose();
    }
}




