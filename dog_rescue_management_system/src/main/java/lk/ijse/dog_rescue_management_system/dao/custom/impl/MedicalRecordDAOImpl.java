package lk.ijse.dog_rescue_management_system.dao.custom.impl;

import lk.ijse.dog_rescue_management_system.dao.custom.MedicalRecordDAO;
import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.entity.MedicalRecord;
import lk.ijse.dog_rescue_management_system.dao.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MedicalRecordDAOImpl implements MedicalRecordDAO {
    @Override
    public boolean save(MedicalRecord entity) throws Exception {

            return CrudUtil.execute(
                    "insert into medical_record (medical_rec_id, dog_id, vet_id , medical_rec_date , diagnosis, treatments , medications , vet_notes, has_lab_report, lab_report_reference) values (?,?,?,?,?,?,?,?,?,?)",
                    entity.getMedicalRecordId(),
                    entity.getDogId(),
                    entity.getVetId(),
                    entity.getMedicalRecordDate(),
                    entity.getDiagnosis(),
                    entity.getTreatment(),
                    entity.getMedication(),
                    entity.getVetNote(),
                    entity.getHasLabReport(),
                    entity.getLabReportReference()
            );
        }

    @Override
    public boolean delete(String medicalRecordId) throws Exception {

            return CrudUtil.execute(
                    "delete from medical_record where medical_rec_id=?",
                    medicalRecordId
            );
    }

    @Override
    public boolean update(MedicalRecord entity) throws SQLException, ClassNotFoundException {

            return CrudUtil.execute(
                    "update medical_record set dog_id=?, vet_id=?, medical_rec_date=? , diagnosis=?, treatments =?, medications=?, vet_notes=?, has_lab_report=?, lab_report_reference=? where medical_rec_id =?",
                    entity.getDogId(),
                    entity.getVetId(),
                    entity.getMedicalRecordDate(),
                    entity.getDiagnosis(),
                    entity.getTreatment(),
                    entity.getMedication(),
                    entity.getVetNote(),
                    entity.getHasLabReport(),
                    entity.getLabReportReference(),
                    entity.getMedicalRecordId()
            );
    }

    @Override
    public String getNextId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("select medical_rec_id from medical_record order by medical_rec_id desc limit 1");
        String tableString = "MR";
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(2); // skip "MR"
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNUmber = lastIdNumber + 1;
            String nextIdString = String.format(tableString + "%03d", nextIdNUmber);

            return nextIdString;
        }
        // I
        return tableString + "MR%03d";
    }

    @Override
    public ArrayList<MedicalRecord> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM medical_record");
        ResultSet resultSet = pst.executeQuery();

        ArrayList<MedicalRecord> entity = new ArrayList<>();
        while (resultSet.next()) {
            try {
                String medicalRecordId = resultSet.getString("medical_rec_id");
                String dogId = resultSet.getString("dog_id");
                String vetId = resultSet.getString("vet_id");

                LocalDate medicalRecordDate;
                try {
                    // First attempt to get as a proper date
                    java.sql.Date sqlDate = resultSet.getDate("medical_rec_date");
                    if (sqlDate != null) {
                        medicalRecordDate = sqlDate.toLocalDate();
                    } else {
                        // If date is null, use current date as fallback
                        medicalRecordDate = LocalDate.now();
                    }
                } catch (SQLException e) {
                    // If there's an error
                    // Get as string and try to parse it
                    String dateStr = resultSet.getString("request_date");
                    try {
                        medicalRecordDate = LocalDate.parse(dateStr);
                    } catch (Exception ex) {
                        // If parsing fails, use current date
                        medicalRecordDate = LocalDate.now();
                        System.out.println("Warning: Could not parse date value for request ID " +
                                medicalRecordDate + ": " + dateStr + ". Using current date instead.");
                    }
                }

                String diagnosis = resultSet.getString("diagnosis");
                String treatment = resultSet.getString("treatments");
                String medication = resultSet.getString("medications");
                String vetNote = resultSet.getString("vet_notes");
                String hasLabReport = resultSet.getString("has_lab_report");
                String labReportReference = resultSet.getString("lab_report_reference");

//                MedicalRecordDto medicalRecordDto = new MedicalRecordDto(
//                        medicalRecordId,
//                        dogId,
//                        vetId,
//                        medicalRecordDate,
//                        diagnosis,
//                        treatment,
//                        medication,
//                        vetNote,
//                        hasLabReport,
//                        labReportReference
//                );
                entity.add(new MedicalRecord(
                        medicalRecordId,
                        dogId,
                        vetId,
                        medicalRecordDate,
                        diagnosis,
                        treatment,
                        medication,
                        vetNote,
                        hasLabReport,
                        labReportReference));
            } catch (SQLException e) {
                System.out.println("Error processing row: " + e.getMessage());
                e.printStackTrace();
                // Continue to next row instead of failing the entire operation
            }
        }

        return entity;
    }
}

