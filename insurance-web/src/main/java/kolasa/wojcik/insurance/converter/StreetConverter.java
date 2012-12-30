package kolasa.wojcik.insurance.converter;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import kolasa.wojcik.insurance.model.Street;
import kolasa.wojcik.insurance.service.AddressService;

@FacesConverter("streetConverter")
public class StreetConverter implements Converter {

	public Object getAsObject(FacesContext facesContext, UIComponent arg1,
			String arg2) {

		return getAddressService(facesContext).findStreetByDescription(arg2);
	}

	public String getAsString(FacesContext facesContext, UIComponent arg1,
			Object arg2) {
		if (arg2 != null) {
			System.out.println("Converting to string: " + arg2);
			return String.valueOf(((Street) arg2).getDescription());
		}
		return null;
	}

	private AddressService getAddressService(FacesContext facesContext) {
		BeanManager bm = getBeanManager(facesContext);
		@SuppressWarnings("unchecked")
		Bean<AddressService> bean = (Bean<AddressService>) bm
				.getBeans(AddressService.class).iterator().next();
		CreationalContext<AddressService> ctx = bm
				.createCreationalContext(bean);
		AddressService dao = (AddressService) bm.getReference(bean,
				AddressService.class, ctx);
		return dao;
	}

	private BeanManager getBeanManager(FacesContext facesContext) {
		/*
		 * ServletContext servletContext = (ServletContext) facesContext
		 * .getExternalContext().getContext();
		 * 
		 * BeanManager beanManager = (BeanManager) servletContext
		 * .getAttribute("javax.enterprise.inject.spi.BeanManager");
		 * 
		 * return beanManager;
		 */

		try {
			InitialContext initialContext = new InitialContext();
			return (BeanManager) initialContext.lookup("java:comp/BeanManager");
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
