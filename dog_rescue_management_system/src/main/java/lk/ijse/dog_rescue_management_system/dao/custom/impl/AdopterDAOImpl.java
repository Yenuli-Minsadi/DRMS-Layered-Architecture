package lk.ijse.dog_rescue_management_system.dao.custom.impl;

import lk.ijse.dog_rescue_management_system.dao.custom.AdopterDAO;
import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.AdopterDto;
import lk.ijse.dog_rescue_management_system.entity.Adopter;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdopterDAOImpl implements AdopterDAO {

    @Override
    public ArrayList<Adopter> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from adopter");

        ArrayList<Adopter> entity = new ArrayList<>();
        while (resultSet.next()) {
//            AdopterDto adopterDto = new AdopterDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    resultSet.getString(6),
//                    resultSet.getDate(7).toLocalDate(),
//                    resultSet.getString(8)
//            );
            entity.add(new Adopter(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDate(7).toLocalDate(),
                    resultSet.getString(8)));
        }
        return entity;
    }

    @Override
    public String getNextId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("select adopter_id from adopter order by adopter_id desc limit 1");
        String tableString = "AD";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(2); // skip "AD"
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNUmber = lastIdNumber + 1;

            String nextIdString = String.format(tableString + "%03d", nextIdNUmber);

            return nextIdString;
        }
        // I
        return tableString + "001";
    }

    @Override
    public boolean save(Adopter entity) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "INSERT INTO adopter (adopter_id, adopter_name, adopter_contact, adopter_address, adopter_email, adopter_has_other_pets , application_date , adopter_income_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setString(1, entity.getAdopterId());
        pst.setString(2, entity.getAdopterName());
        pst.setString(3, entity.getAdopterContact());
        pst.setString(4, entity.getAdopterAddress());
        pst.setString(5, entity.getAdopterEmail());
        pst.setString(6, entity.getAdopterHasOtherPets());
        pst.setString(7, String.valueOf(entity.getAdopterApplyDate()));
        pst.setString(8, entity.getAdopterIncomeStatus());

        int i = pst.executeUpdate();
        boolean isSaved = i > 0;
        return isSaved;
    }

    @Override
    public boolean update(Adopter entity) throws Exception {
        return CrudUtil.execute(
                "update adopter set adopter_name =?, adopter_contact =?, adopter_address  =?, adopter_email =?, adopter_has_other_pets=?, application_date=?, adopter_income_status=? where adopter_id =?",
                entity.getAdopterName(),
                entity.getAdopterContact(),
                entity.getAdopterAddress(),
                entity.getAdopterEmail(),
                entity.getAdopterHasOtherPets(),
                entity.getAdopterApplyDate(),
                entity.getAdopterIncomeStatus(),
                entity.getAdopterId()
        );
    }

    @Override
    public boolean delete(String adopterId) throws Exception {
        return CrudUtil.execute(
                "delete from adopter where adopter_id=?",
                adopterId
        );
    }


//    @Override
//    public  boolean saveAdopter(AdopterDto adopterDto) throws Exception {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "INSERT INTO adopter (adopter_id, adopter_name, adopter_contact, adopter_address, adopter_email, adopter_has_other_pets , application_date , adopter_income_status) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//
//        PreparedStatement pst = connection.prepareStatement(sql);
//
//        pst.setString(1, adopterDto.getAdopterId());
//        pst.setString(2, adopterDto.getAdopterName());
//        pst.setString(3, adopterDto.getAdopterContact());
//        pst.setString(4, adopterDto.getAdopterAddress());
//        pst.setString(5, adopterDto.getAdopterEmail());
//        pst.setString(6, adopterDto.getAdopterHasOtherPets());
//        pst.setString(7, String.valueOf(adopterDto.getAdopterApplyDate()));
//        pst.setString(8, adopterDto.getAdopterIncomeStatus());
//
//        int i = pst.executeUpdate();
//        boolean isSaved = i > 0;
//        return isSaved;
//    }
}
