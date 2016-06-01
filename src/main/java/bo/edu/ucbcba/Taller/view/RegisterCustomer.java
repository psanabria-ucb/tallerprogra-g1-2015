package bo.edu.ucbcba.Taller.view;

import bo.edu.ucbcba.Taller.controller.CustomerController;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Customer;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by Rebeca on 01/06/2016.
 */
public class RegisterCustomer extends JDialog {
    private JTextField ciField;
    private JTextField nombreField;
    private JTextField apellidoPField;
    private JTextField apellidoMField;
    private JTextField fonoField;
    private JTextArea descripArea;
    private JTable tablemante;
    private JButton addButton;
    private JPanel rootPanel;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField searchCustomerField;
    private JRadioButton searchCIRadioButton;
    private JRadioButton searchFirstNameRadioButton;
    private JRadioButton searchFLastNameRadioButton;
    private JRadioButton searchMLastNameRadioButton;
    private JButton showButton;
    private JButton cancelButton;
    private JButton actualizarButton;

    private CustomerController maintenanceController;

    public RegisterCustomer(JFrame parent) {
        super(parent, "Registro de clientes", true);

        setContentPane(rootPanel);

        setSize(500, 400);
        maintenanceController = new CustomerController();
        pack();
        populateTableMan();
        setResizable(false);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMan();
                populateTableMan();
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
                populateSearchingTableCustomers();
            }
        });


        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTableMan();
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
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
                String apellF = (String) tablemante.getValueAt(tablemante.getSelectedRow(), 3);
                String apellM = (String) tablemante.getValueAt(tablemante.getSelectedRow(), 4);
                Integer fono = (Integer) tablemante.getValueAt(tablemante.getSelectedRow(), 5);
                String des = (String) tablemante.getValueAt(tablemante.getSelectedRow(), 6);

                ciField.setText(Integer.toString(ci));
                nombreField.setText(nombre);
                apellidoPField.setText(apellF);
                apellidoMField.setText(apellM);
                fonoField.setText(Integer.toString(fono));
                descripArea.setText(des);


            }


        });


    }


    private void populateTableMan() {
        List<Customer> man = maintenanceController.show();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NUM");
        model.addColumn("CI");
        model.addColumn("Nombre");
        model.addColumn("Apellido Paterno");
        model.addColumn("Apellido Materno");
        model.addColumn("Telefono");
        model.addColumn("Direccion");
        tablemante.setModel(model);

        for (Customer m : man) {
            Object[] row = new Object[7];
            row[0] = m.getId();
            row[1] = m.getCi();
            row[2] = m.getFirtsName();
            row[3] = m.getLastNameF();
            row[4] = m.getLastNameM();
            row[5] = m.getNumberPhone();
            row[6] = m.getAddress();
            model.addRow(row);
        }
    }

    private void clearMant() {
        ciField.setText("");
        nombreField.setText("");
        apellidoPField.setText("");
        apellidoMField.setText("");
        fonoField.setText("");
        descripArea.setText("");
        // Calendar ahoraCal = Calendar.getInstance();
        // System.out.println(ahoraCal.getClass());
        // ahoraCal.set(2016, 5, 18);
        // System.out.println(ahoraCal.getTime());
        //ahoraCal.set(2004,1,7,7,0,0);

        //fecha.(a);
    }


    public void addMan() {
        try {
            maintenanceController.create(ciField.getText(), nombreField.getText(), apellidoPField.getText(), apellidoMField.getText(), fonoField.getText(), descripArea.getText());
            populateTableMan();
            clearMant();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR DE FORMATO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteMan() {


        DefaultTableModel tm = (DefaultTableModel) tablemante.getModel();
        //   int id = (Integer) tm.getValueAt(tablemante.getSelectedRow(), 0);

        if (tablemante.getSelectedRow() > 0)

        {
            int id = (Integer) tm.getValueAt(tablemante.getSelectedRow(), 0);
            maintenanceController.delete(id);
            clearMant();
            populateTableMan();


        } else {
            try {
                maintenanceController.delete(0);

            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de seleccion", JOptionPane.ERROR_MESSAGE);
            }
        }
        // maintenanceController.delete(id);
        //   clearMant();
        //   populateTableMan();


    }


    private void populateSearchCITableMan() {
        List<Customer> maintenances = maintenanceController.show();
        try {
            maintenances = maintenanceController.searhCI(searchCustomerField.getText());
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR DE FORMATO", JOptionPane.ERROR_MESSAGE);
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NUM");
        model.addColumn("CI");
        model.addColumn("Nombre");
        model.addColumn("Apellido Paterno");
        model.addColumn("Apellido Materno");
        model.addColumn("Telefono");
        model.addColumn("Direccion");
        tablemante.setModel(model);
        for (Customer m : maintenances) {
            Object[] row = new Object[7];
            row[0] = m.getId();
            row[1] = m.getCi();
            row[2] = m.getFirtsName();
            row[3] = m.getLastNameF();
            row[4] = m.getLastNameM();
            row[5] = m.getNumberPhone();
            row[6] = m.getAddress();
            model.addRow(row);
        }
    }


    private void populateSearchingTableCustomers() {
        List<Customer> customers = maintenanceController.show();
        if (searchFirstNameRadioButton.isSelected()) {
            customers = maintenanceController.searchCustomerByFirstName(searchCustomerField.getText());
        }
        if (searchFLastNameRadioButton.isSelected()) {
            customers = maintenanceController.searchCustomerByFathersLastName(searchCustomerField.getText());
        }
        if (searchMLastNameRadioButton.isSelected()) {
            customers = maintenanceController.searchCustomerByMothersLastName(searchCustomerField.getText());
        }
        if (searchCIRadioButton.isSelected()) {
            try {
                customers = maintenanceController.searhCI(searchCustomerField.getText());
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }

        }
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CI");
        model.addColumn("NOMBRE");
        model.addColumn("APELLIDO PATERNO");
        model.addColumn("APELLIDO MATERNO");
        model.addColumn("TELEFONO");
        model.addColumn("DIRECCION");
        tablemante.setModel(model);

        for (Customer c : customers) {
            Object[] row = new Object[7];

            row[0] = c.getId();
            row[1] = c.getCi();
            row[2] = c.getFirtsName();
            row[3] = c.getLastNameF();
            row[4] = c.getLastNameM();
            row[5] = c.getNumberPhone();
            row[6] = c.getAddress();
            model.addRow(row);
        }
    }

    public void cancel() {
        setVisible(false);
        dispose();
    }

    private void populateActualizar() {
        DefaultTableModel tm = (DefaultTableModel) tablemante.getModel();
        if (tablemante.getSelectedRow() > 0) {
            int cod = (Integer) tm.getValueAt(tablemante.getSelectedRow(), 0);
            // Integer cod = (Integer) tablemante.getValueAt(tablemante.getSelectedRow(), 0);
            maintenanceController.delete(cod);
            Boolean entro = true;

            try {

                maintenanceController.create(ciField.getText(),
                        nombreField.getText(),       // REGISTRA EL GENERO
                        apellidoPField.getText(),
                        apellidoMField.getText(),
                        fonoField.getText(),
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
        } else {
            try {
                maintenanceController.delete(0);

            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de seleccion paara editar", JOptionPane.ERROR_MESSAGE);
            }

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
        rootPanel.setLayout(new GridLayoutManager(15, 6, new Insets(30, 40, 30, 40), -1, -1));
        panel1.add(rootPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Cliente");
        rootPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootPanel.add(spacer2, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("CI");
        rootPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ciField = new JTextField();
        rootPanel.add(ciField, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Nombre");
        rootPanel.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Apellido Paterno");
        rootPanel.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Apellido Materno");
        rootPanel.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Teléfono");
        rootPanel.add(label6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nombreField = new JTextField();
        rootPanel.add(nombreField, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        apellidoPField = new JTextField();
        rootPanel.add(apellidoPField, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        apellidoMField = new JTextField();
        rootPanel.add(apellidoMField, new GridConstraints(4, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        fonoField = new JTextField();
        rootPanel.add(fonoField, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Dirección");
        rootPanel.add(label7, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        descripArea = new JTextArea();
        descripArea.setColumns(10);
        descripArea.setLineWrap(false);
        descripArea.setRows(5);
        rootPanel.add(descripArea, new GridConstraints(6, 1, 1, 3, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        tablemante = new JTable();
        rootPanel.add(tablemante, new GridConstraints(9, 1, 4, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("Eliminar");
        rootPanel.add(deleteButton, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Buscar");
        rootPanel.add(searchButton, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchCustomerField = new JTextField();
        rootPanel.add(searchCustomerField, new GridConstraints(7, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchCIRadioButton = new JRadioButton();
        searchCIRadioButton.setSelected(true);
        searchCIRadioButton.setText("Ci");
        rootPanel.add(searchCIRadioButton, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchFirstNameRadioButton = new JRadioButton();
        searchFirstNameRadioButton.setText("Nombre");
        rootPanel.add(searchFirstNameRadioButton, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchFLastNameRadioButton = new JRadioButton();
        searchFLastNameRadioButton.setText("Apellido Materno");
        rootPanel.add(searchFLastNameRadioButton, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchMLastNameRadioButton = new JRadioButton();
        searchMLastNameRadioButton.setText("Apellido Paterno");
        rootPanel.add(searchMLastNameRadioButton, new GridConstraints(8, 4, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showButton = new JButton();
        showButton.setText("Mostrar");
        rootPanel.add(showButton, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Buscar por ...");
        rootPanel.add(label8, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        actualizarButton = new JButton();
        actualizarButton.setText("Actualizar");
        rootPanel.add(actualizarButton, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setText("Agregar");
        rootPanel.add(addButton, new GridConstraints(14, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancelar");
        rootPanel.add(cancelButton, new GridConstraints(14, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(searchCIRadioButton);
        buttonGroup.add(searchFirstNameRadioButton);
        buttonGroup.add(searchFLastNameRadioButton);
        buttonGroup.add(searchMLastNameRadioButton);
    }
}
