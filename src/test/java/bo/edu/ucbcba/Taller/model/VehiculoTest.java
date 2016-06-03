package bo.edu.ucbcba.Taller.model;

import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class VehiculoTest {
    private Vehiculo vehiculo;
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Before
    public void setUp() {
        vehiculo = new Vehiculo();
    }



    @Test
    public void testSetModelo() {
        vehiculo.setModelo(2000);
        assertEquals(2000, vehiculo.getModelo());
    }


    //marca


    @Test
    public void testSetMarca() {
        vehiculo.setMarca("Good Title");
        assertEquals("Good Title", vehiculo.getMarca());
    }

    @Test
    public void testSetNoMarca() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        vehiculo.setMarca("");
    }

    @Test
    public void testSetNullMarca() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        vehiculo.setMarca(null);
    }

    @Test
    public void testSetLongMarca() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        vehiculo.setMarca(new String(new char[256]).replace('\0', 'a'));
    }
//origen

    @Test
    public void testSetOrigen() {
        vehiculo.setOrigen("Good Title");
        assertEquals("Good Title", vehiculo.getOrigen());
    }

    @Test
    public void testSetNoOrigen() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        vehiculo.setOrigen("");
    }

    @Test
    public void testSetNullOrigen() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        vehiculo.setOrigen(null);
    }

    @Test
    public void testSetLongOrigen() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        vehiculo.setOrigen(new String(new char[256]).replace('\0', 'a'));
    }

    //placa

    @Test
    public void testSetPlaca() {
        vehiculo.setPlaca("Good Title");
        assertEquals("Good Title", vehiculo.getPlaca());
    }

    @Test
    public void testSetNoPlaca() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        vehiculo.setPlaca("");
    }

    @Test
    public void testSetNullPlaca() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        vehiculo.setPlaca(null);
    }

    @Test
    public void testSetLongPlaca() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        vehiculo.setPlaca(new String(new char[256]).replace('\0', 'a'));
    }
    //color

    @Test
    public void testSetColor() {
        vehiculo.setColor("Good Title");
        assertEquals("Good Title", vehiculo.getColor());
    }

    @Test
    public void testSetNoColor() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        vehiculo.setColor("");
    }

    @Test
    public void testSetNullColor() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        vehiculo.setColor(null);
    }

    @Test
    public void testSetLongColor() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        vehiculo.setColor(new String(new char[256]).replace('\0', 'a'));
    }
}
