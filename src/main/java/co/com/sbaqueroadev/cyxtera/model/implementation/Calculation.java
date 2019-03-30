package co.com.sbaqueroadev.cyxtera.model.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

@Document
public class Measure {

	@Id
	private String id;
    @DBRef
    private Unit unit;
    private Date date;
	private String dateMeasured;
    private String option;
	private Double value;
	@DBRef
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Pacient pacient;
	@DBRef
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private SessionCalculation sessionCalculation;
	private Status status = Status.PENDING;
	@DBRef
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Sick sick;

	private static final SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");

	public Measure(Measure measure) {
		this.unit = measure.getUnit();
		this.value = measure.getValue();
		this.date = measure.getDate();
		this.status = measure.getStatus();
		this.pacient = measure.getPacient();
		this.dateMeasured = sf.format(measure.getDate());
	}

	private Status getStatus() {
		return this.status;
	}

	public String getId() {
		return this.id;
	}

	/**
	 * @param value
	 * @param user
	 */
	public Measure(Double value, ApplicationUser user) {
		this.value = value;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Measure() {

	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		this.dateMeasured = sf.format(date);
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Pacient getPacient() {
		return pacient;
	}

	public void setPacient(Pacient pacient) {
		this.pacient = pacient;
	}

	public void setSick(Sick sick) {
		this.sick = sick;
	}

	public enum Status {
		PENDING(0), READ(1), NEXT(2);

		private final int status;

		Status(int status) {
			this.status = status;
		}
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Sick getSick() {
		return sick;
	}

	public SessionCalculation getSessionCalculation() {
		return sessionCalculation;
	}

	public void setSessionCalculation(SessionCalculation sessionCalculation) {
		this.sessionCalculation = sessionCalculation;
	}

	public String getDateMeasured() {
		return dateMeasured;
	}

	public void setDateMeasured(String dateMeasured) {
		this.dateMeasured = dateMeasured;
	}
}
