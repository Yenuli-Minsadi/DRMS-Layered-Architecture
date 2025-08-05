package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.dao.CrudDAO;
import lk.ijse.dog_rescue_management_system.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminDAO extends CrudDAO<Admin> {

    public ArrayList<Admin> getAll() throws SQLException, ClassNotFoundException;

    public boolean save(Admin adminDto) throws SQLException, ClassNotFoundException;

    public boolean update(Admin adminDto) throws SQLException, ClassNotFoundException;

    public boolean delete(String adminId) throws SQLException, ClassNotFoundException;

    public String getNextId() throws Exception;

}
