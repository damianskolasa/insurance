package pl.insurance.service;

import javax.ejb.Stateless;

import pl.insurance.model.Client;
import pl.insurance.model.Price;
import pl.insurance.model.Product;


@Stateless
public class ProductPriceCalculatorService {

	
	public Price calculatePrice(Client client, Product product) {
		Price price = new Price();
		price.setValue(125.0);
		return price;
	}
}
