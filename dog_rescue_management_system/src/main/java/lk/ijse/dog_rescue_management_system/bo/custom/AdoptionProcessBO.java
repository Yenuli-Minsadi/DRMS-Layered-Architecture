package lk.ijse.dog_rescue_management_system.bo.custom;

import lk.ijse.dog_rescue_management_system.dto.AdoptionProcessDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdoptionProcessBO extends SuperBO {

    public ArrayList<AdoptionProcessDto> getAll() throws SQLException, ClassNotFoundException;

    public String getNextId() throws Exception;

    public int getAdoptedDogCount() throws SQLException, ClassNotFoundException;

    public boolean save(AdoptionProcessDto dto) throws Exception;

    public boolean update(AdoptionProcessDto dto) throws Exception;

    public boolean delete(String adoptionId) throws Exception;
}
