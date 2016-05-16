package bo.edu.ucbcba.Taller;

import bo.edu.ucbcba.Taller.view.StockForm;

import javax.swing.*;

/**
 * Created by Usuario on 12/05/2016.
 */
public class Main {
    public static void main(String[] args) {
        StockForm form = new StockForm();
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
