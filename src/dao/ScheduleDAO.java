package dao;

import java.sql.SQLException;

import bank.Schedule;

public interface ScheduleDAO {
	
	public Schedule editSchedule(Schedule schedule) throws SQLException;
	
	public void createSchedule(Schedule schedule) throws SQLException;

	public Schedule getScheduleById(Integer id) throws SQLException;
	
	public void deleteSchedule(Schedule schedule) throws SQLException;
}
