package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.DogDto;
import lk.ijse.dog_rescue_management_system.entity.Dog;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DogDAO extends CrudDAO<Dog> {

    public boolean save(Dog dogDto) throws Exception;

    public boolean delete(String requestId) throws Exception;

    public boolean update(Dog dogDto) throws Exception;

    public ArrayList<Dog> getAll() throws SQLException, ClassNotFoundException;

    public String getNextId() throws Exception;

    public int getDogCount() throws SQLException, ClassNotFoundException;
}
