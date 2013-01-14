package pl.insurance.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import pl.addresser.model.Street;
import pl.healthinformator.model.HealthCareData;
import pl.insurance.model.Address;
import pl.insurance.model.Client;
import pl.insurance.model.ClientProfile;
import pl.insurance.model.Contract;
import pl.insurance.model.Gender;
import pl.insurance.model.Price;
import pl.insurance.model.ValidyPeriod;
import pl.insurance.service.AddressService;
import pl.insurance.service.ClientService;
import pl.insurance.service.ContractService;
import pl.insurance.service.HealthCareService;
import pl.insurance.service.ProductPriceCalculatorService;
import pl.insurance.service.ProductService;
import pl.insurance.service.exception.NoHealthCareDataException;
import pl.pricecalculator.model.PriceClientInformation;
import pl.productlister.model.Product;
import pl.productlister.model.ProductClientInformation;

@javax.faces.bean.SessionScoped
@ManagedBean(name = "contractWizardController")
public class ContractWizardController implements Serializable {

	private static final long serialVersionUID = -5863910393374718958L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private Logger log;

	@Inject
	private ClientService clientService;

	@Inject
	private AddressService addressService;

	@Inject
	private ProductService productService;

	@Inject
	private HealthCareService healthCareService;

	@Inject
	private ProductPriceCalculatorService productPriceCalculatorService;

	@Inject
	private ContractService contractService;

	private HealthCareData healthCareData = null;
	private Client client = null;
	private ClientProfile clientProfile = null;
	private Contract contract = null;

	private List<Product> availibleProducts = null;

	private String pesel = null;

	private Price calculatedPrice = null;

	private Product selectedProduct = null;

	public String submitPesel() {
		if (client == null) {
			client = clientService.findClientByPESEL(pesel);
			if (client == null) {
				client = new Client();
				client.setGender(Gender.MALE);
				client.setPesel(pesel);
			}
		}
		return "/basicInfo.xhtml";
	}

	public String submitBasicInfo() {
		if (client.getAddress() == null) {
			client.setAddresses(new Address());
		}
		return "/address.xhtml";
	}

	public String submitAddress() {
		clientProfile = client.getClientProfile();
		if (clientProfile == null) {
			clientProfile = new ClientProfile();
		}
		return "/clientProfile.xhtml";
	}

	public String submitClientProfile() {
		if (isHealthCareDataLoaded()) {
			try {
				loadHealthCareDataForCurrentUser();
			} catch (NoHealthCareDataException e) {
				createNewHealthCareDataForCurrentClient();
			}
		}
		return "/healthCareInformation.xhtml";
	}

	public String submitHealthCareInformation() throws Exception {
		availibleProducts = productService
				.findAvailibleProducts(createProductClientInformation());
		return "/products.xhtml";
	}

	public String submitProduct() throws Exception {
		contract = new Contract();
		contract.setClient(client);
		contract.setHealthCareData(healthCareData);
		contract.setProduct(selectedProduct);
		contract.setValidFrom(new Date(System.currentTimeMillis()));
		contract.setValidityPeriod(ValidyPeriod.YEAR);

		calculatedPrice = productPriceCalculatorService.calculatePrice(createPriceClientInformation(),
				selectedProduct);
		contract.setPrice(calculatedPrice);
		return "/contractConfirm.xhtml";
	}

	public HealthCareData getHealthCareData() {
		return healthCareData;
	}

	public void setHealthCareData(HealthCareData healthCareData) {
		this.healthCareData = healthCareData;
	}

	private boolean isHealthCareDataLoaded() {
		return healthCareData == null;
	}

	private void loadHealthCareDataForCurrentUser() {
		healthCareData = healthCareService
				.clientHealthCareInformations(client);
		log.debug("Health care data loaded for current user...");
	}

