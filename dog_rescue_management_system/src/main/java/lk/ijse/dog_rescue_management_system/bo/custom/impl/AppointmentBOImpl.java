package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.AppointmentBO;
import lk.ijse.dog_rescue_management_system.dao.custom.AppointmentDAO;
import lk.ijse.dog_rescue_management_system.dao.DAOFactory;
import lk.ijse.dog_rescue_management_system.dto.AppointmentDto;
import lk.ijse.dog_rescue_management_system.entity.Appointment;

import java.sql.*;
import java.util.ArrayList;

public class AppointmentBOImpl implements AppointmentBO {

    AppointmentDAO appointmentDAO = (AppointmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.APPOINTMENT);
    @Override
    public boolean saveAppointment(AppointmentDto appointmentDto) throws Exception {
        return appointmentDAO.save(new Appointment(
                appointmentDto.getAppointmentId(),appointmentDto.getDogId(), appointmentDto.getVetId(),
                appointmentDto.getAppointmentDate(),appointmentDto.getAppointmentReason(),appointmentDto.getAppointmentStatus()));
    }

    @Override
    public boolean deleteAppointment(String appointmentId) throws Exception {
        return appointmentDAO.delete(appointmentId);
    }

    @Override
    public boolean updateAppointment(AppointmentDto appointmentDto) throws Exception {
        return appointmentDAO.update(new Appointment(
                appointmentDto.getAppointmentId(),appointmentDto.getDogId(), appointmentDto.getVetId(),
                appointmentDto.getAppointmentDate(),appointmentDto.getAppointmentReason(),appointmentDto.getAppointmentStatus()));
    }

    @Override
    public ArrayList<AppointmentDto> getAllAppointments() throws SQLException, ClassNotFoundException {
//        return appointmentDAO.getAll();
        ArrayList<Appointment>entity=appointmentDAO.getAll();
        ArrayList<AppointmentDto>appointmentDto=new ArrayList<>();
        for( Appointment appointment :entity){
            appointmentDto.add(new AppointmentDto(
                    appointment.getAppointmentId(),appointment.getDogId(), appointment.getVetId(),
                    appointment.getAppointmentDate(),appointment.getAppointmentReason(),appointment.getAppointmentStatus()));
        }
        return appointmentDto;
    }

    @Override
    public String getNextAppointmentId() throws Exception {
        return appointmentDAO.getNextId();
    }

}
