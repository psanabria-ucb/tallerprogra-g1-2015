package bo.edu.ucbcba.Taller.view;

import bo.edu.ucbcba.Taller.controller.CustomerController;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by Rebeca on 01/06/2016.
 */
public class RegisterCustomer  extends JDialog{
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

    public RegisterCustomer (JFrame parent) {
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
            maintenanceController.create(ciField.getText(), nombreField.getText(), apellidoPField.getText(), apellidoMField.getText(),fonoField.getText(), descripArea.getText());
            populateTableMan();
            clearMant();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR DE FORMATO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteMan() {


        DefaultTableModel tm = (DefaultTableModel) tablemante.getModel();
     //   int id = (Integer) tm.getValueAt(tablemante.getSelectedRow(), 0);

        if (tablemante.getSelectedRow()>0)

        {
            int id =(Integer) tm.getValueAt(tablemante.getSelectedRow(), 0);
            maintenanceController.delete(id);
            clearMant();
            populateTableMan();


        }
        else
        {
            try{
                maintenanceController.delete(0);

            }
            catch (ValidationException ex)
            {
                JOptionPane.showMessageDialog(this,ex.getMessage(),"Error de seleccion",JOptionPane.ERROR_MESSAGE);
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
        }
        else
        {
            try{
                maintenanceController.delete(0);

            }
            catch (ValidationException ex)
            {
                JOptionPane.showMessageDialog(this,ex.getMessage(),"Error de seleccion paara editar",JOptionPane.ERROR_MESSAGE);
            }

        }
    }



}
