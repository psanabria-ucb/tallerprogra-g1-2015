package bo.edu.ucbcba.Taller.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rebeca on 02/06/2016.
 */
public class VehiculoTest {
    private Vehiculo vehiculo;
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Before
    public void setUp() {
        vehiculo = new Vehiculo();
    }

    @Test
    public void testSetPlaca() {
        vehiculo.setPlaca("2450csi");
        assertEquals("2450csi", vehiculo.getPlaca());
    }

    @Test
    public void testSetColor() {
        vehiculo.setColor("rojo");
        assertEquals("rojo", vehiculo.getColor());
    }

    @Test
    public void testSetMarca() {
        vehiculo.setMarca("toyota");
        assertEquals("toyota", vehiculo.getMarca());
    }

    @Test
    public void testSetModelo() {
        vehiculo.setModelo(2000);
        assertEquals(2000, vehiculo.getModelo());
    }

    @Test
    public void testSetOrigen() {
        vehiculo.setOrigen("Japan");

        assertEquals("Japan", vehiculo.getOrigen());
    }
}
