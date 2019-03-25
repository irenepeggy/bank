package bank;

public class ContactPerson {
	private String name;
	private String telephone;
	private String email;
	private EntityClient entityClient;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
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
	
	public void setEntityClient(EntityClient entityClient) {
		this.entityClient = entityClient;
	}
	
	public EntityClient getEntityClient() {
		return this.entityClient;
	}
	
}
