package bo.edu.ucbcba.Taller.model;

import bo.edu.ucbcba.Taller.exceptions.ValidationException;
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

    //nombre
    @Test
    public void testSetnombre() {
        factura.setNombre("Good Title");
        assertEquals("Good Title", factura.getNombre());
    }

    @Test
    public void testSetNonombre() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        factura.setNombre("");
    }

    @Test
    public void testSetNullnombre() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        factura.setNombre(null);
    }

    @Test
    public void testSetLongnombre() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        factura.setNombre(new String(new char[256]).replace('\0', 'a'));
    }

    //descrip
    @Test
    public void testSetdescrip() {
    factura.setDescrip("Good Title");
    assertEquals("Good Title", factura.getDescrip());
}

    @Test
    public void testSetNodescrip() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        factura.setDescrip("");
    }

    @Test
    public void testSetNulldescrip() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        factura.setDescrip(null);
    }

    @Test
    public void testSetLongdescrip() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        factura.setDescrip(new String(new char[256]).replace('\0', 'a'));
    }
}
