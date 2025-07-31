package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.AppointmentDto;
import lk.ijse.dog_rescue_management_system.entity.Appointment;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;

public interface AppointmentDAO extends CrudDAO<Appointment> {

    public boolean save(Appointment appointmentDto) throws Exception;

    public boolean delete(String appointmentId) throws Exception;

    public boolean update(Appointment appointmentDto) throws Exception;

    public ArrayList<Appointment> getAll() throws SQLException, ClassNotFoundException;

    public String getNextId() throws Exception;

    public int getScheduledAppointmentCount() throws SQLException, ClassNotFoundException;
}
