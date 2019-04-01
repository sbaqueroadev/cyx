package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.Pow;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.Substraction;
import com.sun.tools.javac.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class PowTest extends TestCase {

    private Pow pow;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        pow = new Pow("p");
    }

    @Test
    public void testGetName() {
        assertEquals(pow.getName(),"Pow");
    }

    @Test
    public void testCalculate() {
        try {
            assertEquals(pow.calculate(List.of(2,2)).intValue(),4);
        } catch (OperandsException e) {
            assertTrue(false);
        }
    }
}