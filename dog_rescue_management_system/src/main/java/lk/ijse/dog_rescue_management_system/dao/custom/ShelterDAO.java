package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.dao.CrudDAO;
import lk.ijse.dog_rescue_management_system.entity.Shelter;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ShelterDAO extends CrudDAO<Shelter> {

    public boolean save(Shelter shelterDto) throws Exception;
    public boolean update(Shelter shelterDto) throws Exception;
    public boolean delete(String shelterId) throws Exception;
    public String getNextId() throws Exception;
    public ArrayList<Shelter> getAll() throws SQLException, ClassNotFoundException;
}
