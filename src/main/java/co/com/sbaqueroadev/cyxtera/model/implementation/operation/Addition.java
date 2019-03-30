package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import java.util.List;
import java.util.Optional;

public class Addition extends AppOperation {

    @Override
    public Integer calculate(List<Integer> numbers) {
        Optional<Integer> result = numbers.stream().reduce((item, res) -> res + item);
        return result.isPresent()?result.get():0;
    }
}
