package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Pow extends AppOperation {

    public Pow(String id) {
        super(id);
    }

    @JsonInclude
    public String getName(){
        return "Pow";
    }


    @Override
    public Integer calculate(List<Integer> numbers) throws OperandsException {
        if(numbers.size() == 2){
            return ((Double)Math.pow(numbers.get(0), numbers.get(1))).intValue();
        }
        throw new OperandsException("Only one number could be pow at another one. Review your operands");
    }
}
