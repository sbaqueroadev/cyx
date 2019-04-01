package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Pow extends AppOperation {

    private static final Logger logger = LoggerFactory.getLogger(Pow.class);

    public Pow(String id) {
        super(id);
    }

    @JsonInclude
    public String getName(){
        return "Pow";
    }


    @Override
    public Integer calculate(List<Integer> numbers) throws OperandsException {
        logger.debug("Elevating numbers " + numbers.toString());
        if(numbers.size() == 2){
            return ((Double)Math.pow(numbers.get(0), numbers.get(1))).intValue();
        }
        throw new OperandsException("Only one number could be pow at another one. Review your operands");
    }
}
