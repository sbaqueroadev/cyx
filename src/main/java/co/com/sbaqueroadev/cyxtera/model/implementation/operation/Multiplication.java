package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Optional;

public class Multiplication extends AppOperation {

    public Multiplication(String id) {
        super(id);
    }

    @JsonInclude
    public String getName(){
        return "Multiplication";
    }


    @Override
    public Integer calculate(List<Integer> numbers) {
        Optional<Integer> result = numbers.stream().reduce((item, res) -> res * item);
        return result.isPresent()?result.get():0;
    }
}
