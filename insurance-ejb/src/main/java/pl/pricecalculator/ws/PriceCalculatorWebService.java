package pl.pricecalculator.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import pl.pricecalculator.model.PriceClientInformation;


@WebService
@SOAPBinding(style = Style.RPC)
public interface PriceCalculatorWebService {

	@WebMethod
	public Double calculatePrice(String productCode,
			PriceClientInformation priceClientInformation);
}
