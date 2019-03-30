package bank;

public class ContactPerson extends BaseEntity{
	private String name;
	private String number;
	private String email;
	private EntityClient entityClient;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setNumber(String telephone) {
		this.number = telephone;
	}
	
	public String getNumber() {
		return this.number;
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
