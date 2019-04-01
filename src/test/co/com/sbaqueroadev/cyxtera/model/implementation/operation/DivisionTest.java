package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import com.sun.tools.javac.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class DivisionTest extends TestCase {

    private Division division;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        division = new Division("d");
    }

    @Test
    public void testGetName() {
        assertEquals(division.getName(),"Division");
    }

    @Test
    public void testCalculate() {
        try {
            assertEquals(division.calculate(List.of(2,1)).intValue(),2);
        } catch (OperandsException e) {
            assertTrue(false);
        }
    }
}