package co.com.sbaqueroadev.cyxtera.model.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Document
public class SessionCalculation {

    @Id
    private String id;
    @DBRef
    private ApplicationUser applicationUser;
    @DBRef
    private List<Calculation> calculations = new ArrayList<>();

    public SessionCalculation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public List<Calculation> getCalculations() {
        return calculations;
    }

    public void setCalculations(List<Calculation> calculations) {
        this.calculations = calculations;
    }

    public void addCalculation(Calculation... calculations) {
        if(this.calculations == null){
            this.calculations = new ArrayList<>();
        }
        this.calculations.addAll(Arrays.asList(calculations));
    }

    public void removeCalculation(Calculation... calculations) {
        if(this.calculations == null){
            this.calculations = new ArrayList<>();
        }
        final Calculation[] calculations1 = this.calculations != null ? this.calculations.stream().toArray(Calculation[]::new): new Calculation[]{};
        Arrays.stream(calculations).distinct().forEach(inputCalculation -> {
            SessionCalculation.this.calculations = Arrays.stream(calculations1).filter(calc ->
                    !inputCalculation.equals(calc)
            ).collect(Collectors.toList());
        });
    }
}
