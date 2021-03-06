package bank;

import java.util.HashSet;
import java.util.Set;

public class Department extends BaseEntity{
	private String name;
	private String address;
	private String contacts;
	private Set<Account> accounts;
	private Schedule schedule;
	private Set<Operation> operations;

	
	public void setName(String name) {
	    this.name = name;
	}

	public String getName() {
	    return this.name;
	}
	public void setAddress(String address) {
	    this.address = address;
	}

	public String getAddress() {
	    return this.address;
	}
	public void setContacts(String contacts) {
	    this.contacts = contacts;
	}

	public String getContacts() {
	    return this.contacts;
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
	
	
	public Schedule getSchedule() {
		return this.schedule;
	}

	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public Set<Operation> getOperations() {
		if (this.operations == null) {
			this.operations = new HashSet<Operation>();
		}
		return this.operations;
	}


	public void setOperations(Set<Operation> operations) {
		if (this.operations == null) {
			this.operations = new HashSet<Operation>();
		}
		this.operations.addAll(operations);
	}

}
