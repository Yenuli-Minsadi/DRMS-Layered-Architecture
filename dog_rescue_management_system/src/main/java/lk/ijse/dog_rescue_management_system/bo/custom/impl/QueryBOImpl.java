package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.QueryBO;
import lk.ijse.dog_rescue_management_system.dao.DAOFactory;
import lk.ijse.dog_rescue_management_system.dao.custom.QueryDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.RescuerDAO;
import lk.ijse.dog_rescue_management_system.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public class QueryBOImpl implements QueryBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public UserDto authenticateUser(String username, String password) throws SQLException, ClassNotFoundException {
        return queryDAO.authenticateUser(username, password);
    }

    @Override
    public int getAdoptedDogCount() throws SQLException, ClassNotFoundException {
        return queryDAO.getAdoptedDogCount();
    }

    @Override
    public int getDogCount() throws SQLException, ClassNotFoundException {
        return queryDAO.getDogCount();
    }

    @Override
    public int getScheduledAppointmentCount() throws SQLException, ClassNotFoundException {
        return queryDAO.getScheduledAppointmentCount();
    }


//    @Override
//    public boolean checkLogin(UserDto userDto) throws SQLException, ClassNotFoundException {
//        return false;
//    }

//    @Override
//    public UserDto getUserById(String userId) throws SQLException, ClassNotFoundException {
//        return null;
//    }
//
//    @Override
//    public boolean updatePassword(String username, String newPassword) throws SQLException, ClassNotFoundException {
//        return false;
//    }
//
//    @Override
//    public List<UserDto> getAllUsers() throws SQLException, ClassNotFoundException {
//        return List.of();
//    }
}
