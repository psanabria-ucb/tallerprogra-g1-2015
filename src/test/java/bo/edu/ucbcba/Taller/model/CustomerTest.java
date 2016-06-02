package bo.edu.ucbcba.Taller.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class CustomerTest {



    private Customer customer;
    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Before
    public void setUp() {
        customer = new Customer();
    }

    @Test
    public void testSetCi() {
        customer.setCi(4);

        assertEquals(4, customer.getCi());
    }

    @Test
    public void testSetFirstName() {
        customer.setFirtsName("Vaquita");
        assertEquals("Vaquita", customer.getFirtsName());
    }

    @Test
    public void testSetLastNameF() {
        customer.setLastNameF("Chiquita");
        assertEquals("Chiquita", customer.getLastNameF());
    }
    @Test
    public void testSetLastNameM() {
        customer.setLastNameM("quita");
        assertEquals("quita", customer.getLastNameM());
    }

    @Test
    public void testSetAddress() {
        customer.setAddress("Av");
        assertEquals("Av", customer.getAddress());
    }

    @Test
    public void testSetNumberFono() {
        customer.setNumberPhone(454);

        assertEquals(454, customer.getNumberPhone());
    }


}
