package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.dto.DonorDto;
import lk.ijse.dog_rescue_management_system.entity.Donor;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DonorDAO extends CrudDAO<Donor> {
    public  boolean save(Donor donorDto) throws Exception;
    public  boolean update(Donor donorDto) throws Exception;
    public boolean delete(String donorId) throws Exception;
    public String getNextId() throws Exception;
    public ArrayList<Donor> getAll() throws SQLException, ClassNotFoundException;

}
