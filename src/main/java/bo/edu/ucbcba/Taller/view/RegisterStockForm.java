package bo.edu.ucbcba.Taller.view;

import bo.edu.ucbcba.Taller.controller.StockController;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
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
import java.util.List;

public class RegisterStockForm extends JDialog {
    private JPanel rootPanel;
    private JTextField cost;
    private JTextField name;
    private JTextField code;
    private JButton cancelButton;
    private JButton searchButton;
    private JTextField quantity;
    private StockController controller;


    private JTextField searchText;
    private JButton saveButton;
    private JTable stockTable;
    private JButton deletebutton;
    private JRadioButton nombreRadioButton;
    private JRadioButton codeRadioButton;
    private JButton editButton;
    private JButton mostrarDatosButton;

    public RegisterStockForm(JFrame parent) {
        super(parent, "Repuestos", true);
        setContentPane(rootPanel);
        pack();
        setResizable(false);
        setSize(600, 400);
        //  populateTable();
        mostrarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrar();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {populateTable();}
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        controller = new StockController();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {saveUser();}
        });
        deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletestock();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        stockTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String nombre = (String) stockTable.getValueAt(stockTable.getSelectedRow(), 1);
                String codigo = (String) stockTable.getValueAt(stockTable.getSelectedRow(), 2);
                Integer cantidad = (Integer) stockTable.getValueAt(stockTable.getSelectedRow(), 3);
                Float costo = (Float) stockTable.getValueAt(stockTable.getSelectedRow(), 4);

                name.setText(nombre);
                cost.setText(Float.toString(costo));
                code.setText(codigo);
                quantity.setText(Integer.toString(cantidad));
            }
        });
    }

    public void mostrar() {
        populateTable();
    }

    public void actualizar() {
        DefaultTableModel tm = (DefaultTableModel) stockTable.getModel();
        if (stockTable.getSelectedRowCount() > 0) {
            int id = (Integer) tm.getValueAt(stockTable.getSelectedRow(), 0);
            // Integer cod = (Integer) stockTable.getValueAt(stockTable.getSelectedRow(), 0);
            controller.delete(id);
            Boolean entro = true;
            try {
                controller.create(name.getText(),
                        cost.getText(),       // REGISTRA EL GENERO
                        code.getText(),
                        quantity.getText());
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
                controller.getStock(0);

            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de selección", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void clearstock() {
        name.setText("");
        cost.setText("");
        code.setText("");
        quantity.setText("");
    }

    public void deletestock() {
        DefaultTableModel tm = (DefaultTableModel) stockTable.getModel();
        if (stockTable.getSelectedRowCount() > 0) {
            int id = (Integer) tm.getValueAt(stockTable.getSelectedRow(), 0);
            controller.delete(id);
            populateTable();
        } else {
            try {
                controller.delete(0);
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de selección", JOptionPane.ERROR_MESSAGE);
            }
        }
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
        populateTable();
    }

    private void cancel() {
        setVisible(false);
        dispose();
    }

    private void populateTable() {
        List<Stock> stock = controller.show();

        if (nombreRadioButton.isSelected())
            stock = controller.searchStockbyname(searchText.getText());
        if (codeRadioButton.isSelected())
            try {
                stock = controller.searchStockbycode(searchText.getText());
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Código");
        model.addColumn("Cantidad");
        model.addColumn("Costo");
        stockTable.setModel(model);

        for (Stock m : stock) {
            Object[] row = new Object[5];

            row[0] = m.getId();
            row[1] = m.getName();
            row[2] = m.getCode();
            row[3] = m.getQuantity();
            row[4] = m.getCost();
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
        rootPanel.setLayout(new GridLayoutManager(9, 9, new Insets(10, 10, 10, 10), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Nombre");
        rootPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(58, 16), null, 0, false));
        name = new JTextField();
        name.setText("");
        rootPanel.add(name, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Costo");
        rootPanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cost = new JTextField();
        cost.setText("");
        rootPanel.add(cost, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Codigo");
        rootPanel.add(label3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        code = new JTextField();
        rootPanel.add(code, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Cantidad");
        rootPanel.add(label4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        quantity = new JTextField();
        quantity.setText("");
        rootPanel.add(quantity, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        rootPanel.add(scrollPane1, new GridConstraints(1, 3, 7, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        stockTable = new JTable();
        scrollPane1.setViewportView(stockTable);
        cancelButton = new JButton();
        cancelButton.setText("Cancelar");
        rootPanel.add(cancelButton, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText(" Busca por nombre");
        rootPanel.add(label5, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(56, 58), null, 0, false));
        searchText = new JTextField();
        rootPanel.add(searchText, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Buscar");
        rootPanel.add(saveButton, new GridConstraints(0, 8, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editButton = new JButton();
        editButton.setText("Editar");
        rootPanel.add(editButton, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deletebutton = new JButton();
        deletebutton.setText("Eliminar");
        rootPanel.add(deletebutton, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Guardar");
        rootPanel.add(searchButton, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Registrar Repuesto");
        rootPanel.add(label6, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nombreRadioButton = new JRadioButton();
        nombreRadioButton.setSelected(true);
        nombreRadioButton.setText("Nombre");
        rootPanel.add(nombreRadioButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        codeRadioButton = new JRadioButton();
        codeRadioButton.setText("Código");
        rootPanel.add(codeRadioButton, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mostrarDatosButton = new JButton();
        mostrarDatosButton.setText("Mostrar Datos");
        rootPanel.add(mostrarDatosButton, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(nombreRadioButton);
        buttonGroup.add(codeRadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}










