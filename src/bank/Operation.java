package bank;

import java.sql.Timestamp;

public class Operation extends BaseEntity{
	private Account account;
	private Integer sum;
	private Timestamp time;
	private Department department;
	
	public void setAccount(Account account) {
	    this.account = account;
	}

	public Account getAccount() {
	    return this.account;
	}
	public void setSum(Integer sum) {
	    this.sum = sum;
	}

	public Integer getSum() {
	    return this.sum;
	}
	public void setTime(Timestamp time) {
	    this.time = time;
	}

	public Timestamp getTime() {
	    return this.time;
	}
	public void setDepartment(Department department) {
	    this.department = department;
	}

	public Department getDepartment() {
	    return this.department;
	}

}
