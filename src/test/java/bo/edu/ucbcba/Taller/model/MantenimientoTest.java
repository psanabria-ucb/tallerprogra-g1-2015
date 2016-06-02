package bo.edu.ucbcba.Taller.model;

import bo.edu.ucbcba.Taller.controller.MaintenanceController;
import bo.edu.ucbcba.Taller.model.Customer;
import bo.edu.ucbcba.Taller.model.Maintenance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rebeca on 01/06/2016.
 */
public class MantenimientoTest {
    private Maintenance customer;
    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Before
    public void setUp() {
        customer = new Maintenance();
    }

    @Test
    public void testSetPlaca() {
        customer.setPlaca("2550csi");

        assertEquals("2550csi", customer.getPlaca());
    }

    @Test
    public void testSetMarca() {
        customer.setMarca("toyota");
        assertEquals("toyota", customer.getMarca());
    }

    @Test
    public void testSetLCosto() {
        customer.setCosto(1);
        assertEquals(1, customer.getCosto());
    }

    @Test
    public void testSetDes(){
        customer.setDescrip("descripcion del mantenimeinto");
        assertEquals("descripcion del mantenimeinto", customer.getDescrip());
    }

    @Test
    public void testSetCI(){
        customer.setCi(1);
        assertEquals(1, customer.getCi());
    }
}
