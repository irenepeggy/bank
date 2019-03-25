package bank;

import java.util.HashSet;
import java.util.Set;

public class Department extends BaseEntity{
	private Integer name;
	private String address;
	private String contacts;
	private Set<Account> accounts;
	private Set<Schedule> schedules;

	
	public void setName(Integer name) {
	    this.name = name;
	}

	public Integer getName() {
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


	public void addAccount(Account account) {
		getAccounts().add(account);
	}
	
	
	public Set<Schedule> getSchedule() {
		if (this.schedules == null) {
			this.schedules = new HashSet<Schedule>();
		}
		return this.schedules;
	}

	
	public Set<Schedule> getSchedules() {
		return this.schedules;
	}

}
