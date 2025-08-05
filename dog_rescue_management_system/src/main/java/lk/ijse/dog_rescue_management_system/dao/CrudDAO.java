package lk.ijse.dog_rescue_management_system.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {

    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    public String getNextId() throws Exception;

    public boolean save(T dto) throws Exception;

    public boolean update(T dto) throws Exception;

    public boolean delete(String id) throws Exception;
}
