
/* Archivo: TeacherInterface.java
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
