package dao;

import java.sql.SQLException;

import bank.Client;
import bank.PersonClient;

public interface PersonClientDAO {
	
	public void addPersonClient(PersonClient personClient) throws SQLException;
	
	public void editPersonClient(PersonClient personClient) throws SQLException;
	
	public PersonClient getPersonClientByClient(Client client) throws SQLException;
	
	public PersonClient getPersonClientById(Integer id) throws SQLException;
	
}
