package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.dao.CrudDAO;
import lk.ijse.dog_rescue_management_system.entity.RequestCase;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RequestCaseDAO extends CrudDAO<RequestCase> {

    public ArrayList<RequestCase> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(RequestCase requestCaseDto) throws Exception;
    public boolean delete(String requestId) throws Exception;
    public boolean update(RequestCase requestCaseDto) throws SQLException, ClassNotFoundException;
    public String getNextId() throws Exception;
}
