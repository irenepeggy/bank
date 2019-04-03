package ngtests;

import java.sql.SQLException;
import java.util.Collection;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bank.Account;
import bank.Client;
import bank.EntityClient;
import bank.PersonClient;
import dao.AccountDAO;
import dao.ClientDAO;
import dao.EntityClientDAO;
import dao.PersonClientDAO;
import implement.Factory;

public class ClientTest {
	
	public ClientDAO cliDAO;
	public PersonClientDAO pcDAO;
	public AccountDAO accDAO;
	public EntityClientDAO ecDAO;
	
	@BeforeTest
	public void beforeTest() {
		cliDAO = Factory.getInstance().getClientDAO();
		pcDAO = Factory.getInstance().getPersonClientDAO();
		accDAO = Factory.getInstance().getAccountDAO();
		ecDAO = Factory.getInstance().getEntityClientDAO();
	}

	@Test
	public void testDelete() throws SQLException {
		Client c = cliDAO.getClientById(4);
		
		Collection<Account> a = c.getAccounts();
		PersonClient p = pcDAO.getPersonClientByClient(c);
		EntityClient e = ecDAO.getEntityClientByClient(c);
		
		cliDAO.deleteClient(c);
		
		assert(cliDAO.getClientById(4) == null);
		assert((p != null && pcDAO.getPersonClientById(p.getId()) == null) 
				|| (e != null && ecDAO.getEntityClientById(e.getId()) == null));
		for (Account acc: a) {
			assert(accDAO.getAccountById(acc.getId()) == null);
		}
		
	}

}
