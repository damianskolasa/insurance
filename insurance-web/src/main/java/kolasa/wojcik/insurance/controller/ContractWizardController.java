package kolasa.wojcik.insurance.controller;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import kolasa.wojcik.insurance.model.Address;
import kolasa.wojcik.insurance.model.Client;
import kolasa.wojcik.insurance.model.ClientInformationDTO;
import kolasa.wojcik.insurance.model.ClientProfile;
import kolasa.wojcik.insurance.model.Contract;
import kolasa.wojcik.insurance.model.Gender;
import kolasa.wojcik.insurance.model.HealthCareData;
import kolasa.wojcik.insurance.model.Price;
import kolasa.wojcik.insurance.model.Product;
import kolasa.wojcik.insurance.model.Street;
import kolasa.wojcik.insurance.service.AddressService;
import kolasa.wojcik.insurance.service.ClientService;
import kolasa.wojcik.insurance.service.HealthCareService;
import kolasa.wojcik.insurance.service.ProductPriceCalculatorService;
import kolasa.wojcik.insurance.service.ProductService;
import kolasa.wojcik.insurance.service.exception.NoHealthCareDataException;

import org.apache.log4j.Logger;

@javax.faces.bean.SessionScoped
@ManagedBean(name="contractWizardController")
public class ContractWizardController {

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
	@CurrentClient
	private Client currentClient;

	private HealthCareData healthCareData = null;
	private Client client = new Client();
	private ClientProfile clientProfile = null;
	private Contract contract = null;

	private List<Product> availibleProducts = null;

	private String pesel;

	private Price calculatedPrice = null;

	private Product selectedProduct = null;

	public String submitPesel() {
		client = clientService.findClientByPESEL(pesel);
		if (client == null) {
			client = new Client();
			client.setGender(Gender.MALE);
			client.setPesel(pesel);
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
	
	public String submitHealthCareInformation() {
		availibleProducts = productService
				.findAvailibleProducts(createClientInformationDTO());
		return "/products.xhtml";
	}
	
	public String submitProduct() {
		contract.setPrice(calculatedPrice);
		contract.setHealthCareData(healthCareData);
		contract.setProduct(selectedProduct);
		contract.setValidFrom(new Date(System.currentTimeMillis()));

		calculatedPrice = productPriceCalculatorService.calculatePrice(
				client, selectedProduct);
		return "/confirm.xhtml";
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
				.clientHealthCareInformations(currentClient);
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
			clientService.registerClient(client);

			healthCareService.save(healthCareData);

		} catch (Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(
					"Błąd aktualizacji danych!!"));
		}
		return null;
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

	private ClientInformationDTO createClientInformationDTO() {
		ClientInformationDTO clientInformationDTO = new ClientInformationDTO();
		clientInformationDTO.setMale(client.getGender().equals(Gender.MALE));
		clientInformationDTO.setBirthDate(client.getBirthDate());

		clientInformationDTO.setMarried(clientProfile.getMarried());
		clientInformationDTO.setNumberOfKids(clientProfile.getNumberOfKids());
		clientInformationDTO.setDriving(clientProfile.getDriving());
		clientInformationDTO.setHouse(clientProfile.getHouse());
		clientInformationDTO.setHome(clientProfile.getHome());
		clientInformationDTO.setHigherEducation(clientProfile
				.getHigherEducation());
		clientInformationDTO.setWorking(clientProfile.getWorking());
		clientInformationDTO.setLearning(clientProfile.getLearning());

		clientInformationDTO.setHeartDisieases(healthCareData
				.getHeartDisieases());
		clientInformationDTO.setDiabetes(healthCareData.getDiabetes());
		clientInformationDTO
				.setMentalIllness(healthCareData.getMentalIllness());
		clientInformationDTO.setSuddenDeaths(healthCareData.getSuddenDeaths());
		clientInformationDTO.setOverweight(healthCareData.getOverweight());
		clientInformationDTO.setHypertension(healthCareData.getHypertension());

		clientInformationDTO.setLastHospitalisation(healthCareData
				.getLastHospitalisation());

		clientInformationDTO.setLastSurgery(healthCareData.getLastSurgery());

		clientInformationDTO.setLongTermTreatments(healthCareData
				.getLongTermTreatments());
		return clientInformationDTO;
	}

}
