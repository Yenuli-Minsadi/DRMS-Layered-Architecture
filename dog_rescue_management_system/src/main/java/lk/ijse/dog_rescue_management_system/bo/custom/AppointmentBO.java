package lk.ijse.dog_rescue_management_system.bo.custom;

import lk.ijse.dog_rescue_management_system.dto.AppointmentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentBO extends SuperBO {

    public boolean saveAppointment(AppointmentDto appointmentDto) throws Exception;

    public boolean deleteAppointment(String appointmentId) throws Exception;

    public boolean updateAppointment(AppointmentDto appointmentDto) throws Exception;

    public ArrayList<AppointmentDto> getAllAppointments() throws SQLException, ClassNotFoundException;

    public String getNextAppointmentId() throws Exception;

    public int getScheduledAppointmentCount() throws SQLException, ClassNotFoundException;
}
