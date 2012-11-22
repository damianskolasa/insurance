package kolasa.wojcik.insurance.model;

import java.io.Serializable;

public class Price implements Serializable {

	private static final long serialVersionUID = 7102700594161746773L;

	private Double value;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
