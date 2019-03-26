package dao;

import java.sql.SQLException;
import java.util.Collection;

import bank.ContactPerson;
import bank.EntityClient;

public interface ContactPersonDAO {
	
	public void editContactPerson(ContactPerson contactPerson) throws SQLException;
	
	public void addContactPerson(ContactPerson  contactPerson) throws SQLException;
	
	public void deleteContactPerson(ContactPerson contactPerson) throws SQLException;
	
	public Collection<ContactPerson> getContactPersonsByEntityClient(EntityClient entityClient) throws SQLException;
	
	
}
