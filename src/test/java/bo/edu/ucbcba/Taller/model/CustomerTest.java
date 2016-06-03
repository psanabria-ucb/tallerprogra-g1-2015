package bo.edu.ucbcba.Taller.model;

import bo.edu.ucbcba.Taller.exceptions.ValidationException;
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
    public void testSetAddresss() {
        customer.setAddress("Av");
        assertEquals("Av", customer.getAddress());
    }

    @Test
    public void testSetNumberFono() {
        customer.setNumberPhone(454);

        assertEquals(454, customer.getNumberPhone());
    }
//test address
    @Test
    public void testSetAddress() {
        customer.setAddress("Good Title");
        assertEquals("Good Title", customer.getAddress());
    }

    @Test
    public void testSetNoAddress() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        customer.setAddress("");
    }

    @Test
    public void testSetNullAddress() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        customer.setAddress(null);
    }

    @Test
    public void testSetLongAddress() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        customer.setAddress(new String(new char[256]).replace('\0', 'a'));
    }

    //lastNameM
    @Test
    public void testSetlastNameM() {
        customer.setLastNameM("Good Title");
        assertEquals("Good Title", customer.getLastNameM());
    }

    @Test
    public void testSetNolastNameM() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        customer.setLastNameM("");
    }

    @Test
    public void testSetNulllastNameM() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        customer.setLastNameM(null);
    }

    @Test
    public void testSetLonglastNameM() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        customer.setLastNameM(new String(new char[256]).replace('\0', 'a'));
    }
    //lastNameF
    @Test
    public void testSetlastNameF() {
        customer.setLastNameF("Good Title");
        assertEquals("Good Title", customer.getLastNameF());
    }

    @Test
    public void testSetNolastNameF() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        customer.setLastNameF("");
    }

    @Test
    public void testSetNulllastNameF() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        customer.setLastNameF(null);
    }

    @Test
    public void testSetLonglastNameF() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        customer.setLastNameF(new String(new char[256]).replace('\0', 'a'));
    }

    //firtsName
    @Test
    public void testSetfirtsName() {
        customer.setFirtsName("Good Title");
        assertEquals("Good Title", customer.getFirtsName());
    }

    @Test
    public void testSetNofirtsName() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        customer.setFirtsName("");
    }

    @Test
    public void testSetNullfirtsName() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        customer.setFirtsName(null);
    }

    @Test
    public void testSetLongfirtsName() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        customer.setFirtsName(new String(new char[256]).replace('\0', 'a'));
    }


}
