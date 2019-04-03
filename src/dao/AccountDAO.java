package dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import bank.Account;

public interface AccountDAO {

	public void openAccount(Account account) throws SQLException;
	
	public void editAccount(Account account) throws SQLException;
	
	public Account getAccountById(Integer id) throws SQLException;
	
	public Collection<Account> getAccountsByOperationProperties(Date earliestDate, 
			Date latestDate, 
			Double maxCancellation, 
			Double minCancellation, 
			Double maxAssessment, 
			Double minAssessment) throws SQLException;
}
