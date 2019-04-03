package dao;

import java.sql.SQLException;

import bank.AccountType;

public interface AccountTypeDAO {
	
	public AccountType getAccountTypeById(Integer id) throws SQLException;
	
}
