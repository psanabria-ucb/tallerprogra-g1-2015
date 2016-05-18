package bo.edu.ucbcba.Taller.view;

import bo.edu.ucbcba.Taller.controller.MaintenanceController;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Maintenance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * Created by Osmar on 17/05/2016.
 */
public class RegisterMaintenance extends JFrame {
    private JTextField ciField;
    private JTextField placaField;
    private JTextField marcaField;
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
    private MaintenanceController maintenanceController;

    public RegisterMaintenance(){
        super("MANTENIMIENTO");

        setContentPane(rootPanel);
        setSize(500,400);
        maintenanceController = new MaintenanceController();
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

            }
        });
    }

    private void populateTableMan()
    {
        List<Maintenance> man = maintenanceController.show();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CI");
        model.addColumn("PLACA");
        model.addColumn("MARCA");
        model.addColumn("COSTO");
        model.addColumn("DESCRIPCION");
        tablemante.setModel(model);

        for(Maintenance m : man)
        {
            Object[] row = new Object[5];
            row[0] = m.getCi();
            row[1] = m.getPlaca();
            row[2] = m.getMarca();
            row[3] = m.getCosto();
            row[4] = m.getDescrip();
            model.addRow(row);
        }
    }

    private  void clearMant(){
        ciField.setText("");
        placaField.setText("");
        marcaField.setText("");
        costoField.setText("");
        descripArea.setText("");
    }

    public void addMan(){
        try{
            maintenanceController.create(ciField.getText(),placaField.getText(),marcaField.getText(),costoField.getText(),descripArea.getText());
            populateTableMan();
            clearMant();
        }
        catch (ValidationException ex){
            JOptionPane.showMessageDialog(this,ex.getMessage(),"ERROR DE FORMATO",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cancel(){
        setVisible(false);
        dispose();
    }

    public  void deleteMan(){
        DefaultTableModel tm = (DefaultTableModel) tablemante.getModel();
        int ci = (Integer) tm.getValueAt(tablemante.getSelectedRow(),0);
        maintenanceController.delete(ci);
        populateTableMan();
    }

    private void populateSearchTableMan(){
        List<Maintenance> maintenances = maintenanceController.show();
        try{
            maintenances = maintenanceController.searhCI(searchtextField.getText());
        }
        catch (ValidationException ex)
        {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"ERROR DE FORMATO",JOptionPane.ERROR_MESSAGE);
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CI");
        model.addColumn("PLACA");
        model.addColumn("MARCA");
        model.addColumn("COSTO");
        model.addColumn("DESCRIPCION");
        tablemante.setModel(model);
        for(Maintenance m : maintenances){
            Object[] row = new Object[5];
            row[0] = m.getCi();
            row[1] = m.getPlaca();
            row[2] = m.getMarca();
            row[3] = m.getCosto();
            row[4] = m.getDescrip();
            model.addRow(row);
        }
    }

}
