package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import com.sun.tools.javac.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;

public class SubstractionTest extends TestCase {

    private Substraction substraction;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        substraction = new Substraction("s");
    }

    @Test
    public void testGetName() {
        assertEquals(substraction.getName(),"Substraction");
    }

    @Test
    public void testCalculate() {
        try {
            assertEquals(substraction.calculate(List.of(2,1)).intValue(),1);
        } catch (OperandsException e) {
            assertTrue(false);
        }
    }
}