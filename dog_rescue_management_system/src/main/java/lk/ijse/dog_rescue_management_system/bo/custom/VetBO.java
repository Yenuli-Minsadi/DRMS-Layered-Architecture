package lk.ijse.dog_rescue_management_system.bo.custom;

import lk.ijse.dog_rescue_management_system.dto.VetDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VetBO extends SuperBO {

    public boolean saveVet(VetDto vetDto) throws Exception;
    public boolean deleteVet(String vetId) throws Exception;
    public boolean updateVet(VetDto vetDto) throws SQLException, ClassNotFoundException;
    public String getNextVetId() throws Exception;
    public ArrayList<VetDto> getAllVets() throws SQLException, ClassNotFoundException;
}
