package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.MedicalRecordDto;
import lk.ijse.dog_rescue_management_system.entity.MedicalRecord;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface MedicalRecordDAO extends CrudDAO<MedicalRecord> {

    public boolean save(MedicalRecord medicalRecordDto) throws Exception;
    public boolean delete(String medicalRecordId) throws Exception;
    public boolean update(MedicalRecord medicalRecordDto) throws SQLException, ClassNotFoundException;
    public String getNextId() throws Exception;
    public ArrayList<MedicalRecord> getAll() throws SQLException, ClassNotFoundException;
}
