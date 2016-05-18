package bo.edu.ucbcba.Taller.view;

import bo.edu.ucbcba.Taller.controller.StockController;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class RegisterStockForm extends JDialog {
    private JPanel rootPanel;
    private JTextField cost;
    private JTextField name;
    private JTextField code;
    private JButton cancelButton;
    private JButton saveButton;
    private JTextField quantity;
    private StockController controller;


    private JTextField searchText;
    private JButton searchButton;
    private JTable stockTable;
    private JButton deletebutton;

    public RegisterStockForm(JFrame parent) {
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
        controller = new StockController();
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
                deletestock();
            }
        });
    }

    public void deletestock() {
        DefaultTableModel tm = (DefaultTableModel) stockTable.getModel();
        int id = (Integer) tm.getValueAt(stockTable.getSelectedRow(), 0);
        controller.delete(id);
        populateTable();
    }

    private void saveUser() {
        try {
            controller.create(name.getText(),
                              cost.getText(),
                              code.getText(),
                              quantity.getText());
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }
      //  JOptionPane.showMessageDialog(this, "Stock created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cancel() {
        setVisible(false);
        dispose();
    }

    private void populateTable() {
        java.util.List<Stock> movies = controller.searchStock(searchText.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Code");
        model.addColumn("Quantity");
        model.addColumn("Cost");
        stockTable.setModel(model);

        for (Stock m : movies) {
            Object[] row = new Object[5];

            row[0] = m.getId();
            row[1] = m.getName();
            row[2] = m.getCost();
            row[3] = m.getCode();
            row[4] = m.getQuantity();
            model.addRow(row);
        }
    }
}










