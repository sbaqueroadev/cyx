package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import com.sun.tools.javac.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class AdditionTest extends TestCase {

    private Addition addition;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        addition = new Addition("s");
    }

    @Test
    public void testGetName() {
        assertEquals(addition.getName(),"Addition");
    }

    @Test
    public void testCalculate() {
        assertEquals(addition.calculate(List.of(2,1)).intValue(),3);

    }
}