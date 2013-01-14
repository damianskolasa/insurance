package pl.pricecalculator.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PriceClientInformation implements Serializable {
	
	private static final long serialVersionUID = 1787618780304911510L;

	private boolean male;
	private Date birthDate;
	
	private boolean married;
	private Integer numberOfKids;
	private boolean driving;
	private boolean house;
	private boolean home;
	private boolean higherEducation;
	private boolean working;
	private boolean learning;

	private boolean heartDisieases;
	private boolean diabetes;
	private boolean mentalIllness;
	private boolean suddenDeaths;
	private boolean overweight;
	private boolean hypertension;

	private Date lastHospitalisation;

	private Date lastSurgery;

	private boolean longTermTreatments;

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public Integer getNumberOfKids() {
		return numberOfKids;
	}

	public void setNumberOfKids(Integer numberOfKids) {
		this.numberOfKids = numberOfKids;
	}

	public boolean isDriving() {
		return driving;
	}

	public void setDriving(boolean driving) {
		this.driving = driving;
	}

	public boolean isHouse() {
		return house;
	}

	public void setHouse(boolean house) {
		this.house = house;
	}

	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

	public boolean isHigherEducation() {
		return higherEducation;
	}

	public void setHigherEducation(boolean higherEducation) {
		this.higherEducation = higherEducation;
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean working) {
		this.working = working;
	}

	public boolean isLearning() {
		return learning;
	}

	public void setLearning(boolean learning) {
		this.learning = learning;
	}

	public boolean isHeartDisieases() {
		return heartDisieases;
	}

	public void setHeartDisieases(boolean heartDisieases) {
		this.heartDisieases = heartDisieases;
	}

	public boolean isDiabetes() {
		return diabetes;
	}

	public void setDiabetes(boolean diabetes) {
		this.diabetes = diabetes;
	}

	public boolean isMentalIllness() {
		return mentalIllness;
	}

	public void setMentalIllness(boolean mentalIllness) {
		this.mentalIllness = mentalIllness;
	}

	public boolean isSuddenDeaths() {
		return suddenDeaths;
	}

	public void setSuddenDeaths(boolean suddenDeaths) {
		this.suddenDeaths = suddenDeaths;
	}

	public boolean isOverweight() {
		return overweight;
	}

	public void setOverweight(boolean overweight) {
		this.overweight = overweight;
	}

	public boolean isHypertension() {
		return hypertension;
	}

	public void setHypertension(boolean hypertension) {
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

	public boolean isLongTermTreatments() {
		return longTermTreatments;
	}

	public void setLongTermTreatments(boolean longTermTreatments) {
		this.longTermTreatments = longTermTreatments;
	}
	
	
}
