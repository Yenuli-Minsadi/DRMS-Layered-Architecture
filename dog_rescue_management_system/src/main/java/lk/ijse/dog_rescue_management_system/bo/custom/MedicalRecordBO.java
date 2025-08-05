package lk.ijse.dog_rescue_management_system.bo.custom;

import lk.ijse.dog_rescue_management_system.bo.SuperBO;
import lk.ijse.dog_rescue_management_system.dto.MedicalRecordDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicalRecordBO extends SuperBO {

    public boolean saveMedRec(MedicalRecordDto medicalRecordDto) throws Exception;
    public boolean deleteMedRec(String medicalRecordId) throws Exception;
    public boolean updateMedRec(MedicalRecordDto medicalRecordDto) throws SQLException, ClassNotFoundException;
    public String getNextMedRecId() throws Exception;
    public ArrayList<MedicalRecordDto> getAllMedRecs() throws SQLException, ClassNotFoundException;
}
