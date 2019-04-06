package dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import bank.Account;
import bank.AccountType;

public interface AccountDAO {

	public void openAccount(Account account, Double sum, Integer period) throws SQLException;
	
	public void closeAccount(Account account) throws SQLException;

	public Account getAccountById(Integer id) throws SQLException;
	
	public Collection<Account> getAccountsByFilter(String status,
			Date earliestOperationDate, 
			Date latestOperationDate, 
			Double maxCancellation, 
			Double minCancellation, 
			Double maxAssessment, 
			Double minAssessment,
			Date earliestOpenDate,
			Date latestOpenDate,
			Date earliestCloseDate,
			Date latestCloseDate,
			AccountType accountType) throws SQLException;
}
