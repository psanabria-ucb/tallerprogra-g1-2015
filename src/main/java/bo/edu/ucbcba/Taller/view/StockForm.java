package bo.edu.ucbcba.Taller.view;

import bo.edu.ucbcba.Taller.controller.StockController;
import bo.edu.ucbcba.Taller.model.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StockForm extends JFrame {
    private JPanel rootPanel;
    private JTextField searchText;
    private JButton searchButton;
    private JTable stockTable;
    private JButton createButton;
    private StockController stockController;

    public StockForm() {
        super("Stock");
        setContentPane(rootPanel);
        setSize(600, 400);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchRegister();
            }
        });
        stockController = new StockController();
        populateTable();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });
    }


    private void launchRegister() {
        RegisterStockForm form = new RegisterStockForm(this);

        form.setVisible(true);
        populateTable();
    }

    private void populateTable() {
        List<Stock> movies = stockController.searchStock(searchText.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Code");
        model.addColumn("Quantity");
        model.addColumn("Cost");
        stockTable.setModel(model);

        for (Stock m : movies) {
            Object[] row = new Object[5];

            row[0] = m.getName();
            row[1] = m.getCost();
            row[2] = m.getCode();
            row[3] = m.getQuantity();
            model.addRow(row);
        }
    }
}






