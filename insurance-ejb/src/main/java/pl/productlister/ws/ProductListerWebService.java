package pl.productlister.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import pl.productlister.model.Product;
import pl.productlister.model.ProductClientInformation;

@WebService
public interface ProductListerWebService {

	@WebMethod
	public List<Product> findAvailibleProducts(
			ProductClientInformation prductClientInformation);
	
	@WebMethod
	public Product findProductById(Long id);
}
