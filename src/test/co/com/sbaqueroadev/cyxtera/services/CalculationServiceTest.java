package co.com.sbaqueroadev.cyxtera.services;

import co.com.sbaqueroadev.cyxtera.dao.SessionCalculationRepository;
import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.exceptions.OperationException;
import co.com.sbaqueroadev.cyxtera.model.implementation.Calculation;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperation;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperationFactory;
import com.sun.tools.javac.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CalculationServiceTest extends TestCase {

    @Autowired
    private CalculationService calculationService;
    @Autowired
    private SessionCalculationRepository sessionCalculationRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    private Calculation calculation;

    private AppOperation appOperation;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        calculation = new Calculation();
        calculation.setNumbers(new ArrayList<>(List.of(1,2,3)));
        calculation.setId("idc");
        appOperation = AppOperationFactory.createOperation("m","idm");
    }
    @Test
    public void testCalculate() {
        try {
            calculation.setAppOperation(AppOperationFactory.createOperation("s","id"));
            assertEquals(calculationService.calculate(calculation).intValue(),6);
        } catch (OperandsException e) {
            assertTrue(false);
        } catch (OperationException e) {
            assertTrue(false);
        }
    }
    @Test
    public void testAddOperation() {
        try {
            calculation = calculationService.addNumbers(calculation, List.of(4,5,6));
            assertEquals(calculationService.addOperation(calculation, appOperation).getAppOperation(),appOperation);
        } catch (OperationException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testAddNumbers() {
        calculation = calculationService.addNumbers(calculation, List.of(4,5,6));
        assertEquals(calculation.getNumbers().get(0).intValue(),1);
        assertEquals(calculation.getNumbers().get(1).intValue(),2);
        assertEquals(calculation.getNumbers().get(2).intValue(),3);
        assertEquals(calculation.getNumbers().get(3).intValue(),4);
        assertEquals(calculation.getNumbers().get(4).intValue(),5);
        assertEquals(calculation.getNumbers().get(5).intValue(),6);
    }
}