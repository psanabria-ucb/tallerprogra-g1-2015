package bo.edu.ucbcba.Taller.view;

import bo.edu.ucbcba.Taller.controller.MaintenanceController;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Maintenance;
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


/**
 * Created by Osmar on 17/05/2016.
 */
public class RegisterMaintenance extends JDialog {
    private JTextField ciField;
    private JTextField placaField;
    private JTextField costoField;
    private JTextArea descripArea;
    private JButton addButton;
    private JButton deleteButton;
    private JTable tablemante;
    private JPanel rootPanel;
    private JButton editaButton;
    private JButton searchButton;
    private JButton showButton;
    private JTextField searchtextField;
    private JButton cancelButton;
    private JComboBox comboBoxMarcaq;
    private MaintenanceController maintenanceController;

    public RegisterMaintenance(JFrame parent) {
        super(parent, "Registrar Mantenimiento", true);

        setContentPane(rootPanel);
        setSize(500, 400);
        maintenanceController = new MaintenanceController();
        pack();
        populateTableMan();
        setResizable(false);
        editaButton.setEnabled(false);
        deleteButton.setEnabled(false);


        editaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //UpdateTabla();
                int resp=JOptionPane.showConfirmDialog(null,"DESEA EDITAR");
                if (JOptionPane.OK_OPTION == resp){
                    //System.out.println("Selecciona opción Afirmativa");
                    //clearMant();
                    //deleteMan();
                    //populateTableMan();
                    UpdateTabla();
                    clearMant();
                }
                else{
                    //System.out.println("No selecciona una opción afirmativa");
                    deleteButton.setEnabled(false);
                    addButton.setEnabled(true);
                    editaButton.setEnabled(false);
                    clearMant();
                }
                deleteButton.setEnabled(false);
                addButton.setEnabled(true);
                editaButton.setEnabled(false);
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
                //deleteMan();
                //populateTableMan();
                int resp=JOptionPane.showConfirmDialog(null,"DESEA ELIMINAR");
                if (JOptionPane.OK_OPTION == resp){
                    //System.out.println("Selecciona opción Afirmativa");
                    clearMant();
                    deleteMan();
                    populateTableMan();
                }
                else{
                    //System.out.println("No selecciona una opción afirmativa");
                    deleteButton.setEnabled(false);
                    addButton.setEnabled(true);
                    editaButton.setEnabled(false);
                    clearMant();
                }
                deleteButton.setEnabled(false);
                addButton.setEnabled(true);
                editaButton.setEnabled(false);
            }

        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateSearchCITableMan();
            }
        });

        tablemante.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Integer ci = (Integer) tablemante.getValueAt(tablemante.getSelectedRow(), 1);
                String placa = (String) tablemante.getValueAt(tablemante.getSelectedRow(), 2);
                String marca = (String) tablemante.getValueAt(tablemante.getSelectedRow(), 3);
                int costo = (Integer) tablemante.getValueAt(tablemante.getSelectedRow(), 4);
                String descrip = (String) tablemante.getValueAt(tablemante.getSelectedRow(), 5);

                ciField.setText(Integer.toString(ci));
                placaField.setText(placa);
                //marcaField.setText(marca);
                comboBoxMarcaq.setSelectedItem(marca);
                costoField.setText(Integer.toString(costo));
                descripArea.setText(descrip);
                editaButton.setEnabled(true);
                addButton.setEnabled(false);
                deleteButton.setEnabled(true);
            }
        });
    }

    private void populateTableMan() {
        List<Maintenance> man = maintenanceController.show();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NUM");
        model.addColumn("CI");
        model.addColumn("PLACA");
        model.addColumn("MARCA");
        model.addColumn("COSTO");
        model.addColumn("DESCRIPCION");
        tablemante.setModel(model);

        for (Maintenance m : man) {
            Object[] row = new Object[6];
            row[0] = m.getId();
            row[1] = m.getCi();
            row[2] = m.getPlaca();
            row[3] = m.getMarca();
            row[4] = m.getCosto();
            row[5] = m.getDescrip();
            model.addRow(row);
        }
    }

    private void clearMant() {
        ciField.setText("");
        placaField.setText("");
        //marcaField.setText("");
        comboBoxMarcaq.setSelectedItem("");
        costoField.setText("");
        descripArea.setText("");
    }

    public void addMan() {
        try {
            maintenanceController.create(ciField.getText(), placaField.getText(), (String) comboBoxMarcaq.getSelectedItem(), costoField.getText(), descripArea.getText());
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

    public void editMan() {
        DefaultTableModel tm = (DefaultTableModel) tablemante.getModel();
        //String id =(String) tm.getValueAt(tablemante.getSelectedRow(), 0);
        //ciField.setText(id);
        String placa = (String) tm.getValueAt(tablemante.getSelectedRow(), 1);
        placaField.setText(placa);
        String marca = (String) tm.getValueAt(tablemante.getSelectedRow(), 2);
        comboBoxMarcaq.setSelectedItem(marca);
        //marcaField.setText(marca);
        String costo = (String) tm.getValueAt(tablemante.getSelectedRow(), 3);
        costoField.setText(costo);
        String descripcion = (String) tm.getValueAt(tablemante.getSelectedRow(), 4);
        descripArea.setText(descripcion);
        //maintenanceController.delete(id);
        populateTableMan();
    }

    private void populateSearchCITableMan() {
        List<Maintenance> maintenances = maintenanceController.show();
        try {
            maintenances = maintenanceController.searhCI(searchtextField.getText());
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR DE FORMATO", JOptionPane.ERROR_MESSAGE);
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NUM");
        model.addColumn("CI");
        model.addColumn("PLACA");
        model.addColumn("MARCA");
        model.addColumn("COSTO");
        model.addColumn("DESCRIPCION");
        tablemante.setModel(model);

        for (Maintenance m : maintenances) {
            Object[] row = new Object[6];
            row[0] = m.getId();
            row[1] = m.getCi();
            row[2] = m.getPlaca();
            row[3] = m.getMarca();
            row[4] = m.getCosto();
            row[5] = m.getDescrip();
            model.addRow(row);
        }

        /*
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CI");
        model.addColumn("PLACA");
        model.addColumn("MARCA");
        model.addColumn("COSTO");
        model.addColumn("DESCRIPCION");
        tablemante.setModel(model);
        for (Maintenance m : maintenances) {
            Object[] row = new Object[5];
            row[0] = m.getCi();
            row[1] = m.getPlaca();
            row[2] = m.getMarca();
            row[3] = m.getCosto();
            row[4] = m.getDescrip();
            model.addRow(row);
        }
        */
    }

    private void UpdateTabla() {
        DefaultTableModel tm = (DefaultTableModel) tablemante.getModel();
        int cod = (Integer) tm.getValueAt(tablemante.getSelectedRow(), 0);
        maintenanceController.delete(cod);
        Boolean entro = true;
        try {
            maintenanceController.create(ciField.getText(), placaField.getText(), /*marcaField.getText()*/(String) comboBoxMarcaq.getSelectedItem(), costoField.getText(), descripArea.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERROR AL ELIMINAR");
            entro = false;
        }
        if (entro) {
            JOptionPane.showMessageDialog(this, "LA ACTUALIZACION FUE EXITOSA");
        }
        populateTableMan();
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
        rootPanel.setLayout(new GridLayoutManager(18, 7, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), Font.BOLD, 16));
        label1.setText("MARCA  :");
        rootPanel.add(label1, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font(label2.getFont().getName(), Font.BOLD, 16));
        label2.setText("PLACA  :");
        rootPanel.add(label2, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font(label3.getFont().getName(), Font.BOLD, 16));
        label3.setText("COSTO  :");
        rootPanel.add(label3, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setFont(new Font(label4.getFont().getName(), Font.BOLD, 16));
        label4.setText("DESCRIPCION  :");
        rootPanel.add(label4, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ciField = new JTextField();
        rootPanel.add(ciField, new GridConstraints(2, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        placaField = new JTextField();
        rootPanel.add(placaField, new GridConstraints(3, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
       // marcaField = new JTextField();
        //rootPanel.add(marcaField, new GridConstraints(4, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        costoField = new JTextField();
        rootPanel.add(costoField, new GridConstraints(5, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        descripArea = new JTextArea();
        rootPanel.add(descripArea, new GridConstraints(6, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        tablemante = new JTable();
        rootPanel.add(tablemante, new GridConstraints(13, 2, 4, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(200, 150), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setFont(new Font(label5.getFont().getName(), Font.BOLD, 18));
        label5.setText("INGRESE LOS DATOS");
        rootPanel.add(label5, new GridConstraints(1, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setFont(new Font(label6.getFont().getName(), Font.BOLD, 16));
        label6.setText("CI  :");
        rootPanel.add(label6, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        rootPanel.add(separator1, new GridConstraints(8, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JToolBar.Separator toolBar$Separator1 = new JToolBar.Separator();
        rootPanel.add(toolBar$Separator1, new GridConstraints(1, 1, 8, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator2 = new JSeparator();
        rootPanel.add(separator2, new GridConstraints(0, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JToolBar.Separator toolBar$Separator2 = new JToolBar.Separator();
        rootPanel.add(toolBar$Separator2, new GridConstraints(1, 5, 8, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setText("AGREGAR");
        rootPanel.add(addButton, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("CANCELAR");
        rootPanel.add(cancelButton, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchButton = new JButton();
        searchButton.setText("BUSCAR");
        rootPanel.add(searchButton, new GridConstraints(9, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchtextField = new JTextField();
        rootPanel.add(searchtextField, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JSeparator separator3 = new JSeparator();
        rootPanel.add(separator3, new GridConstraints(10, 1, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JToolBar.Separator toolBar$Separator3 = new JToolBar.Separator();
        rootPanel.add(toolBar$Separator3, new GridConstraints(0, 6, 17, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JToolBar.Separator toolBar$Separator4 = new JToolBar.Separator();
        rootPanel.add(toolBar$Separator4, new GridConstraints(0, 0, 17, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator4 = new JSeparator();
        rootPanel.add(separator4, new GridConstraints(17, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setFont(new Font(label7.getFont().getName(), Font.BOLD, 18));
        label7.setText("MATENIMIENTOS REGISTRADOS");
        rootPanel.add(label7, new GridConstraints(12, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setFont(new Font(label8.getFont().getName(), Font.BOLD, 16));
        label8.setText("INGRESE EL CI DEL CLIENTE");
        rootPanel.add(label8, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editaButton = new JButton();
        editaButton.setText("ACTUALIZAR ");
        rootPanel.add(editaButton, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("ELIMINAR");
        rootPanel.add(deleteButton, new GridConstraints(13, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
