package ngtests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bank.Client;
import bank.PersonClient;
import dao.AccountDAO;
import dao.AccountTypeDAO;
import dao.ClientDAO;
import dao.PersonClientDAO;
import implement.Factory;

public class PersonClientTest {

	public PersonClientDAO pcDAO;
	public ClientDAO cliDAO;
	public AccountTypeDAO accTypeDAO;
	public AccountDAO accDAO;
	
	
	@BeforeTest
	public void beforeTest() {
		pcDAO = Factory.getInstance().getPersonClientDAO();
		cliDAO = Factory.getInstance().getClientDAO();
		accTypeDAO = Factory.getInstance().getAccountTypeDAO();
		accDAO = Factory.getInstance().getAccountDAO();
	}
	
	/*
	 * should be runned only once or @name and @surname properties should be changed 
	 * to another
	 */

	@Test
	public void testEdit() throws SQLException{
		Client newCli = new Client();
		newCli.setType("person");
		cliDAO.addClient(newCli);
		
		PersonClient newPersCli = new PersonClient();
		newPersCli.setClient(newCli);
		newPersCli.setName("Igor");
		newPersCli.setSurname("Mashechkin");

		pcDAO.addPersonClient(newPersCli);
		
		newPersCli.setTelephone("+7(998)877-66-55");
		pcDAO.editPersonClient(newPersCli);
		
		Assert.assertEquals(pcDAO.getPersonClientByClient(newCli).getTelephone(), "+7(998)877-66-55");
		
	}
	

}
