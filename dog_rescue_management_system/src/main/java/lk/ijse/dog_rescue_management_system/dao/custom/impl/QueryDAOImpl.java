package lk.ijse.dog_rescue_management_system.dao.custom.impl;

import lk.ijse.dog_rescue_management_system.dao.CrudUtil;
import lk.ijse.dog_rescue_management_system.dao.custom.QueryDAO;
import lk.ijse.dog_rescue_management_system.dto.UserDto;
import lk.ijse.dog_rescue_management_system.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {


    /**
     * Authenticates a user by checking credentials against the database
     * The role is determined from the database based on username and password
     *
     * @param username The username to check
     * @param password The password to check
     * @return UserDto if authentication successful, null otherwise
     * @throws SQLException if database error occurs
     * @throws ClassNotFoundException if driver not found
     */
    @Override
    public UserDto authenticateUser(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
        ResultSet resultSet = CrudUtil.execute(sql, username, password);

        if (resultSet.next()) {
            // User exists with given credentials
            UserDto user = new UserDto();
            user.setUserId(resultSet.getString("user_id"));
            user.setUserName(resultSet.getString("name"));
            // Don't set password for security reasons
            user.setUserRole(resultSet.getString("role"));
            // Set other user properties as needed

            return user;
        }

        return null; // Authentication failed
    }

    /**
     * Legacy method for checking login, kept for backward compatibility
     * @param userDto Contains user credentials
     * @return true if authentication successful, false otherwise
     * @throws SQLException if database error occurs
     * @throws ClassNotFoundException if driver not found
     */


    /**
     * Retrieves user by ID
     * @param userId The user ID to retrieve
     * @return UserDto if user found, null otherwise
     * @throws SQLException if database error occurs
     * @throws ClassNotFoundException if driver not found
     */

    /**
     * Updates user password for password reset functionality
     * @param username The username to update
     * @param newPassword The new password
     * @return true if password updated successfully, false otherwise
     * @throws SQLException if database error occurs
     * @throws ClassNotFoundException if driver not found
     */

    /**
     * Gets a list of all users
     * @return List of UserDto objects
     * @throws SQLException if database error occurs
     * @throws ClassNotFoundException if driver not found
     */

    @Override
    public int getAdoptedDogCount() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute(
                "SELECT COUNT(DISTINCT dog_id) FROM adoption_process WHERE adoption_status IN ('Approved', 'Completed')"
        );
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public int getDogCount() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) FROM dog");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public int getScheduledAppointmentCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute(
                "SELECT COUNT(*) FROM appointments WHERE status = 'Scheduled'"
        );

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return 0;
    }
}
