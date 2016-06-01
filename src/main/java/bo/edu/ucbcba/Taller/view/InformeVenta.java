package bo.edu.ucbcba.Taller.view;

import bo.edu.ucbcba.Taller.controller.SaleController;
import bo.edu.ucbcba.Taller.controller.StockController;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Sale;
import bo.edu.ucbcba.Taller.model.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class InformeVenta extends JDialog {
    private JPanel rootPanel;
    private JTable saleTable;
    private JTextField searchText;
    private JButton searchButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JTextField canti;
    private JTextField day;
    private JTextField month;
    private JComboBox stockcomboBox;
    private JTextField year;
    private JButton saveButton;
    private JButton editButton;
    private JButton mostrarDatosButton;

    private StockController controllerstock;
    private SaleController controllersale;

    public InformeVenta(JFrame parent) {

        super(parent, "Ventas", true);
        setContentPane(rootPanel);
        pack();
        setResizable(false);
        setSize(600, 400);

        controllerstock = new StockController();
        controllersale = new SaleController();
     //   populateTable();
        populateComboBox();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              deletesale();
            }
        });
        mostrarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
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
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        saleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                super.mouseClicked(e);
                Stock s = (Stock) saleTable.getValueAt(saleTable.getSelectedRow(), 2);
                Integer cantidad = (Integer) saleTable.getValueAt(saleTable.getSelectedRow(), 1);
                String fecha = (String) saleTable.getValueAt(saleTable.getSelectedRow(), 5);

                stockcomboBox.getSelectedObjects();
                canti.setText(Integer.toString(cantidad));
                day.setText(fecha);
            }
        });

    }

    private void actualizar(){
            DefaultTableModel tm = (DefaultTableModel) saleTable.getModel();
            if (saleTable.getSelectedRowCount() > 0) {
                int id = (Integer) tm.getValueAt(saleTable.getSelectedRow(), 0);
                // Integer cod = (Integer) stockTable.getValueAt(stockTable.getSelectedRow(), 0);
                controllersale.delete(id);
                Boolean entro = true;
                try {
                    Stock s = (Stock) stockcomboBox.getSelectedItem();
                    controllersale.create(s,
                            canti.getText(),
                            day.getText());
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "error de formato", JOptionPane.ERROR_MESSAGE);
                    entro = false;
                }
                if (entro) {
                    JOptionPane.showMessageDialog(this, "Elemento actualizado correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
                    clearstock();
                }
                populateTable();
            } else {
                try {
                    controllersale.getSale(0);

                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de selección", JOptionPane.ERROR_MESSAGE);
                }
            }
    }

    private void clearstock()
    {
        canti.setText("");
        day.setText("");
    }

        private void deletesale() {
            DefaultTableModel tm = (DefaultTableModel) saleTable.getModel();
            if (saleTable.getSelectedRowCount() > 0) {
                int id = (Integer) tm.getValueAt(saleTable.getSelectedRow(), 0);
                controllersale.delete(id);
                populateTable();
            } else {
                try {
                    controllersale.delete(0);
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de selección", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    private void populateComboBox() {
        List<Stock> stocks = controllerstock.getAllStock();
        for (Stock s : stocks) {
            stockcomboBox.addItem(s);
        }
    }

    private void saveUser() {
        try {
            Stock s = (Stock) stockcomboBox.getSelectedItem();
            controllersale.create(s,
                                canti.getText(),
                                day.getText());
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }
        populateTable();
    }

    private void cancel() {
        setVisible(false);
        dispose();
    }

    private void populateTable() {

        List<Sale> sale = controllersale.show();


        sale = controllersale.searchSalebydate(searchText.getText());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Cantidad");
        model.addColumn("Descripción");
        model.addColumn("Precio Unitario");
        model.addColumn("Importe");
        model.addColumn("Fecha");
        saleTable.setModel(model);

        for (Sale m : sale) {
            Object[] row = new Object[6];

            row[0] = m.getId();
            row[1] = m.getcant();
            row[2] = m.getstocks();
            row[3] = m.getPrice();
            row[4] = m.gettotal();
            row[5] = m.getD();
            model.addRow(row);
        }

    }
}