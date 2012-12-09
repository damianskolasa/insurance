package kolasa.wojcik.insurance.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class HealthCareData {

	@Id
	@GeneratedValue
	private Long id;

	private Boolean heartDisieases;
	private Boolean diabetes;
	private Boolean mentalIllness;
	private Boolean suddenDeaths;
	private Boolean overweight;
	private Boolean hypertension;

	@Temporal(TemporalType.DATE)
	private Date lastHospitalisation;

	@Temporal(TemporalType.DATE)
	private Date lastSurgery;

	private Boolean longTermTreatments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getHeartDisieases() {
		return heartDisieases;
	}

	public void setHeartDisieases(Boolean heartDisieases) {
		this.heartDisieases = heartDisieases;
	}

	public Boolean getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(Boolean diabetes) {
		this.diabetes = diabetes;
	}

	public Boolean getMentalIllness() {
		return mentalIllness;
	}

	public void setMentalIllness(Boolean mentalIllness) {
		this.mentalIllness = mentalIllness;
	}

	public Boolean getSuddenDeaths() {
		return suddenDeaths;
	}

	public void setSuddenDeaths(Boolean suddenDeaths) {
		this.suddenDeaths = suddenDeaths;
	}

	public Boolean getOverweight() {
		return overweight;
	}

	public void setOverweight(Boolean overweight) {
		this.overweight = overweight;
	}

	public Boolean getHypertension() {
		return hypertension;
	}

	public void setHypertension(Boolean hypertension) {
		this.hypertension = hypertension;
	}

	public Date getLastHospitalisation() {
		return lastHospitalisation;
	}

	public void setLastHospitalisation(Date lastHospitalisation) {
		this.lastHospitalisation = lastHospitalisation;
	}

	public Date getLastSurgery() {
		return lastSurgery;
	}

	public void setLastSurgery(Date lastSurgery) {
		this.lastSurgery = lastSurgery;
	}

	public Boolean getLongTermTreatments() {
		return longTermTreatments;
	}

	public void setLongTermTreatments(Boolean longTermTreatments) {
		this.longTermTreatments = longTermTreatments;
	}

}
