package bank;

import java.util.HashSet;
import java.util.Set;

public class AccountType extends BaseEntity{
	
	private String name;
	private Double maxCredit;
	private Double interestOnLoan;
	private Integer minPeriod;
	private Integer maxPeriod;
	private Double interestOnDeposit;
	private Double minDepositSum;
	private Double maxAssessment;
	private Double maxCancellation;
	private Set<Account> accounts;
	

	public void setName(String name) {
	    this.name = name;
	}

	public String getName() {
	    return this.name;
	}
	public void setMaxCredit(Double maxCredit) {
	    this.maxCredit = maxCredit;
	}

	public Double getMaxCredit() {
	    return this.maxCredit == null? 0: this.maxCredit;
	}
	public void setInterestOnLoan(Double interestOnLoan) {
	    this.interestOnLoan = interestOnLoan;
	}

	public Double getInterestOnLoan() {
	    return this.interestOnLoan == null? 0 : this.interestOnLoan;
	}
	public void setMinPeriod(Integer minPeriod) {
	    this.minPeriod = minPeriod;
	}

	public Integer getMinPeriod() {
	    return this.minPeriod == null? 0 : this.minPeriod;
	}
	public void setMaxPeriod(Integer maxPeriod) {
	    this.maxPeriod = maxPeriod;
	}

	public Integer getMaxPeriod() {
	    return this.maxPeriod == null? 0 : this.maxPeriod;
	}
	public void setInterestOnDeposit(Double interestOnDeposit) {
	    this.interestOnDeposit = interestOnDeposit;
	}

	public Double getInterestOnDeposit() {
	    return this.interestOnDeposit == null? 0 : this.interestOnDeposit;
	}
	public void setMinDepositSum(Double minDepositSum) {
	    this.minDepositSum = minDepositSum;
	}

	public Double getMinDepositSum() {
	    return this.minDepositSum == null? 0 : this.minDepositSum;
	}
	public void setMaxAssessment(Double maxAssessment) {
	    this.maxAssessment = maxAssessment;
	}

	public Double getMaxAssessment() {
	    return this.maxAssessment == null? 0 : this.maxAssessment;
	}
	public void setMaxCancellation(Double maxCancellation) {
	    this.maxCancellation = maxCancellation;
	}

	public Double getMaxCancellation() {
	    return this.maxCancellation == null? 0 : this.maxCancellation;
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
}
