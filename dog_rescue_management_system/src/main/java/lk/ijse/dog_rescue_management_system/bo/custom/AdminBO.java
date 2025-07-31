package lk.ijse.dog_rescue_management_system.bo.custom;

import lk.ijse.dog_rescue_management_system.dto.AdminDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminBO extends SuperBO {

    public ArrayList<AdminDto> getAllAdmins() throws SQLException, ClassNotFoundException;

    public boolean saveAdmin(AdminDto adminDto) throws SQLException, ClassNotFoundException;

    public boolean updateAdmin(AdminDto adminDto) throws SQLException, ClassNotFoundException;

    public boolean deleteAdmin(String adminId) throws SQLException, ClassNotFoundException;

    public String getNextAdminId() throws Exception;

}
