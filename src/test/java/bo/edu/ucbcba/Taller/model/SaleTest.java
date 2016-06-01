package bo.edu.ucbcba.Taller.model;

import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class SaleTest {
    private Sale movie;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        movie = new Sale();
    }

    @Test
    public void testSetTitle() {
        movie.setD("Good Title");
        assertEquals("Good Title", movie.getD());
    }

    @Test
    public void testSetNoTitle() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        movie.setD("");
    }

    @Test
    public void testSetNullTitle() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        movie.setD(null);
    }

    @Test
    public void testSetLongTitle() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        movie.setD(new String(new char[256]).replace('\0', 'a'));
    }
}


