package lk.ijse.dog_rescue_management_system.bo.custom;

import lk.ijse.dog_rescue_management_system.dto.DogDto;
import net.sf.jasperreports.components.subreport.fill.SubreportElementAdapter;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DogBO extends SuperBO {

    public boolean saveDog(DogDto dogDto) throws Exception;

    public boolean deleteDog(String requestId) throws Exception;

    public boolean updateDog(DogDto dogDto) throws Exception;

    public ArrayList<DogDto> getAllDogs() throws SQLException, ClassNotFoundException;

    public String getNextDogId() throws Exception;

    public int getDogCount() throws SQLException, ClassNotFoundException;
}
