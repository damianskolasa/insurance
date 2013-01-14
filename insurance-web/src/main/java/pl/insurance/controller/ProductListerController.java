package pl.insurance.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import pl.insurance.service.ProductService;
import pl.productlister.model.Product;
import pl.productlister.model.ProductClientInformation;

@ViewScoped
@ManagedBean(name="productListerController")
public class ProductListerController implements Serializable {

	private static final long serialVersionUID = -5744294395686958233L;
	
	private List<Product> availibleProducts = null;
	
	@Inject
	private ProductService productService;
	
	public String showProducts() throws Exception {
		availibleProducts = productService
				.findAvailibleProducts(new ProductClientInformation());
		return null;
	}
	
	public List<Product> getAvailibleProducts() {
		return availibleProducts;
	}
}
