package lk.ijse.dog_rescue_management_system.bo.custom;

import lk.ijse.dog_rescue_management_system.dto.ShelterDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ShelterBO extends SuperBO {

    public boolean saveShelter(ShelterDto shelterDto) throws Exception;
    public boolean updateShelter(ShelterDto shelterDto) throws Exception;
    public boolean deleteShelter(String shelterId) throws Exception;
    public String getNextShelterId() throws Exception;
    public ArrayList<ShelterDto> getAllShelters() throws SQLException, ClassNotFoundException;
}
