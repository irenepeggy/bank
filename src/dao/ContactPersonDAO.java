package dao;

import java.sql.SQLException;

import bank.ContactPerson;

public interface ContactPersonDAO {
	
	public void editContactPerson(ContactPerson contactPerson) throws SQLException;
	
	public void addContactPerson(ContactPerson  contactPerson) throws SQLException;
	
	public void deleteContactPerson(ContactPerson contactPerson) throws SQLException;
	
}
