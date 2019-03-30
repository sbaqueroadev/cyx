package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;

import java.util.List;

public class Substraction extends AppOperation {

    @Override
    public Integer calculate(List<Integer> numbers) throws OperandsException {
        if(numbers.size() == 2){
            return numbers.get(0)-numbers.get(1);
        }
        throw new OperandsException("Only one number could be substracted from another one. Review your operands");
    }
}
