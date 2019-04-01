package co.com.sbaqueroadev.cyxtera.services;

import co.com.sbaqueroadev.cyxtera.dao.SessionCalculationRepository;
import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.model.implementation.CalculationData;
import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculationData;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperationFactory;
import com.sun.tools.javac.util.List;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@DataMongoTest
public class SessionCalculationServiceTest {

    @Autowired
    private SessionCalculationService sessionCalculationService;
    @Autowired
    private SessionCalculationRepository sessionCalculationRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    private SessionCalculationData sessionCalculationData;

    private CalculationData calculationData;

    @Before
    public void setUp() throws Exception {
        calculationData = new CalculationData();
        calculationData.setAppOperation(AppOperationFactory.createOperation("s","id"));
        calculationData.setNumbers(new ArrayList<>(List.of(1,2,3)));
        calculationData.setId("id1");

        sessionCalculationData = new SessionCalculationData();
        sessionCalculationData.setId("idc");
    }

    @Test
    public void testAddCalculation() {
        sessionCalculationService.addCalculation(sessionCalculationData, calculationData);
        Assert.assertEquals(calculationData, sessionCalculationData.getCalculations().get(0));
    }

    @Test
    public void testCalculateResult() {
        sessionCalculationService.addCalculation(sessionCalculationData, calculationData);
        try {
            Assert.assertEquals(sessionCalculationService.calculateResult(sessionCalculationData).intValue(), 6);
        } catch (OperandsException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testUpdateLastCalculation() {
        sessionCalculationService.updateLastCalculation(sessionCalculationData, calculationData);
        Assert.assertEquals(calculationData, sessionCalculationData.getCalculations().get(0));
    }
}