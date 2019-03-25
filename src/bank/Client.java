package bank;

import java.util.HashSet;
import java.util.Set;

public class Client extends BaseEntity {
	
	private String type;
	private Set<Account> accounts;
	
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


	public void addAccount(Account Account) {
		getAccounts().add(Account);
	}
	
}
