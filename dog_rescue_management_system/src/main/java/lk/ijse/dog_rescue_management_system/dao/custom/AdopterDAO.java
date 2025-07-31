package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.AdopterDto;
import lk.ijse.dog_rescue_management_system.entity.Adopter;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AdopterDAO extends CrudDAO<Adopter> {

    public ArrayList<Adopter> getAll() throws SQLException, ClassNotFoundException;

    public String getNextId() throws Exception;

    public boolean save(Adopter adopterDto) throws Exception;

    public boolean update(Adopter adopterDto) throws Exception;

    public boolean delete(String adopterId) throws Exception;
}
