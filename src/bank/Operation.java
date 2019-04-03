package bank;

import java.util.Date;

public class Operation extends BaseEntity{
	private Account account;
	private Double sum;
	private Date time;
	private Department department;
	
	public void setAccount(Account account) {
	    this.account = account;
	}

	public Account getAccount() {
	    return this.account;
	}
	public void setSum(Double sum) {
	    this.sum = sum;
	}

	public Double getSum() {
	    return this.sum;
	}
	public void setTime(Date time) {
	    this.time = time;
	}

	public Date getTime() {
	    return this.time;
	}
	public void setDepartment(Department department) {
	    this.department = department;
	}

	public Department getDepartment() {
	    return this.department;
	}

}
