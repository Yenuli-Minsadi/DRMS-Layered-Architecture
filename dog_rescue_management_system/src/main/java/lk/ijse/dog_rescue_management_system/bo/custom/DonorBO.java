package lk.ijse.dog_rescue_management_system.bo.custom;

import lk.ijse.dog_rescue_management_system.bo.SuperBO;
import lk.ijse.dog_rescue_management_system.dto.DonorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DonorBO extends SuperBO {
    public  boolean saveDonor(DonorDto donorDto) throws Exception;
    public  boolean updateDonor(DonorDto donorDto) throws Exception;
    public boolean deleteDonor(String donorId) throws Exception;
    public String getNextDonorId() throws Exception;
    public ArrayList<DonorDto> getAllDonors() throws SQLException, ClassNotFoundException;

}
