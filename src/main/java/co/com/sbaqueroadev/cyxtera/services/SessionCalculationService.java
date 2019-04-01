package co.com.sbaqueroadev.cyxtera.services;

import co.com.sbaqueroadev.cyxtera.dao.SessionCalculationRepository;
import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.model.SessionCalculationInterface;
import co.com.sbaqueroadev.cyxtera.model.implementation.Calculation;
import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculation;
import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author: gasdsba - sbaqueroa@gmail.com
 * SessionCalculationService:
 */
@Service
public class SessionCalculationService implements SessionCalculationInterface {

    private static final long SIX_MONTHS = 24*36*18000000;
    @Autowired
	private SessionCalculationRepository sessionCalculationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final Logger logger = LoggerFactory.getLogger(SessionCalculationService.class);

	public SessionCalculationService() {
		super();
	}

    @Override
    public SessionCalculation addCalculation(SessionCalculation sessionCalculation, Calculation calculation) {
        sessionCalculation.addCalculation(calculation);
        logger.debug("Adding calculation");
        return sessionCalculation;
    }

    @Override
    public SessionCalculation insert(SessionCalculation sessionCalculation) {
        logger.debug("Persisting session calculation");
        return sessionCalculationRepository.insert(sessionCalculation);
    }

    @Override
    public Integer calculateResult(SessionCalculation sessionCalculation) throws OperandsException{
        Integer result = 0;
        int index = 0;
        logger.debug("Iterating over calculations");
        for(Calculation item : sessionCalculation.getCalculations())
        {
            if (index > 0) {
                List<Integer> numbers = item.getNumbers();
                numbers.add(0, result);
                item.setNumbers(numbers);
            }
            if(item.getAppOperation()!=null){
                logger.debug("Calculating operation result");
                result = item.calculate();
            }
            if (index++ > 0) {
                List<Integer> numbers = item.getNumbers();
                numbers.remove(0);
                item.setNumbers(numbers);
            }
        }
        return result;
    }

    @Override
    public void update(SessionCalculationData sessionData) {
        logger.debug("Persisting session calculation");
	    sessionCalculationRepository.save(sessionData);
    }

    @Override
    public SessionCalculation updateLastCalculation(SessionCalculation sessionCalculation, Calculation calculation) {
        logger.debug("Updating latest calculation");
        List<Calculation> calcs = sessionCalculation.getCalculations();
        if(calcs.size()>0) {
            calcs.set(sessionCalculation.getCalculations().size() - 1, calculation);
        }else{
            calcs.add(calculation);
        }
        sessionCalculation.setCalculations(calcs);
        return sessionCalculation;
    }
}
