package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.dao.CrudDAO;
import lk.ijse.dog_rescue_management_system.entity.Adopter;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdopterDAO extends CrudDAO<Adopter> {

    public ArrayList<Adopter> getAll() throws SQLException, ClassNotFoundException;

    public String getNextId() throws Exception;

    public boolean save(Adopter adopterDto) throws Exception;

    public boolean update(Adopter adopterDto) throws Exception;

    public boolean delete(String adopterId) throws Exception;
}
