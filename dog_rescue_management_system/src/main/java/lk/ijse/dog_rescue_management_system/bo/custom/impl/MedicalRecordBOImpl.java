package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.MedicalRecordBO;
import lk.ijse.dog_rescue_management_system.dao.custom.AdminDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.DAOFactory;
import lk.ijse.dog_rescue_management_system.dao.custom.MedicalRecordDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.impl.MedicalRecordDAOImpl;
import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.AdopterDto;
import lk.ijse.dog_rescue_management_system.dto.MedicalRecordDto;
import lk.ijse.dog_rescue_management_system.entity.Adopter;
import lk.ijse.dog_rescue_management_system.entity.MedicalRecord;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MedicalRecordBOImpl implements MedicalRecordBO {
    MedicalRecordDAO medicalRecordDAO = (MedicalRecordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEDICALRECORD);
    @Override
    public boolean saveMedRec(MedicalRecordDto medicalRecordDto) throws Exception {

            return medicalRecordDAO.save(new MedicalRecord(
                    medicalRecordDto.getMedicalRecordId(),medicalRecordDto.getDogId(), medicalRecordDto.getVetId(),
                    medicalRecordDto.getMedicalRecordDate(),medicalRecordDto.getDiagnosis(),medicalRecordDto.getTreatment(),
                    medicalRecordDto.getMedication(),medicalRecordDto.getVetNote(),medicalRecordDto.getHasLabReport(),medicalRecordDto.getLabReportReference()));
        }

    @Override
    public boolean deleteMedRec(String medicalRecordId) throws Exception {

            return medicalRecordDAO.delete(medicalRecordId);
    }

    @Override
    public boolean updateMedRec(MedicalRecordDto medicalRecordDto) throws SQLException, ClassNotFoundException {

            return medicalRecordDAO.update(new MedicalRecord(
                    medicalRecordDto.getMedicalRecordId(),medicalRecordDto.getDogId(), medicalRecordDto.getVetId(),
                    medicalRecordDto.getMedicalRecordDate(),medicalRecordDto.getDiagnosis(),medicalRecordDto.getTreatment(),
                    medicalRecordDto.getMedication(),medicalRecordDto.getVetNote(),medicalRecordDto.getHasLabReport(),medicalRecordDto.getLabReportReference()));
    }

    @Override
    public String getNextMedRecId() throws Exception {
        return medicalRecordDAO.getNextId();
    }

    @Override
    public ArrayList<MedicalRecordDto> getAllMedRecs() throws SQLException, ClassNotFoundException {
//        return medicalRecordDAO.getAll();
        ArrayList<MedicalRecord>entity=medicalRecordDAO.getAll();
        ArrayList<MedicalRecordDto>medicalRecordDto=new ArrayList<>();
        for( MedicalRecord medicalRecord :entity){
            medicalRecordDto.add(new MedicalRecordDto(
                    medicalRecord.getMedicalRecordId(),medicalRecord.getDogId(), medicalRecord.getVetId(),
                    medicalRecord.getMedicalRecordDate(),medicalRecord.getDiagnosis(),medicalRecord.getTreatment(),
                    medicalRecord.getMedication(),medicalRecord.getVetNote(),medicalRecord.getHasLabReport(),medicalRecord.getLabReportReference()));
        }
        return medicalRecordDto;
    }
}

