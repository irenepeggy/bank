package dao;

import java.sql.SQLException;


import bank.Schedule;

public interface ScheduleDAO {
	
	 public void addSchedule(Schedule schedule) throws SQLException;
	 
	 public Schedule getScheduleById(Integer id) throws SQLException;
}
