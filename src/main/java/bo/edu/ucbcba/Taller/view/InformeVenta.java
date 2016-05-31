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
import java.util.List;


public class InformeVenta extends JDialog {
    private JPanel rootPanel;
    private JTable saleTable;
    private JTextField searchText;
    private JButton editButton;
    private JButton searchButton;
    private JButton cancelButton;
    private JButton deleteButton;

    private StockController controllerstock;
    private SaleController controllersale;

    public InformeVenta(JFrame parent) {

        super(parent, "Historial de ventas", true);
        setContentPane(rootPanel);
        pack();
        setResizable(false);
        setSize(900, 400);

        controllerstock = new StockController();
        controllersale = new SaleController();
        populateTable();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == deleteButton) {
                    JOptionPane.showMessageDialog(null,"Porfavor seleccione una venta para eliminar");
                } else {
                    DefaultTableModel tm = (DefaultTableModel) saleTable.getModel();
                    int id = (Integer) tm.getValueAt(saleTable.getSelectedRow(), 0);
                    controllersale.delete(id);
                    populateTable();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    private void cancel() {
        setVisible(false);
        dispose();
    }

    private void populateTable() {

        List<Sale> sale = controllersale.searchSale(searchText.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Cantidad");
        model.addColumn("Descripción");
        model.addColumn("Precio Unitario");
        model.addColumn("Importe");
        model.addColumn("Fecha");
        saleTable.setModel(model);

        for (Sale m : sale) {
            Object[] row = new Object[5];

            row[0] = m.getcant();
            row[1] = m.getstocks();
            row[2] = m.getPrice();
            row[3] = m.gettotal();
            row[4] = m.getD();
            model.addRow(row);
        }

    }
}