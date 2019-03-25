package bank;

import java.util.HashSet;
import java.util.Set;

public class EntityClient extends BaseEntity{

	private String name;
	private String kind;
	private String manager;
	private Client client;
	private Set<ContactPerson> contactPersons;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public String getKind() {
		return this.kind;
	}
	
	public void setMAnager(String manager) {
		this.manager = manager;
	}
	
	public String getManager() {
		return this.manager;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public Set<ContactPerson> getContactPersons() {
		if (this.contactPersons == null) {
			this.contactPersons = new HashSet<ContactPerson>();
		}
		return this.contactPersons;
	}


	public void addContactPersons(ContactPerson contactPerson) {
		getContactPersons().add(contactPerson);
	}
}
