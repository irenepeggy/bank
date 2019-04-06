package dao;

import java.sql.SQLException;

import bank.Client;
import bank.EntityClient;

public interface EntityClientDAO {
	
	public void addEntityClient(EntityClient entityClient) throws SQLException;
	
	public void editEntityClient(EntityClient entityClient) throws SQLException;
	
	public EntityClient getEntityClientByClient(Client client) throws SQLException;

	public EntityClient getEntityClientById(Integer id) throws SQLException;

}
