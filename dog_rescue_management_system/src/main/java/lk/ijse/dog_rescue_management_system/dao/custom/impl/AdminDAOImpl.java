package lk.ijse.dog_rescue_management_system.dao.custom.impl;

import lk.ijse.dog_rescue_management_system.dao.custom.AdminDAO;
import lk.ijse.dog_rescue_management_system.dto.AdminDto;
import lk.ijse.dog_rescue_management_system.entity.Admin;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public ArrayList<Admin> getAll() throws SQLException, ClassNotFoundException {

            ResultSet rst = CrudUtil.execute("select * from admins");

            ArrayList<Admin> entity = new ArrayList<>();
            while (rst.next()) {
//                AdminDto adminDto = new AdminDto(
//                        resultSet.getString(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getString(4),
//                        resultSet.getString(5)
//                AdminDto adminDto = new AdminDto(rst.getString(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5));
                entity.add(new Admin(rst.getString(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5)));
            }
            return entity;
    }

    @Override
    public boolean save(Admin entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "insert into admins set  admin_id =?, admin_name =?, admin_contact =?, admin_email  =?, admin_address =?",
                entity.getAdminId(),
                entity.getAdminName(),
                entity.getAdminContact(),
                entity.getAdminEmail(),
                entity.getAdminAddress()
        );
    }

    @Override
    public boolean update(Admin entity) throws SQLException, ClassNotFoundException {
                return CrudUtil.execute("update admins set admin_name =?, admin_contact =?, admin_email  =?, admin_address =? where admin_id=?",

                entity.getAdminName(),
                entity.getAdminContact(),
                entity.getAdminEmail(),
                entity.getAdminAddress(),
                entity.getAdminId()
        );
    }

    @Override
    public boolean delete(String adminId) throws SQLException, ClassNotFoundException {
                return CrudUtil.execute(
                "delete from admins where admin_id=?", adminId
        );
    }

    @Override
    public String getNextId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("select admin_id from admins order by admin_id desc limit 1");
        String tableString = "A";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1); // skip "A"
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNUmber = lastIdNumber + 1;
            String nextIdString = String.format(tableString + "%03d", nextIdNUmber);

            return nextIdString;
        }
        return tableString + "001";
    }


}
