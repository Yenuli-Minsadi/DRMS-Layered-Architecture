package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.dao.CrudDAO;
import lk.ijse.dog_rescue_management_system.entity.Dog;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DogDAO extends CrudDAO<Dog> {

    public boolean save(Dog dogDto) throws Exception;

    public boolean delete(String requestId) throws Exception;

    public boolean update(Dog dogDto) throws Exception;

    public ArrayList<Dog> getAll() throws SQLException, ClassNotFoundException;

    public String getNextId() throws Exception;

}
