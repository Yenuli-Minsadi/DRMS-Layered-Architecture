package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.dao.CrudDAO;
import lk.ijse.dog_rescue_management_system.entity.MedicalRecord;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicalRecordDAO extends CrudDAO<MedicalRecord> {

    public boolean save(MedicalRecord medicalRecordDto) throws Exception;
    public boolean delete(String medicalRecordId) throws Exception;
    public boolean update(MedicalRecord medicalRecordDto) throws SQLException, ClassNotFoundException;
    public String getNextId() throws Exception;
    public ArrayList<MedicalRecord> getAll() throws SQLException, ClassNotFoundException;
}
