package bo.edu.ucbcba.Taller.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Usuario on 18/05/2016.
 */
public class MainForm extends JFrame {
    private JButton stockButton;
    private JButton facturacionButton;
    private JPanel rootPanel;

    private JButton ventasButton;
    private JButton salirButton;
    private JButton mantenimientoButton;
    private JButton historialDeVentasButton;
    private JButton registrarClienteButton;
    private JTextField Img;

    public MainForm() {
        super("TALLER DE MANTENIMIENTO");
        setContentPane(rootPanel);
        setSize(600, 400);


        facturacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register();
            }
        });

        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchRegister();
            }
        });

        mantenimientoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });


        ventasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launcHistory();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        historialDeVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventasregister();
            }
        });


        registrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clienteregisterr();
            }
        });

    }

    private void launcHistory() {
        RegistrarVentaF form = new RegistrarVentaF(this);
        form.setVisible(true);
    }

    private void exit() {
        setVisible(false);
        dispose();
    }

    private void ventasregister() {
        RegisterSaleForm form = new RegisterSaleForm(this);
        form.setVisible(true);
    }

    private void launchRegister() {
        RegistrarRepuestoF form = new RegistrarRepuestoF(this);
        form.setVisible(true);
    }

    private void populateTable() {
        RegisterMaintenance f = new RegisterMaintenance(this);
        f.setVisible(true);
    }

    private void Register() {
        RegisterFact f = new RegisterFact(this);
        f.setVisible(true);
    }

    private void clienteregisterr() {
        RegisterCustomer r = new RegisterCustomer(this);
        r.setVisible(true);
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
        rootPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        mantenimientoButton = new JButton();
        mantenimientoButton.setText("Mantenimiento");
        rootPanel.add(mantenimientoButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        facturacionButton = new JButton();
        facturacionButton.setText("Facturacion");
        rootPanel.add(facturacionButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        registrarClienteButton = new JButton();
        registrarClienteButton.setText("Cliente");
        rootPanel.add(registrarClienteButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        salirButton = new JButton();
        salirButton.setText("Salir");
        rootPanel.add(salirButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        historialDeVentasButton = new JButton();
        historialDeVentasButton.setText("Button");
        rootPanel.add(historialDeVentasButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        stockButton = new JButton();
        stockButton.setText("Repuestos");
        rootPanel.add(stockButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ventasButton = new JButton();
        ventasButton.setText("Ventas");
        rootPanel.add(ventasButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Img = new JTextField();
        rootPanel.add(Img, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}