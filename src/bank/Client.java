package bank;

import java.util.HashSet;
import java.util.Set;

public class Client extends BaseEntity {
	
	private String type;
	private Set<Account> accounts;
	private Set<PersonClient> personClient;
	private Set<EntityClient> entityClient;
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	

	public Set<Account> getAccounts() {
		if (this.accounts == null) {
			this.accounts = new HashSet<Account>();
		}
		return this.accounts;
	}


	public void setAccounts(Set<Account> accounts) {
		if (this.accounts == null) {
			this.accounts = new HashSet<Account>();
		}
		this.accounts.addAll(accounts);
	}
	

	public Set<PersonClient> getPersonClient() {
		if (this.personClient == null) {
			this.personClient = new HashSet<PersonClient>();
		}
		return this.personClient;
	}


	public void setPersonClient(Set<PersonClient> personClient) {
		if (this.personClient == null) {
			this.personClient = new HashSet<PersonClient>();
		}
		this.personClient.addAll(personClient);
	}
	
	public Set<EntityClient> getEntityClient() {
		if (this.entityClient == null) {
			this.entityClient = new HashSet<EntityClient>();
		}
		return this.entityClient;
	}


	public void setEntityClient(Set<EntityClient> entityClient) {
		if (this.entityClient == null) {
			this.entityClient = new HashSet<EntityClient>();
		}
		this.entityClient.addAll(entityClient);
	}
}
