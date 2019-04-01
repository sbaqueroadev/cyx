package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Optional;

public class Addition extends AppOperation {

    public Addition(String id) {
        super(id);
    }

    @JsonInclude
    public String getName(){
        return "Adition";
    }

    @Override
    public Integer calculate(List<Integer> numbers) {
        System.out.println("Addition:   " + numbers.size());
        Optional<Integer> result = numbers.stream().reduce((item, res) -> res + item);
        System.out.println("Addition:   " + result.isPresent());
        return result.isPresent()?result.get():0;
    }
}
