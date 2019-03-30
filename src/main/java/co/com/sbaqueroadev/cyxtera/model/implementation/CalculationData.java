package co.com.sbaqueroadev.cyxtera.model.implementation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CalculationData extends Calculation{

    public CalculationData() {
    }

}
