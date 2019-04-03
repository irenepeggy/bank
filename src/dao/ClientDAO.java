package dao;

import java.sql.SQLException;

import bank.Client;

public interface ClientDAO {
	
	public void addClient(Client client) throws SQLException;
	
	public void deleteClient (Client client) throws SQLException;
	
	public Client getClientById (Integer id) throws SQLException;
	
}
