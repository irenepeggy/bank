package dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import bank.AccountType;
import bank.Client;
import bank.PersonClient;

public interface PersonClientDAO {
	
	public void addPersonClient(PersonClient personClient) throws SQLException;
	
	public void editPersonClient(PersonClient personClient) throws SQLException;
	
	public PersonClient getPersonClientByClient(Client client) throws SQLException;
	
	public PersonClient getPersonClientById(Integer id) throws SQLException;
	
	public Collection<PersonClient> getPersonsByFilter(Date openDate,
														Date closeDate,
														Set<AccountType> accountTypes) throws SQLException;
	
}
