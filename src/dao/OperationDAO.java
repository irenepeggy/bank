package dao;

import java.sql.SQLException;

import bank.Account;
import bank.Operation;

public interface OperationDAO {
	
	public Operation getOperationById(Integer id) throws SQLException;
	//sum > 0
	public void performAssessment(Operation operation) throws SQLException;
	//sum < 0
	public void performCancellation(Operation operation) throws SQLException;
	public void applyInterestOnDeposit(Account account) throws SQLException;
	public void applyInterestOnLoan(Account account)  throws SQLException;
	public void payForCredit(Account account, Double sum) throws SQLException;
	
}
