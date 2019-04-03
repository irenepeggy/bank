package bank;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Account extends BaseEntity{

	private Department department;
	private String accountNum;
	private Client client;
	private AccountType accountType;
	private String status;
	private Double debit;
	private Double credit;
	private Double deposit;
	private Double balance;
	private Integer period;
	private Date openDate;
	private Date closeDate;
	
	private Set<Operation> operations;
	
	public void setDepartment(Department department) {
	    this.department = department;
	}

	public Department getDepartment() {
	    return this.department;
	}
	public void setAccountNum(String accountNum) {
	    this.accountNum = accountNum;
	}

	public String getAccountNum() {
	    return this.accountNum;
	}
	public void setClient(Client client) {
	    this.client = client;
	}

	public Client getClient() {
	    return this.client;
	}
	public void setAccountType(AccountType accountType) {
	    this.accountType = accountType;
	}

	public AccountType getAccountType() {
	    return this.accountType;
	}
	
	public void setStatus(String status) {
	    this.status = status;
	}

	public String getStatus() {
	    return this.status;
	}
	public void setDebit(Double debit) {
	    this.debit = debit;
	}

	public Double getDebit() {
		return this.debit == null? 0 : this.debit;
	}
	public void setCredit(Double credit) {
	    this.credit = credit;
	}

	public Double getCredit() {
	    return this.credit == null? 0 : this.credit;
	}
	public void setDeposit(Double deposit) {
	    this.deposit = deposit;
	}

	public Double getDeposit() {
	    return this.deposit == null? 0: this.deposit;
	}
	public void setBalance(Double balance) {
	    this.balance = balance;
	}

	public Double getBalance() {
	    return this.balance == null? 0 : this.balance;
	}
	public void setPeriod(Integer period) {
	    this.period = period;
	}

	public Integer getPeriod() {
	    return this.period == null? 0 : this.period;
	}
	public void setOpenDate(Date openDate) {
	    this.openDate = openDate;
	}

	public Date getOpenDate() {
	    return this.openDate;
	}
	public void setCloseDate(Date closeDate) {
	    this.closeDate = closeDate;
	}

	public Date getCloseDate() {
	    return this.closeDate;
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
