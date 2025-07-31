package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.RequestCaseDto;
import lk.ijse.dog_rescue_management_system.entity.RequestCase;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RequestCaseDAO extends CrudDAO<RequestCase> {

    public ArrayList<RequestCase> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(RequestCase requestCaseDto) throws Exception;
    public boolean delete(String requestId) throws Exception;
    public boolean update(RequestCase requestCaseDto) throws SQLException, ClassNotFoundException;
    public String getNextId() throws Exception;
}
