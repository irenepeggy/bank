package ngtests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
		Client c = cliDAO.getClientById(1);
		for (PersonClient pc: c.getPersonClient()) {
			System.out.println(pc.getId());
		}
		if (c == null)
			return;
		if (c.getType().equals("entity")) {
			EntityClient ec = ecDAO.getEntityClientByClient(c);
			Integer ecId = ec.getId();
			System.out.println(ecId);
			cliDAO.deleteClient(c);
			System.out.println(ecId);
			Assert.assertNull(ecDAO.getEntityClientById(ecId));
		} else {
			PersonClient pc = pcDAO.getPersonClientByClient(c);
			Integer pcId = pc.getId();
			System.out.println(pcDAO.getPersonClientByClient(c).getId());

			cliDAO.deleteClient(c);
			System.out.println(pcId);

			Assert.assertNull(pcDAO.getPersonClientById(pcId));
		}
		
	}

}
