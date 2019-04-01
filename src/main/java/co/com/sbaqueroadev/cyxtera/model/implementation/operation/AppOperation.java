package co.com.sbaqueroadev.cyxtera.model.implementation.operation;


import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


@Document
public abstract class AppOperation{

    @Id
    private String id;

    private String name;

    public String getName() {
        return name;
    }

    public AppOperation(String id) {
        this.id = id;
    }

    public abstract Integer calculate(List<Integer> numbers) throws OperandsException;

}
