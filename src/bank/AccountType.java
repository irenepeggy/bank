package bank;

public class AccountType {
	
	private String name;
	private Integer maxCredit;
	private Double interestOnLoan;
	private Integer minPeriod;
	private Integer maxPeriod;
	private Double interestOnDeposit;
	private Integer minDepositSum;
	private Integer maxAssessment;
	private Integer maxCancellation;
	

	public void setName(String name) {
	    this.name = name;
	}

	public String getName() {
	    return this.name;
	}
	public void setMaxCredit(Integer maxCredit) {
	    this.maxCredit = maxCredit;
	}

	public Integer getMaxCredit() {
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
	public void setMinDepositSum(Integer minDepositSum) {
	    this.minDepositSum = minDepositSum;
	}

	public Integer getMinDepositSum() {
	    return this.minDepositSum == null? 0 : this.minDepositSum;
	}
	public void setMaxAssessment(Integer maxAssessment) {
	    this.maxAssessment = maxAssessment;
	}

	public Integer getMaxAssessment() {
	    return this.maxAssessment == null? 0 : this.maxAssessment;
	}
	public void setMaxCancellation(Integer maxCancellation) {
	    this.maxCancellation = maxCancellation;
	}

	public Integer getMaxCancellation() {
	    return this.maxCancellation == null? 0 : this.maxCancellation;
	}

}
