
/* Archivo: TeacherServiceImpl.java
 * Fecha: 21/12/2017
 * Todos los derechos de propiedad intelectual e industrial sobre esta
 * aplicacion son de propiedad exclusiva de Sergio Baquero Ariza
 * Su uso, alteracion, reproduccion o modificacion sin la debida
 * consentimiento por escrito de Sergio Baquero Ariza.
 * 
 * Este programa se encuentra protegido por las disposiciones de la
 * Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
 * propiedad intelectual. Su uso no autorizado dará lugar a las sanciones
 * previstas en la Ley.
 */

package co.com.sbaqueroadev.cyxtera.services;

import co.com.sbaqueroadev.cyxtera.dao.CalculationRepository;
import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.exceptions.OperationException;
import co.com.sbaqueroadev.cyxtera.model.CalculationInterface;
import co.com.sbaqueroadev.cyxtera.model.implementation.Calculation;
import co.com.sbaqueroadev.cyxtera.model.implementation.CalculationData;
import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculation;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * @author: gasdsba - sbaqueroa@gmail.com
 * TeacherServiceImpl:  
 */
@Service
public class CalculationService implements CalculationInterface {

    private static final long SIX_MONTHS = 24*36*18000000;
    @Autowired
	private CalculationRepository calculationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

	public CalculationService() {
		super();
	}


    @Override
    public Calculation findById(String id) {
        Optional<Calculation> calculation= calculationRepository.findById(id);
        return calculation.isPresent()?calculation.get():null;
    }

    @Override
    public Calculation insert(Calculation calculation) {
        return calculationRepository.insert(calculation);
    }

    @Override
    public Integer calculate(Calculation calculation) throws OperandsException {
        return calculation.calculate();
    }

    @Override
    public Calculation addOperation(Calculation calculation, AppOperation operation) throws OperationException {
	    if(calculation.getAppOperation() != null){
	        throw new OperationException("You need to add more operands to create a new operation");
        }
        if(calculation.getNumbers() != null || calculation.getNumbers().size() == 0){
            throw new OperationException("You need to operands to create this operation");
        }
        calculation.setAppOperation(operation);
        return calculation;
    }

    @Override
    public void addNumbers(Calculation calculation, List<Integer> numbers) {
        calculation.addNumbers(numbers);
    }

    @Override
    public void update(CalculationData calculationData) {
        calculationRepository.save(calculationData);
    }
}
