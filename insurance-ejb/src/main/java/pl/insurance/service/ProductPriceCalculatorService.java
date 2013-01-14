package pl.insurance.service;

import java.net.URL;

import javax.ejb.Stateless;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import pl.insurance.model.Price;
import pl.pricecalculator.model.PriceClientInformation;
import pl.pricecalculator.ws.PriceCalculatorWebService;
import pl.productlister.model.Product;

@Stateless
public class ProductPriceCalculatorService {

	private PriceCalculatorWebService getService() throws Exception {
		URL url = new URL(
				"http://localhost:8081/pricecalculator/PriceCalculatorWebServiceImpl?wsdl");

		QName qname = new QName("http://ws.pricecalculator.pl/",
				"PriceCalculatorWebServiceImplService");

		Service service = Service.create(url, qname);

		return service.getPort(PriceCalculatorWebService.class);
	}

	public Price calculatePrice(PriceClientInformation priceClientInformation, Product product)  throws Exception{
		Price price = new Price();
		price.setValue(getService().calculatePrice(product.getCode(), priceClientInformation));
		return price;
	}
}
