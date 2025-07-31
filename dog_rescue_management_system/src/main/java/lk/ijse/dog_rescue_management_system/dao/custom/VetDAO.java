package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.dto.VetDto;
import lk.ijse.dog_rescue_management_system.entity.Vet;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface VetDAO extends CrudDAO<Vet>{

    public boolean save(Vet vetDto) throws Exception;
    public boolean delete(String vetId) throws Exception;
    public boolean update(Vet vetDto) throws SQLException, ClassNotFoundException;
    public String getNextId() throws Exception;
    public ArrayList<Vet> getAll() throws SQLException, ClassNotFoundException;
}
