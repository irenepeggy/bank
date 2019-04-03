package ngtests;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bank.Account;
import bank.Operation;
import dao.AccountDAO;
import implement.Factory;

public class AccountTest {
	
//	public OperationDAO opDAO;
	public AccountDAO accDAO;
//	public DepartmentDAO depDAO;
	
	@BeforeTest
	public void beforeTest() {
		accDAO = Factory.getInstance().getAccountDAO();
//		opDAO = Factory.getInstance().getOperationDAO();
//		depDAO = Factory.getInstance().getDepartmentDAO();
	}

	@Test
	public void testByOperationsProperties() throws ParseException, SQLException {
		String pattern = "yyyy-mm-dd HH:mm:ss.S";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);


		Collection<Account> accs = accDAO.getAccountsByOperationProperties(sdf.parse("2019-03-03 00:51:08.0"), 
				sdf.parse("2019-04-03 01:51:08.0"), 
				100000.0, 
				0.0, 
				111.0, 
				0.0);
		for (Account a: accs) {
			for (Operation o: a.getOperations()) {
				assert(o.getTime().compareTo(sdf.parse("2019-03-03 00:51:08.0")) >= 0 &&
						o.getTime().compareTo(sdf.parse("2019-04-03 00:51:08.0")) <= 0);
				if (o.getSum() > 0) {
					assert(o.getSum() <= 100000.0 && o.getSum() >= 0);
				} else {
					assert(-o.getSum() <= 111.0 && -o.getSum() >= 0);

				}
				
			}
		}
	}

}
