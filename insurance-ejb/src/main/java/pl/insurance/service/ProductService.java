package pl.insurance.service;

import java.net.URL;
import java.util.List;

import javax.ejb.Stateless;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import pl.productlister.model.Product;
import pl.productlister.model.ProductClientInformation;
import pl.productlister.ws.ProductListerWebService;

@Stateless
public class ProductService {

	private ProductListerWebService getService() throws Exception {
		URL url = new URL(
				"http://localhost:8081/productlister/ProductListerWebServiceImpl?wsdl");

		QName qname = new QName("http://ws.productlister.pl/",
				"ProductListerWebServiceImplService");

		Service service = Service.create(url, qname);

		return service.getPort(ProductListerWebService.class);
	}
	
	public Product findProductById(Long id) throws Exception {
		return getService().findProductById(id);
	}

	public List<Product> findAvailibleProducts (ProductClientInformation productClientInformation) throws Exception  {

		return getService().findAvailibleProducts(productClientInformation);
	}

}
