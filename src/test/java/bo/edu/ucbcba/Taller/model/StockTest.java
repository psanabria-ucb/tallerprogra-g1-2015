package bo.edu.ucbcba.Taller.model;

        import bo.edu.ucbcba.Taller.exceptions.ValidationException;
        import org.junit.Before;
        import org.junit.Rule;
        import org.junit.Test;
        import org.junit.rules.ExpectedException;

        import static org.junit.Assert.*;

public class StockTest {
    private Stock movie;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        movie = new Stock();
    }

    @Test
    public void testSetName() {
        movie.setName("Good Title");
        assertEquals("Good Title", movie.getName());
    }

    @Test
    public void testSetNoName() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        movie.setName("");
    }

    @Test
    public void testSetNullName() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        movie.setName(null);
    }

    @Test
    public void testSetLongName() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        movie.setName(new String(new char[256]).replace('\0', 'a'));
    }

    @Test
    public void testSetCode() {
        movie.setCode("Good Title");
        assertEquals("Good Title", movie.getCode());
    }

    @Test
    public void testSetNoCode() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title can't be empty");
        movie.setCode("");
    }

    @Test
    public void testSetNullCode() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Null title");
        movie.setCode(null);
    }

    @Test
    public void testSetLongCode() {
        exception.expect(ValidationException.class);
        exception.expectMessage("Title is too long");
        movie.setCode(new String(new char[256]).replace('\0', 'a'));
    }
}


