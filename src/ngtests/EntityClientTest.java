package ngtests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bank.Client;
import bank.EntityClient;
import dao.AccountDAO;
import dao.AccountTypeDAO;
import dao.ClientDAO;
import dao.EntityClientDAO;
import implement.Factory;

public class EntityClientTest {
	public EntityClientDAO ecDAO;
	public ClientDAO cliDAO;
	public AccountTypeDAO accTypeDAO;
	public AccountDAO accDAO;

	@BeforeTest
	public void beforeTest() {
		ecDAO = Factory.getInstance().getEntityClientDAO();
		cliDAO = Factory.getInstance().getClientDAO();
		accTypeDAO = Factory.getInstance().getAccountTypeDAO();
		accDAO = Factory.getInstance().getAccountDAO();
	}
	
	@Test
	public void testGetEntityClientByClient() throws SQLException {
		Client newCli = new Client();
		newCli.setType("entity");
		cliDAO.addClient(newCli);
		
		EntityClient newEntCli = new EntityClient();
		newEntCli.setClient(newCli);
		newEntCli.setName("Tanuki");

		ecDAO.addEntityClient(newEntCli);
		
		EntityClient checkingEntCli = ecDAO.getEntityClientByClient(newCli);
		
		Assert.assertEquals(checkingEntCli.getId(), newEntCli.getId());
		Assert.assertEquals(checkingEntCli.getName(), newEntCli.getName());

	}

	@Test
	public void testEdit() throws SQLException {
		Client newCli = new Client();
		newCli.setType("entity");
		cliDAO.addClient(newCli);
		
		EntityClient newEntCli = new EntityClient();
		newEntCli.setClient(newCli);
		newEntCli.setName("Tanuki");

		ecDAO.addEntityClient(newEntCli);
		
		newEntCli.setKind("Japanise restourant");
		ecDAO.editEntityClient(newEntCli);
		
		Assert.assertEquals(ecDAO.getEntityClientByClient(newCli).getKind(), "Japanise restourant");
	}

}
