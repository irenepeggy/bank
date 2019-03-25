package bank;

public class PersonClient extends BaseEntity {
	
	private String name;
	private String surname;
	private String telephone;
	private String email;
	private String passport;
	private Client client;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getTelephone() {
		return this.telephone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setPassport(String passport) {
		this.passport = passport;
	}
	
	public String getPassport() {
		return this.passport;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Client getClient() {
		return this.client;
	}
}
