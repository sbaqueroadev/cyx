package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class Multiplication extends AppOperation {

    private static final Logger logger = LoggerFactory.getLogger(Multiplication.class);

    public Multiplication(String id) {
        super(id);
    }

    @JsonInclude
    public String getName(){
        return "Multiplication";
    }


    @Override
    public Integer calculate(List<Integer> numbers) {
        logger.debug("Multiplying numbers " + numbers.toString());
        Optional<Integer> result = numbers.stream().reduce((item, res) -> res * item);
        return result.isPresent()?result.get():0;
    }
}
