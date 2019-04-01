package co.com.sbaqueroadev.cyxtera.model;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.exceptions.OperationException;
import co.com.sbaqueroadev.cyxtera.model.implementation.Calculation;
import co.com.sbaqueroadev.cyxtera.model.implementation.CalculationData;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperation;

import java.util.List;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* CalculationInterface:
*/
public interface CalculationInterface {

	Calculation findById(String Id);

	Calculation insert(Calculation calculation);

	Integer calculate(Calculation calculation) throws OperandsException;

	Calculation addOperation(Calculation calculation, AppOperation operation) throws OperationException;

	Calculation addNumbers(Calculation calculation, List<Integer> numbers);

	void update(CalculationData calculationData);
}
