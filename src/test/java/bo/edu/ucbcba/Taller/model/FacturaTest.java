package bo.edu.ucbcba.Taller.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class FacturaTest {
    private Factura factura;
    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Before
    public void setUp() {
        factura = new Factura();
    }

    @Test
    public void testSetCi() {
        factura.setCi(5);

        assertEquals(5, factura.getCi());
    }

    @Test
    public void testSetNombre() {
        factura.setNombre("Rebeca");
        assertEquals("Rebeca", factura.getNombre());
    }


    @Test
    public void testSetCosto() {
        factura.setCosto(50);

        assertEquals(50, factura.getCosto());
    }



    @Test
    public void testSetDescrip() {
        factura.setDescrip("blablabla");
        assertEquals("blablabla", factura.getDescrip());
    }




}
