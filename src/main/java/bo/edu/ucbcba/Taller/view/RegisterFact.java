package bo.edu.ucbcba.Taller.view;

import javax.swing.*;
import bo.edu.ucbcba.Taller.controller.FacturaController;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Factura;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.List;


/**
 * Created by Rebeca on 18/05/2016.
 */
public class RegisterFact extends JDialog {
    private JPanel rootPanel;
    private JTextField ciField;
    private JTextField placaField;
    private JTextField marcaField;
    private JTextField costoField;
    private JTextArea descripArea;
    private JTable tablemante;
    private JButton addButton;
    private JButton searchButton;
    private JTextField searchtextField;
    private JButton showButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JButton actualizarButton;

    private FacturaController maintenanceController;

    public RegisterFact(JFrame parent) {
        super(parent, "Registrar Mantenimiento", true);

        setContentPane(rootPanel);
        setSize(500, 400);
        maintenanceController = new FacturaController();
        pack();
        setResizable(false);

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTableMan();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMan();
                populateTableMan();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMan();
                populateTableMan();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateSearchCITableMan();
            }
        });


        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateActualizar();
            }
        });

        tablemante.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Integer ci = (Integer) tablemante.getValueAt(tablemante.getSelectedRow(), 1);
                String nombre = (String) tablemante.getValueAt(tablemante.getSelectedRow(), 2);
                String date = (String) tablemante.getValueAt(tablemante.getSelectedRow(), 3);
                Integer costo = (Integer) tablemante.getValueAt(tablemante.getSelectedRow(), 4);
                String des = (String) tablemante.getValueAt(tablemante.getSelectedRow(), 5);

                ciField.setText(Integer.toString(ci));
                placaField.setText(nombre);
                marcaField.setText(date);
                costoField.setText(Integer.toString(costo));
                descripArea.setText(des);


            }


        });


    }

    private void populateTableMan() {
        List<Factura> man = maintenanceController.show();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NUM");
        model.addColumn("CI");
        model.addColumn("PLACA");
        model.addColumn("MARCA");
        model.addColumn("COSTO");
        model.addColumn("DESCRIPCION");
        tablemante.setModel(model);

        for (Factura m : man) {
            Object[] row = new Object[6];
            row[0] = m.getId();
            row[1] = m.getCi();
            row[2] = m.getNombre();
            row[3] = "2016,5,18";//m.getDate();
            row[4] = m.getCosto();
            row[5] = m.getDescrip();
            model.addRow(row);
        }
    }

    private void clearMant() {
        ciField.setText("");
        placaField.setText("");
        marcaField.setText("");
        costoField.setText("");
        descripArea.setText("");
        Calendar ahoraCal = Calendar.getInstance();
        System.out.println(ahoraCal.getClass());
        ahoraCal.set(2016, 5, 18);
        System.out.println(ahoraCal.getTime());
        //ahoraCal.set(2004,1,7,7,0,0);

        //fecha.(a);
    }

    public void addMan() {
        try {
            maintenanceController.create(ciField.getText(), placaField.getText(), marcaField.getText(), costoField.getText(), descripArea.getText());
            populateTableMan();
            clearMant();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR DE FORMATO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cancel() {
        setVisible(false);
        dispose();
    }

    public void deleteMan() {
        DefaultTableModel tm = (DefaultTableModel) tablemante.getModel();
        int id = (Integer) tm.getValueAt(tablemante.getSelectedRow(), 0);
        maintenanceController.delete(id);
        populateTableMan();

    }

    private void populateActualizar() {

        Integer cod = (Integer) tablemante.getValueAt(tablemante.getSelectedRow(), 0);
        maintenanceController.delete(cod);
        Boolean entro = true;

        try {

            maintenanceController.create(ciField.getText(),
                    placaField.getText(),       // REGISTRA EL GENERO
                    marcaField.getText(),
                    costoField.getText(),
                    descripArea.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error de formato", JOptionPane.ERROR_MESSAGE);
            entro = false;


        }
        if (entro) {
            JOptionPane.showMessageDialog(this, "Elemento actualizado correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);

            clearMant();
        }

        populateTableMan();
    }

    private void populateSearchCITableMan() {
        List<Factura> maintenances = maintenanceController.show();
        try {
            maintenances = maintenanceController.searhCI(searchtextField.getText());
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR DE FORMATO", JOptionPane.ERROR_MESSAGE);
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CI");
        model.addColumn("PLACA");
        model.addColumn("MARCA");
        model.addColumn("COSTO");
        model.addColumn("DESCRIPCION");
        tablemante.setModel(model);
        for (Factura m : maintenances) {
            Object[] row = new Object[5];
            row[0] = m.getCi();
            row[1] = m.getNombre();
            row[2] = m.getDate();
            row[3] = m.getCosto();
            row[4] = m.getDescrip();
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(12, 3, new Insets(30, 40, 30, 40), -1, -1));
        panel1.add(rootPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), Font.BOLD, 16));
        label1.setText("DATE");
        rootPanel.add(label1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font(label2.getFont().getName(), Font.BOLD, 16));
        label2.setText("NOMBRE");
        rootPanel.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font(label3.getFont().getName(), Font.BOLD, 16));
        label3.setText("COSTO  :");
        rootPanel.add(label3, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setFont(new Font(label4.getFont().getName(), Font.BOLD, 16));
        label4.setText("DESCRIPCION  :");
        rootPanel.add(label4, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ciField = new JTextField();
        rootPanel.add(ciField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        placaField = new JTextField();
        rootPanel.add(placaField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        marcaField = new JTextField();
        rootPanel.add(marcaField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        costoField = new JTextField();
        rootPanel.add(costoField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        descripArea = new JTextArea();
        rootPanel.add(descripArea, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(30, 30), null, 0, false));
        tablemante = new JTable();
        rootPanel.add(tablemante, new GridConstraints(8, 0, 4, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(200, 150), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setFont(new Font(label5.getFont().getName(), Font.BOLD, 18));
        label5.setText("FACTURAS REGISTRADAS");
        rootPanel.add(label5, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setText("AGREGAR");
        rootPanel.add(addButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchButton = new JButton();
        searchButton.setText("BUSCAR");
        rootPanel.add(searchButton, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchtextField = new JTextField();
        rootPanel.add(searchtextField, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setFont(new Font(label6.getFont().getName(), Font.BOLD, 16));
        label6.setText("NIT");
        rootPanel.add(label6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showButton = new JButton();
        showButton.setText("MOSTRAR");
        rootPanel.add(showButton, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("CANCELAR");
        rootPanel.add(cancelButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("ELIMINAR");
        rootPanel.add(deleteButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setFont(new Font("Batang", label7.getFont().getStyle(), 16));
        label7.setText("NIT Taller 123");
        rootPanel.add(label7, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setFont(new Font("Batang", Font.BOLD, 18));
        label8.setText("FACTURA");
        rootPanel.add(label8, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        actualizarButton = new JButton();
        actualizarButton.setText("Actualizar");
        rootPanel.add(actualizarButton, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }
}
