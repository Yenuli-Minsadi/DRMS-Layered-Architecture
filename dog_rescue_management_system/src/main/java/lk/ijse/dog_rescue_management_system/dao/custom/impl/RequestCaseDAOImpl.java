package lk.ijse.dog_rescue_management_system.dao.custom.impl;

import lk.ijse.dog_rescue_management_system.dao.custom.RequestCaseDAO;
import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.entity.RequestCase;
import lk.ijse.dog_rescue_management_system.dao.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RequestCaseDAOImpl implements RequestCaseDAO {

    @Override
    public ArrayList<RequestCase> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM rescue_request");
        ResultSet resultSet = pst.executeQuery();

        ArrayList<RequestCase> entity = new ArrayList<>();
        while (resultSet.next()) {
            try {
                // Get column values by name instead of index to avoid column order issues
                String requestId = resultSet.getString("request_id");
                String rescuerId = resultSet.getString("rescuer_id");
                String location = resultSet.getString("location");
                String reason = resultSet.getString("reason");
                String caseType = resultSet.getString("case_type");
                String urgencyLevel = resultSet.getString("urgency_level");

                // Safely handle date conversion
                LocalDate requestDate;
                try {
                    // First attempt to get as a proper date
                    java.sql.Date sqlDate = resultSet.getDate("request_date");
                    if (sqlDate != null) {
                        requestDate = sqlDate.toLocalDate();
                    } else {
                        // If date is null, use current date
                        requestDate = LocalDate.now();
                    }
                } catch (SQLException e) {
                    // If there's an error
                    // Get as string and try to parse it
                    String dateStr = resultSet.getString("request_date");
                    try {
                        requestDate = LocalDate.parse(dateStr);
                    } catch (Exception ex) {
                        // If parsing fails, use current date as fallback
                        requestDate = LocalDate.now();
                        System.out.println("Warning: Could not parse date value for request ID " +
                                requestId + ": " + dateStr + ". Using current date instead.");
                    }
                }

                String requestStatus = resultSet.getString("request_status");
                String requestNotes = resultSet.getString("request_notes");
                String requestContact = resultSet.getString("contact_number");

//                RequestCaseDto requestCaseDto = new RequestCaseDto(
//                        requestId,
//                        rescuerId,
//                        location,
//                        reason,
//                        caseType,
//                        urgencyLevel,
//                        requestDate,
//                        requestStatus,
//                        requestNotes,
//                        requestContact
//                );
                entity.add(new RequestCase(
                        requestId,
                        rescuerId,
                        location,
                        reason,
                        caseType,
                        urgencyLevel,
                        requestDate,
                        requestStatus,
                        requestNotes,
                        requestContact
                ));
            } catch (SQLException e) {
                System.out.println("Error processing row: " + e.getMessage());
                e.printStackTrace();
                // Continue to next row instead of failing the entire operation
            }
        }

        return entity;

    }

    @Override
    public boolean save(RequestCase entity) throws Exception {

            return CrudUtil.execute(
                    "insert into rescue_request (request_id, rescuer_id, location, reason, case_type, urgency_level, request_date, request_status, request_notes, contact_number) values (?,?,?,?,?,?,?,?,?,?)",
                    entity.getRequestId(),
                    entity.getRescuerId(),
                    entity.getLocation(),
                    entity.getReason(),
                    entity.getCaseType(),
                    entity.getUrgencyLevel(),
                    entity.getRequestDate(),
                    entity.getRequestStatus(),
                    entity.getRequestNote(),
                    entity.getRequestContact()
            );
    }

    @Override
    public boolean delete(String requestId) throws Exception {
            return CrudUtil.execute(
                    "delete from rescue_request where request_id=?",
                    requestId
            );
    }

    @Override
    public boolean update(RequestCase entity) throws SQLException, ClassNotFoundException {

            return CrudUtil.execute(
                    "update rescue_request set rescuer_id =?, request_date =?, request_status =?, location =?, reason = ?, urgency_level = ?, request_notes = ?, case_type = ?, contact_number = ? where request_id =?",
                    entity.getRescuerId(),
                    entity.getRequestDate(),
                    entity.getRequestStatus(),
                    entity.getLocation(),
                    entity.getReason(),
                    entity.getUrgencyLevel(),
                    entity.getRequestNote(),
                    entity.getCaseType(),
                    entity.getRequestContact(),
                    entity.getRequestId()
            );
    }

    @Override
    public String getNextId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("select request_id from rescue_request order by request_id desc limit 1");
        String tableString = "RQ";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(2);//skip RQ
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNUmber = lastIdNumber + 1;
            String nextIdString = String.format(tableString + "%03d", nextIdNUmber);
            return nextIdString;
        }
        return tableString + "001";
    }
}
