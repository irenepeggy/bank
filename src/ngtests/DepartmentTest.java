package ngtests;

import java.sql.SQLException;

import org.hibernate.ObjectNotFoundException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bank.Department;
import bank.Schedule;
import dao.DepartmentDAO;
import dao.ScheduleDAO;
import implement.Factory;

public class DepartmentTest {

	public DepartmentDAO depDAO;
	public ScheduleDAO schDAO;
	
	@BeforeTest
	public void beforeTest() {
		depDAO = Factory.getInstance().getDepartmentDAO();
		schDAO = Factory.getInstance().getScheduleDAO();
	}

	@Test
	public void testById() throws SQLException {
		Department dep = depDAO.getDepartmentById(1);
		assert (dep.getId() == 1);
	}
	
//	
//	@Test
//	public void testGetAll() throws SQLException {
//		Collection<Department> deps = depDAO.getAllDepartments();
//		
//	}

	@Test
	public void testCreate() throws SQLException {

		Department newDep = new Department();
		
		Schedule sched = new Schedule();
		schDAO.createSchedule(sched);
		
		newDep.setName("Main Rostov Office");
		newDep.setSchedule(sched);
		
		depDAO.addDepartment(newDep);
		Department added = depDAO.getDepartmentById(newDep.getId());

		assert (added.getName().equals("Main Rostov Office"));

		depDAO.deleteDepartment(newDep);
	}

	@Test
	public void testEdit() throws SQLException {

		Department dep = new Department();
		Schedule sched = new Schedule();
		schDAO.createSchedule(sched);
		dep.setSchedule(sched);
		depDAO.addDepartment(dep);

		dep.setName("Main Moscow Office");

		depDAO.editDepartment(dep);

		assert (depDAO.getDepartmentById(dep.getId()).getName().equals("Main Moscow Office"));

		depDAO.deleteDepartment(dep);
	}

	@Test(expectedExceptions = { ObjectNotFoundException.class })
	public void testDelete() throws SQLException {

		Department dep = new Department();
		Schedule sched = new Schedule();
		schDAO.createSchedule(sched);
		dep.setSchedule(sched);
		
		depDAO.addDepartment(dep);
		Integer id = dep.getId();
		depDAO.deleteDepartment(dep);
		depDAO.getDepartmentById(id);

	}

}
