package kolasa.wojcik.insurance.model;

public enum ValidyPeriod {
	YEAR("Rok"), TWO_YEARS("Dwa lata");

	private String description;

	private ValidyPeriod(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
