//package lk.ijse.dog_rescue_management_system.dao.custom;
//
//import lk.ijse.dog_rescue_management_system.dao.CrudDAO;
//import lk.ijse.dog_rescue_management_system.dto.UserDto;
//import lk.ijse.dog_rescue_management_system.entity.User;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public interface UserDAO extends CrudDAO<User> {
//
//    public UserDto authenticateUser(String username, String password) throws SQLException, ClassNotFoundException;
//    public boolean checkLogin(UserDto userDto) throws SQLException, ClassNotFoundException;
//    public UserDto getUserById(String userId) throws SQLException, ClassNotFoundException;
//    public boolean updatePassword(String username, String newPassword) throws SQLException, ClassNotFoundException;
//    public List<UserDto> getAllUsers() throws SQLException, ClassNotFoundException;
//}
