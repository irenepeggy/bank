package ngtests;

import java.sql.SQLException;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bank.Account;
import bank.Client;
import bank.EntityClient;
import bank.Operation;
import bank.PersonClient;
import dao.AccountDAO;
import dao.AccountTypeDAO;
import dao.ClientDAO;
import dao.DepartmentDAO;
import dao.EntityClientDAO;
import dao.OperationDAO;
import dao.PersonClientDAO;
import implement.Factory;

public class ClientTest {
	
	public ClientDAO cliDAO;
	public PersonClientDAO pcDAO;
	public AccountDAO accDAO;
	public EntityClientDAO ecDAO;
	public AccountTypeDAO accTypeDAO;
	public DepartmentDAO depDAO;
	public OperationDAO opDAO;
	
	@BeforeTest
	public void beforeTest() {
		cliDAO = Factory.getInstance().getClientDAO();
		pcDAO = Factory.getInstance().getPersonClientDAO();
		accDAO = Factory.getInstance().getAccountDAO();
		ecDAO = Factory.getInstance().getEntityClientDAO();
		accTypeDAO = Factory.getInstance().getAccountTypeDAO();
		depDAO = Factory.getInstance().getDepartmentDAO();
		opDAO = Factory.getInstance().getOperationDAO();
	}
	@Test
	public void testById() throws SQLException {
		Client c = cliDAO.getClientById(6);
		Assert.assertEquals((Object)c.getId(), 6);
		if (c.getType().equals("entity")) {
			Set<EntityClient > ec = c.getEntityClient();
			for (EntityClient e: ec) {
				Assert.assertEquals((Object)e.getId(), 1);
			}
		}
	}
	
	@Test
	public void testAdd() throws SQLException {
		
		//adding new client and entity client that responses to it
		Client newCli1 = new Client();
		newCli1.setType("entity");
		cliDAO.addClient(newCli1);
		
		EntityClient newEntCli = new EntityClient();
		newEntCli.setClient(newCli1);
		newEntCli.setName("Test entity client");
		ecDAO.addEntityClient(newEntCli);
		
		Assert.assertEquals(ecDAO.getEntityClientByClient(newCli1).getName(), newEntCli.getName());
		Assert.assertEquals(ecDAO.getEntityClientByClient(newCli1).getId(), newEntCli.getId());
		Assert.assertEquals(ecDAO.getEntityClientByClient(newCli1).getClient().getId(), newEntCli.getClient().getId());
		Assert.assertEquals(ecDAO.getEntityClientByClient(newCli1).getClient().getType(), newEntCli.getClient().getType());
		Set<EntityClient> EntCli = cliDAO.getClientById(newCli1.getId()).getEntityClient();
		
		boolean containsNewEntCliAndNothingElse = false;
		for (EntityClient ec: EntCli) {
			System.out.println(ec.getClient().getId());
			containsNewEntCliAndNothingElse |= ec.getId() == newEntCli.getId();
		}
		containsNewEntCliAndNothingElse &= EntCli.size() == 1;
		Assert.assertTrue(containsNewEntCliAndNothingElse);
		
		
		//adding new client and person client that responses to it
		Client newCli2 = new Client();
		newCli2.setType("person");
		cliDAO.addClient(newCli2);
		
		PersonClient newPersCli = new PersonClient();
		newPersCli.setClient(newCli2);
		newPersCli.setName("Test person client");
		pcDAO.addPersonClient(newPersCli);
		
		Assert.assertEquals(pcDAO.getPersonClientByClient(newCli2).getName(), "Test person client");
		
		Set<PersonClient> PersCli = cliDAO.getClientById(newCli2.getId()).getPersonClient();
		
		boolean containsNewPersCliAndNothingElse = false;
		for (PersonClient pc: PersCli) {
			containsNewPersCliAndNothingElse |= pc.getId() == newPersCli.getId();
		}
		containsNewPersCliAndNothingElse &= EntCli.size() == 1;
		Assert.assertTrue(containsNewPersCliAndNothingElse);
	}
	
	@Test
	public void testDelete() throws SQLException {
		
		
//		//deleting client that responses to entity client
		//first creating it
		Client newCli1 = new Client();
		newCli1.setType("entity");
		cliDAO.addClient(newCli1);
		
		EntityClient newEntCli = new EntityClient();
		newEntCli.setClient(newCli1);
		newEntCli.setName("Test entity client");
		ecDAO.addEntityClient(newEntCli);
		
		Account newAcc1 = new Account();
		newAcc1.setClient(newCli1);
		newAcc1.setAccountType(accTypeDAO.getAccountTypeById(1));
		newAcc1.setDepartment(depDAO.getDepartmentById(1));
		Double sumSettlement1 = 10000000.0;
		accDAO.openAccount(newAcc1, sumSettlement1, null);
		
		Operation op1 = new Operation();
		op1.setAccount(newAcc1);
		op1.setDepartment(newAcc1.getDepartment());
		op1.setSum(1.0);
		opDAO.performAssessment(op1);
		
		newCli1 = cliDAO.getClientById(newCli1.getId());
		//check if new account and entity client were actually bounded with new client
		Assert.assertFalse(newCli1.getAccounts().isEmpty());
		Assert.assertFalse(newCli1.getEntityClient().isEmpty());
		//and eventually deleting
		cliDAO.deleteClient(newCli1);
		Assert.assertNull(ecDAO.getEntityClientById(newEntCli.getId()));
		Assert.assertNull(accDAO.getAccountById(newAcc1.getId()));
		
		
	}

}
