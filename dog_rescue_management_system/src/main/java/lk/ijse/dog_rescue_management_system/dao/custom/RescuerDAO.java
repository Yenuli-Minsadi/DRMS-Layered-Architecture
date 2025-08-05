package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.dao.CrudDAO;
import lk.ijse.dog_rescue_management_system.entity.Rescuer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RescuerDAO extends CrudDAO<Rescuer> {

    public boolean save(Rescuer rescuerDto) throws Exception;
    public boolean delete(String rescuerId) throws Exception;
    public boolean update(Rescuer rescuerDto) throws SQLException, ClassNotFoundException;
    public String getNextId() throws Exception;
    public ArrayList<Rescuer> getAll() throws SQLException, ClassNotFoundException;
}
