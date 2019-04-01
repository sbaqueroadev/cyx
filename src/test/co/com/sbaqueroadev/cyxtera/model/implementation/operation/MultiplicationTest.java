package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import com.sun.tools.javac.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class MultiplicationTest extends TestCase {

    private Multiplication multiplication;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        multiplication = new Multiplication("mu");
    }

    @Test
    public void testGetName() {
        assertEquals(multiplication.getName(),"Multiplication");
    }

    @Test
    public void testCalculate() {
        assertEquals(multiplication.calculate(List.of(2,3)).intValue(),6);
    }
}