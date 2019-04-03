package ngtests;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bank.Account;
import bank.AccountType;
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
		PersonClient pc = pcDAO.getPersonClientById(1);
		assert(!pc.getName().equals("Victor") && !pc.getSurname().equals("Kulyamin"));
		pc.setName("Victor");
		pc.setSurname("Kulyamin");
		
		pcDAO.editPersonClient(pc);
		PersonClient editedPc = pcDAO.getPersonClientById(1);
		assert(editedPc.getName().equals("Victor") && editedPc.getSurname().equals("Kulyamin"));
		
	}
	
	@Test
	public void testFilter() throws SQLException, ParseException {
		String pattern = "yyyy-mm-dd HH:mm:ss.S";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		Date openDate1 = sdf.parse("2000-04-03 01:51:08.0");
		Date openDate2 = sdf.parse("2030-04-03 01:51:08.0");
		Date closeDate = null;
		Set<AccountType> accountTypes = new HashSet<AccountType>();
		accountTypes.add(accTypeDAO.getAccountTypeById(1));
		accountTypes.add(accTypeDAO.getAccountTypeById(2));
		
		Collection<PersonClient> ps = pcDAO.getPersonsByFilter(openDate1, closeDate, accountTypes);
		assert(!ps.isEmpty());
		for (PersonClient p: ps) {
			for (Account a: p.getClient().getAccounts()) {
				assert(a.getOpenDate().compareTo(openDate1) >= 0);
				System.out.println(a.getAccountType().getId());
				boolean contains = false;
				for (AccountType t: accountTypes) {
					contains |= t.getId() == a.getAccountType().getId();
				}
				assert(contains);
			}
		}
		assert(pcDAO.getPersonsByFilter(openDate2, closeDate, accountTypes).isEmpty());
		
	}

}