	private void createNewHealthCareDataForCurrentClient() {
		log.debug("No stored health care data, creating a new one...");
		healthCareData = new HealthCareData();
		healthCareData.setFirstName(client.getFirstName());
		healthCareData.setLastName(client.getLastName());
		healthCareData.setPesel(client.getPesel());
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ClientProfile getClientProfile() {
		return clientProfile;
	}

	public void setClientProfile(ClientProfile clientProfile) {
		this.clientProfile = clientProfile;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String save() {
		try {
			client.setAddress(addressService.addAddress(client.getAddress()));

			healthCareData = healthCareService.save(contract.getHealthCareData());
			contract.setHealthCareData(healthCareData);
			
			client.setClientProfile(clientProfile);
			contract.setClient(clientService.registerClient(client));

			contractService.saveContract(contract);

		} catch (Exception e) {
			log.error("Error registering contract", e);
			facesContext.addMessage(null, new FacesMessage(
					"Błąd aktualizacji danych!!"));
			return null;

		}
		return "/contractOk.xhtml";
	}
	
	public String cancel() {
		healthCareData = null;
		client = null;
		clientProfile = null;
		contract = null;
		availibleProducts = null;
		pesel = null;
		calculatedPrice = null;
		selectedProduct = null;
		return "/pesel.xhtml";
	}

	public Gender[] getGenderValues() {
		return Gender.values();
	}

	public List<Street> suggestStreets(String pattern) {
		return addressService.suggestStreets(pattern);
	}

	public List<Product> getAvailibleProducts() {
		return availibleProducts;
	}

	public Double getCalculatedPrice() {
		return calculatedPrice.getValue();
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setCalculatedPrice(Double calculatedPrice) {
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	private ProductClientInformation createProductClientInformation() {
		ProductClientInformation productClientInformation = new ProductClientInformation();
		productClientInformation.setMale(client.getGender().equals(Gender.MALE));
		productClientInformation.setBirthDate(client.getBirthDate());

		productClientInformation.setMarried(clientProfile.getMarried());
		productClientInformation.setNumberOfKids(clientProfile.getNumberOfKids());
		productClientInformation.setDriving(clientProfile.getDriving());
		productClientInformation.setHouse(clientProfile.getHouse());
		productClientInformation.setHome(clientProfile.getHome());
		productClientInformation.setHigherEducation(clientProfile
				.getHigherEducation());
		productClientInformation.setWorking(clientProfile.getWorking());
		productClientInformation.setLearning(clientProfile.getLearning());

		productClientInformation.setHeartDisieases(healthCareData
				.getHeartDisieases());
		productClientInformation.setDiabetes(healthCareData.getDiabetes());
		productClientInformation
				.setMentalIllness(healthCareData.getMentalIllness());
		productClientInformation.setSuddenDeaths(healthCareData.getSuddenDeaths());
		productClientInformation.setOverweight(healthCareData.getOverweight());
		productClientInformation.setHypertension(healthCareData.getHypertension());

		productClientInformation.setLastHospitalisation(healthCareData
				.getLastHospitalisation());

		productClientInformation.setLastSurgery(healthCareData.getLastSurgery());

		productClientInformation.setLongTermTreatments(healthCareData
				.getLongTermTreatments());
		return productClientInformation;
	}
	
	private PriceClientInformation createPriceClientInformation() {
		PriceClientInformation priceClientInformation = new PriceClientInformation();
		priceClientInformation.setMale(client.getGender().equals(Gender.MALE));
		priceClientInformation.setBirthDate(client.getBirthDate());

		priceClientInformation.setMarried(clientProfile.getMarried());
		priceClientInformation.setNumberOfKids(clientProfile.getNumberOfKids());
		priceClientInformation.setDriving(clientProfile.getDriving());
		priceClientInformation.setHouse(clientProfile.getHouse());
		priceClientInformation.setHome(clientProfile.getHome());
		priceClientInformation.setHigherEducation(clientProfile
				.getHigherEducation());
		priceClientInformation.setWorking(clientProfile.getWorking());
		priceClientInformation.setLearning(clientProfile.getLearning());

		priceClientInformation.setHeartDisieases(healthCareData
				.getHeartDisieases());
		priceClientInformation.setDiabetes(healthCareData.getDiabetes());
		priceClientInformation
				.setMentalIllness(healthCareData.getMentalIllness());
		priceClientInformation.setSuddenDeaths(healthCareData.getSuddenDeaths());
		priceClientInformation.setOverweight(healthCareData.getOverweight());
		priceClientInformation.setHypertension(healthCareData.getHypertension());

		priceClientInformation.setLastHospitalisation(healthCareData
				.getLastHospitalisation());

		priceClientInformation.setLastSurgery(healthCareData.getLastSurgery());

		priceClientInformation.setLongTermTreatments(healthCareData
				.getLongTermTreatments());
		return priceClientInformation;
	}

}
