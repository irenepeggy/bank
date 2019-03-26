package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import bank.Client;
import bank.Department;

public interface ClientDAO {
	
	public void addClient(Client client) throws SQLException;
	
	public void editClient(Client client) throws SQLException;
	
	public void deleteClient(Client client) throws SQLException, IOException;
	
	public Collection<Client> getClientById(Integer id) throws SQLException;
	
	public Collection<Client> getAllClient() throws SQLException;
	
	public Collection<Client> getClientsByDepartment(Department department) throws SQLException;
}
