package bo.edu.ucbcba.Taller.view;

import bo.edu.ucbcba.Taller.controller.UserController;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Usuario on 16/05/2016.
 */
public class RegisterUserForm extends JDialog {
    private JTextField name;
    private JTextField usuario;
    private JTextField password;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField searchText;
    private JButton searchButton;
    private JTable UserTable;
    private JButton deletebutton;
    private JPanel rootPanel;
    private UserController controller;
    public RegisterUserForm(JFrame parent) {
        super(parent, "Registrar Repuesto", true);
        setContentPane(rootPanel);
        pack();
        setResizable(false);
        setSize(600, 400);
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
        controller = new UserController();
        populateTable();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });
        deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteuser();
            }
        });
    }
    public void deleteuser() {
        DefaultTableModel tm = (DefaultTableModel) UserTable.getModel();
        int id = (Integer) tm.getValueAt(UserTable.getSelectedRow(), 0);
        controller.delete(id);
        populateTable();
    }

    private void populateTable() {
        java.util.List<User> mo = controller.search(searchText.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("Nombre de usuario");
        model.addColumn("Contraseña");
        UserTable.setModel(model);

        for (User m : mo) {
            Object[] row = new Object[5];

            row[0] = m.getId();
            row[1] = m.getName();
            row[2] = m.getUsername();
            row[3] = m.getPassword();
            model.addRow(row);
        }
    }

    private void saveUser() {
        try {
            controller.create(name.getText(),
                              usuario.getText(),
                              password.getText());
            populateTable();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }
       // JOptionPane.showMessageDialog(this, "User created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cancel() {
        setVisible(false);
        dispose();
    }

}
