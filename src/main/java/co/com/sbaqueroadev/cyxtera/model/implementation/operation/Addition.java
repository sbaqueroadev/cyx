package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class Addition extends AppOperation {

    private static final Logger logger = LoggerFactory.getLogger(Addition.class);

    public Addition(String id) {
        super(id);
    }

    @JsonInclude
    public String getName(){
        return "Addition";
    }

    @Override
    public Integer calculate(List<Integer> numbers) {
        logger.debug("Adding numbers " + numbers.toString());
        Optional<Integer> result = numbers.stream().reduce((item, res) -> res + item);
        return result.isPresent()?result.get():0;
    }
}
