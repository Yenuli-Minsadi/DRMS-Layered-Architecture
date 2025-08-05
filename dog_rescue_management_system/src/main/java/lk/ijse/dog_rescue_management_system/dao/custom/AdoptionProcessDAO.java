package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.dao.CrudDAO;
import lk.ijse.dog_rescue_management_system.dto.AdoptionProcessDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdoptionProcessDAO extends CrudDAO<AdoptionProcessDto> {

    public ArrayList<AdoptionProcessDto> getAll() throws SQLException, ClassNotFoundException;

    public String getNextId() throws Exception;

    public boolean save(AdoptionProcessDto dto) throws Exception;

    public boolean update(AdoptionProcessDto dto) throws Exception;

    public boolean delete(String adoptionId) throws Exception;
}
