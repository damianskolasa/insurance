package pl.insurance.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClientProfile implements Serializable {

	private static final long serialVersionUID = -8480466778578074667L;

	@Id
	@GeneratedValue
	private Long id;

	private Boolean married;
	private Integer numberOfKids;
	private Boolean driving;
	private Boolean house;
	private Boolean home;
	private Boolean higherEducation;
	private Boolean working;
	private Boolean learning;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getMarried() {
		return married;
	}

	public void setMarried(Boolean married) {
		this.married = married;
	}

	public Integer getNumberOfKids() {
		return numberOfKids;
	}

	public void setNumberOfKids(Integer numberOfKids) {
		this.numberOfKids = numberOfKids;
	}

	public Boolean getDriving() {
		return driving;
	}

	public void setDriving(Boolean driving) {
		this.driving = driving;
	}

	public Boolean getHouse() {
		return house;
	}

	public void setHouse(Boolean house) {
		this.house = house;
	}

	public Boolean getHome() {
		return home;
	}

	public void setHome(Boolean home) {
		this.home = home;
	}

	public Boolean getHigherEducation() {
		return higherEducation;
	}

	public void setHigherEducation(Boolean higherEducation) {
		this.higherEducation = higherEducation;
	}

	public Boolean getWorking() {
		return working;
	}

	public void setWorking(Boolean working) {
		this.working = working;
	}

	public Boolean getLearning() {
		return learning;
	}

	public void setLearning(Boolean learning) {
		this.learning = learning;
	}

}
