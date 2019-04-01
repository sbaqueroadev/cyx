package co.com.sbaqueroadev.cyxtera.services;

import co.com.sbaqueroadev.cyxtera.dao.CalculationRepository;
import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.exceptions.OperationException;
import co.com.sbaqueroadev.cyxtera.model.CalculationInterface;
import co.com.sbaqueroadev.cyxtera.model.implementation.Calculation;
import co.com.sbaqueroadev.cyxtera.model.implementation.CalculationData;
import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculation;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * @author: gasdsba - sbaqueroa@gmail.com
 * CalculationServiceImpl:
 */
@Service
public class CalculationService implements CalculationInterface {

    private static final long SIX_MONTHS = 24*36*18000000;
    @Autowired
	private CalculationRepository calculationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CalculationService.class);

	public CalculationService() {
		super();
	}


    @Override
    public Calculation findById(String id) {
        logger.debug("Looking for calculation by Id");
        Optional<Calculation> calculation= calculationRepository.findById(id);
        return calculation.isPresent()?calculation.get():null;
    }

    @Override
    public Calculation insert(Calculation calculation) {
        logger.debug("Persisting calculation");
	    return calculationRepository.insert(calculation);
    }

    @Override
    public Integer calculate(Calculation calculation) throws OperandsException {
        logger.debug("Calculating operation result");
        return calculation.calculate();
    }

    @Override
    public Calculation addOperation(Calculation calculation, AppOperation operation) throws OperationException {
	    if(calculation.getAppOperation() != null){
            OperationException e = new OperationException("You need to add more operands to create a new operation");
            logger.error("Error adding operation: ",e);
            throw e;
        }
        if(calculation.getNumbers() != null && calculation.getNumbers().size() == 0){
            OperationException e = new OperationException("You need to add operands to create this operation");
            logger.error("Error adding operation: ",e);
            throw e;
        }
        logger.debug("Setting Operation");
        calculation.setAppOperation(operation);
        return calculation;
    }

    @Override
    public Calculation addNumbers(Calculation calculation, List<Integer> numbers) {
        calculation.addNumbers(numbers);
        return calculation;
    }

    @Override
    public void update(CalculationData calculationData) {
        calculationRepository.save(calculationData);
    }
}
