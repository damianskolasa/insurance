package pl.insurance.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Contract implements Serializable {

	private static final long serialVersionUID = 6786776534895293003L;

	@Id
	@GeneratedValue
	private Long id;

	private String contractNumber;
	@Temporal(TemporalType.DATE)
	private Date validFrom;

	@Enumerated(EnumType.STRING)
	private ValidyPeriod validityPeriod;

	@ManyToOne
	private Client client;

	@ManyToOne
	private Product product;

	private Price price;

	@ManyToOne
	private HealthCareData healthCareData;

	public Contract() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public ValidyPeriod getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(ValidyPeriod validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public HealthCareData getHealthCareData() {
		return healthCareData;
	}

	public void setHealthCareData(HealthCareData healthCareData) {
		this.healthCareData = healthCareData;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
