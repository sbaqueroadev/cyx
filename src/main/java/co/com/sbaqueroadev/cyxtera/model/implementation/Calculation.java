package co.com.sbaqueroadev.cyxtera.model.implementation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Calculation {

	@Id
	private String id;
	private List<Integer> numbers = new ArrayList<>();
    private AppOperation appOperation;

	public String getId() {
		return this.id;
	}

	public Calculation() {

	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public AppOperation getAppOperation() {
		return appOperation;
	}

	public void setAppOperation(AppOperation appOperation) {
		this.appOperation = appOperation;
	}

	public Integer calculate() throws OperandsException {
		return getAppOperation()!=null?getAppOperation().calculate(getNumbers()):0;
	}

	public void addNumbers(List<Integer> numbers) {
		this.numbers.addAll(this.numbers.size(),numbers);
	}
}
