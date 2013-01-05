package kolasa.wojcik.insurance.service;

import javax.ejb.Stateless;

import kolasa.wojcik.insurance.model.Client;
import kolasa.wojcik.insurance.model.Price;
import kolasa.wojcik.insurance.model.Product;

@Stateless
public class ProductPriceCalculatorService {

	
	public Price calculatePrice(Client client, Product product) {
		Price price = new Price();
		price.setValue(125.0);
		return price;
	}
}
