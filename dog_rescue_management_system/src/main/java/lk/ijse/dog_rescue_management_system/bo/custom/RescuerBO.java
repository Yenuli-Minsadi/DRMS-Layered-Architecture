package lk.ijse.dog_rescue_management_system.bo.custom;

import lk.ijse.dog_rescue_management_system.bo.SuperBO;
import lk.ijse.dog_rescue_management_system.dto.RescuerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RescuerBO extends SuperBO {

    public boolean saveRescuer(RescuerDto rescuerDto) throws Exception;
    public boolean deleteRescuer(String rescuerId) throws Exception;
    public boolean updateRescuer(RescuerDto rescuerDto) throws SQLException, ClassNotFoundException;
    public String getNextRescuerId() throws Exception;
    public ArrayList<RescuerDto> getAllRescuers() throws SQLException, ClassNotFoundException;
}
