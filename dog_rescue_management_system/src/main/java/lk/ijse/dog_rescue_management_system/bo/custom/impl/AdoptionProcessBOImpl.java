package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.AdoptionProcessBO;
import lk.ijse.dog_rescue_management_system.dao.custom.AdoptionProcessDAO;
import lk.ijse.dog_rescue_management_system.dao.DAOFactory;
import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.AdoptionProcessDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdoptionProcessBOImpl implements AdoptionProcessBO {
        AdoptionProcessDAO adoptionProcessDAO = (AdoptionProcessDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADOPTIONPROCESSS );

    @Override
    public ArrayList<AdoptionProcessDto> getAll() throws SQLException, ClassNotFoundException {
        return adoptionProcessDAO.getAll();
    }

    @Override
    public String getNextId() throws Exception {
        return adoptionProcessDAO.getNextId();
    }

    @Override
    public boolean save(AdoptionProcessDto dto) throws Exception {
//            return adoptionProcessDAO.save(dto);
        Connection connection = null;
        connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isSaved = adoptionProcessDAO.save(dto);

            if (isSaved) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean update(AdoptionProcessDto dto) throws Exception {

//           return adoptionProcessDAO.update(dto);
        Connection connection = null;
        connection = DBConnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            boolean isUpdated = adoptionProcessDAO.update(dto);
            if (isUpdated) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }

    }

    @Override
    public boolean delete(String adoptionId) throws Exception {

//            return adoptionProcessDAO.delete(adoptionId);
        Connection connection = null;
        connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isDeleted = adoptionProcessDAO.delete(adoptionId);

            if (isDeleted) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}
