package dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import bank.AccountType;
import bank.Client;
import bank.EntityClient;

public interface EntityClientDAO {
	
	public void addEntityClient(EntityClient entityClient) throws SQLException;
	
	public void editEntityClient(EntityClient entityClient) throws SQLException;
	
	public EntityClient getEntityClientByClient(Client client) throws SQLException;

	public EntityClient getEntityClientById(Integer id) throws SQLException;
	
	public Collection<EntityClient> getEntitiesByFilter(Date openDate,
			Date closeDate,
			Set<AccountType> accountTypes) throws SQLException;

}
