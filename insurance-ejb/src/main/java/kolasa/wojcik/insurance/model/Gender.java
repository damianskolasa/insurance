package kolasa.wojcik.insurance.model;

public enum Gender {
	MALE("Mężczyzna"), FEMALE("Kobieta");
	
	private String description;
	
	private Gender(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
