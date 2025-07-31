package lk.ijse.dog_rescue_management_system.bo.custom;

import lk.ijse.dog_rescue_management_system.dto.AdopterDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdopterBO extends SuperBO {

    public ArrayList<AdopterDto> getAllAdopters() throws SQLException, ClassNotFoundException;

    public String getNextAdopterId() throws Exception;

    public boolean saveAdopter(AdopterDto adopterDto) throws Exception;

    public boolean updateAdopter(AdopterDto adopterDto) throws Exception;

    public boolean deleteAdopter(String adopterId) throws Exception;
}
