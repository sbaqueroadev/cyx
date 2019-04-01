package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.exceptions.OperationException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Division extends AppOperation {

    private static final Logger logger = LoggerFactory.getLogger(Division.class);

    public Division(String id) {
        super(id);
    }

    @JsonInclude
    public String getName(){
        return "Division";
    }

    @Override
    public Integer calculate(List<Integer> numbers) throws OperandsException {
        logger.debug("Dividing numbers " + numbers.toString());
        if(numbers.size() == 2){
            return numbers.get(0)/numbers.get(1);
        }
        OperandsException e = new OperandsException("Only one number could be divided by another one. Review your operands");
        logger.error("Error adding operation: ",e);
        throw e;
    }
}
