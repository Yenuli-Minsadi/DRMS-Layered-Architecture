package lk.ijse.dog_rescue_management_system.dao.custom.impl;

import lk.ijse.dog_rescue_management_system.dao.custom.AppointmentDAO;
import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.AppointmentDto;
import lk.ijse.dog_rescue_management_system.entity.Appointment;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;

public class AppointmentDAOImpl implements AppointmentDAO {
    @Override
    public boolean save(Appointment entity) throws Exception {

            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "INSERT INTO appointments (appointment_id, dog_id, vet_id, appointment_date, reason, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, entity.getAppointmentId());
            pst.setString(2, entity.getDogId());
            pst.setString(3, entity.getVetId());

            // Ensure appointment date is not null
            if (entity.getAppointmentDate() != null) {
                pst.setDate(4, Date.valueOf(entity.getAppointmentDate()));
            } else {
                throw new IllegalArgumentException("Appointment date cannot be null");
            }

            pst.setString(5, entity.getAppointmentReason());
            pst.setString(6, entity.getAppointmentStatus());

            int i = pst.executeUpdate();
            return i > 0;
    }

    @Override
    public boolean delete(String appointmentId) throws Exception {
        return CrudUtil.execute(
                "delete from appointments where appointment_id=?",
                appointmentId
        );
    }

    @Override
    public boolean update(Appointment entity) throws Exception {

            return CrudUtil.execute(
                    "update appointments set dog_id =?, vet_id=?, appointment_date =?, reason=?, status=? where appointment_id =?",
                    entity.getDogId(),
                    entity.getVetId(),
                    entity.getAppointmentDate(),
                    entity.getAppointmentReason(),
                    entity.getAppointmentStatus(),
                    entity.getAppointmentId()
            );
    }

    @Override
    public ArrayList<Appointment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM appointments");

        ArrayList<Appointment> entity = new ArrayList<>();
        while (resultSet.next()) {
//            AppointmentDto appointmentDto = new AppointmentDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getDate(4).toLocalDate(),
//                    resultSet.getString(5),
//                    resultSet.getString(6)
//            );
            entity.add(new Appointment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getString(5),
                    resultSet.getString(6)));
        }
        return entity;
    }

    @Override
    public String getNextId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("select appointment_id from appointments order by appointment_id desc limit 1");
        String tableString = "APT";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(3); // skip "APT"
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableString + "%03d", nextIdNumber);

            return nextIdString;
        }
        return tableString + "001";
    }

    @Override
    public int getScheduledAppointmentCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute(
                "SELECT COUNT(*) FROM appointments WHERE status = 'Scheduled'"
        );

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return 0;
    }


}
