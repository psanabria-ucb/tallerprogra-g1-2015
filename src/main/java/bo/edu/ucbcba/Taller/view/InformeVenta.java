package bo.edu.ucbcba.Taller.view;

import bo.edu.ucbcba.Taller.controller.SaleController;
import bo.edu.ucbcba.Taller.controller.StockController;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Sale;
import bo.edu.ucbcba.Taller.model.Stock;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
            public void mouseClicked(MouseEvent e) {
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

    private void actualizar() {
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

    private void clearstock() {
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(9, 16, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Busca por fecha");
        rootPanel.add(label1, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(98, 73), null, 3, false));
        canti = new JTextField();
        rootPanel.add(canti, new GridConstraints(2, 1, 1, 15, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        day = new JTextField();
        day.setText("");
        rootPanel.add(day, new GridConstraints(3, 1, 1, 15, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        stockcomboBox = new JComboBox();
        rootPanel.add(stockcomboBox, new GridConstraints(1, 1, 1, 15, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Descripción");
        rootPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JLabel label3 = new JLabel();
        label3.setText("Cantidad");
        rootPanel.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Fecha(mes/día/año)");
        rootPanel.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchText = new JTextField();
        rootPanel.add(searchText, new GridConstraints(6, 2, 1, 10, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Guardar");
        rootPanel.add(saveButton, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Registrar Venta");
        rootPanel.add(label5, new GridConstraints(0, 1, 1, 15, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editButton = new JButton();
        editButton.setText("Editar");
        rootPanel.add(editButton, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        rootPanel.add(scrollPane1, new GridConstraints(7, 0, 1, 16, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(453, 159), null, 0, false));
        saleTable = new JTable();
        scrollPane1.setViewportView(saleTable);
        deleteButton = new JButton();
        deleteButton.setText("Eliminar");
        rootPanel.add(deleteButton, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Salir");
        rootPanel.add(cancelButton, new GridConstraints(5, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Buscar");
        rootPanel.add(searchButton, new GridConstraints(6, 12, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mostrarDatosButton = new JButton();
        mostrarDatosButton.setText("Mostrar Datos");
        rootPanel.add(mostrarDatosButton, new GridConstraints(8, 1, 1, 8, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}