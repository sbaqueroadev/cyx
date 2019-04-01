package co.com.sbaqueroadev.cyxtera.model;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.model.implementation.*;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* SessionCalculationInterface:
*/
public interface SessionCalculationInterface {

	SessionCalculation addCalculation(SessionCalculation sessionCalculation, Calculation calculation);

	SessionCalculation insert(SessionCalculation sessionCalculation);

	Integer calculateResult(SessionCalculation sessionCalculation) throws OperandsException;

	void update(SessionCalculationData sessionData);

    SessionCalculation updateLastCalculation(SessionCalculation sessionCalculation, Calculation calculation);
}
