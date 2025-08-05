package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.AdminBO;
import lk.ijse.dog_rescue_management_system.dao.custom.AdminDAO;
import lk.ijse.dog_rescue_management_system.dao.DAOFactory;
import lk.ijse.dog_rescue_management_system.dto.AdminDto;
import lk.ijse.dog_rescue_management_system.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminBOImpl implements AdminBO {

    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);

    @Override
    public ArrayList<AdminDto> getAllAdmins() throws SQLException, ClassNotFoundException {
//           return adminDAO.getAll();
        ArrayList<Admin>entity=adminDAO.getAll();
        ArrayList<AdminDto>adminDto=new ArrayList<>();
        for( Admin admin :entity){
            adminDto.add(new AdminDto(
                    admin.getAdminId(),admin.getAdminName(), admin.getAdminContact(),
                    admin.getAdminContact(),admin.getAdminAddress()
            ));
        }
        return adminDto;
    }

    @Override
    public boolean saveAdmin(AdminDto adminDto) throws SQLException, ClassNotFoundException {
        return adminDAO.save(new Admin(
                adminDto.getAdminId(),adminDto.getAdminName(), adminDto.getAdminContact(),
                adminDto.getAdminContact(),adminDto.getAdminAddress()
        ));
    }

    @Override
    public boolean updateAdmin(AdminDto adminDto) throws SQLException, ClassNotFoundException {
        return adminDAO.update(new Admin(
                adminDto.getAdminId(),adminDto.getAdminName(), adminDto.getAdminContact(),
                adminDto.getAdminContact(),adminDto.getAdminAddress()
        ));
    }

    @Override
    public boolean deleteAdmin(String adminId) throws SQLException, ClassNotFoundException {
        return adminDAO.delete(adminId);
    }

    @Override
    public String getNextAdminId() throws Exception {
        return adminDAO.getNextId();
    }
}
