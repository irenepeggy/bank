package ngtests;

import java.sql.SQLException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bank.Schedule;
import dao.DepartmentDAO;
import dao.ScheduleDAO;
import implement.Factory;

public class ScheduleTest {

	public ScheduleDAO schDAO;
	public DepartmentDAO depDAO;
	@BeforeTest
	public void beforeTest() {
		schDAO = Factory.getInstance().getScheduleDAO();
		depDAO = Factory.getInstance().getDepartmentDAO();
	}

	@Test
	public void testById() throws SQLException {
		Schedule sched = schDAO.getScheduleById(1);
		assert (sched.getId() == 1);
	}

	@Test
	public void testCreate() throws SQLException {

		Schedule newSched = new Schedule();
		newSched.setFri("10:00 - 20:00");

		schDAO.createSchedule(newSched);
		Schedule added = schDAO.getScheduleById(newSched.getId());

		assert (newSched.getFri().equals(added.getFri()));

		schDAO.deleteSchedule(newSched);
	}

	@Test
	public void testEdit() throws SQLException {

		Schedule sched = new Schedule();

		schDAO.createSchedule(sched);

		sched.setFri("10:00 - 19:00");

		schDAO.editSchedule(sched);

		assert (schDAO.getScheduleById(sched.getId()).getFri().equals("10:00 - 19:00"));

		schDAO.deleteSchedule(sched);
	}

	@Test
	public void testDelete() throws SQLException {

		Schedule sched = new Schedule();
		schDAO.createSchedule(sched);
		Integer id = sched.getId();
		schDAO.deleteSchedule(sched);
		assert(schDAO.getScheduleById(id) == null);

	}

}
