package lk.ijse.dog_rescue_management_system.dao.custom.impl;

import lk.ijse.dog_rescue_management_system.dao.custom.DonorDAO;
import lk.ijse.dog_rescue_management_system.dto.DonorDto;
import lk.ijse.dog_rescue_management_system.entity.Donor;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DonorDAOImpl implements DonorDAO {
    @Override
    public boolean save(Donor entity) throws Exception {
            return CrudUtil.execute(
                    "insert into donor set  donor_id =?, donor_name =?, donor_contact =?, donor_email  =?, donor_address =?, donation_amount=?",
                    entity.getDonorId(),
                    entity.getDonorName(),
                    entity.getDonorContact(),
                    entity.getDonorEmail(),
                    entity.getDonorAddress(),
                    entity.getDonorAmount()
            );
    }

    @Override
    public boolean update(Donor entity) throws Exception {
            return CrudUtil.execute(
                    "update donor set donor_name =?, donor_contact =?, donor_email  =?, donor_address =?, donation_amount=? where donor_id =?",
                    entity.getDonorName(),
                    entity.getDonorContact(),
                    entity.getDonorEmail(),
                    entity.getDonorAddress(),
                    entity.getDonorAmount(),
                    entity.getDonorId()
            );
    }

    @Override
    public boolean delete(String donorId) throws Exception {

            return CrudUtil.execute(
                    "delete from donor where donor_id=?",
                    donorId
            );
    }

    @Override
    public String getNextId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("select donor_id from donor order by donor_id desc limit 1");
        String tableString = "DN";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(2); // skip "DN"
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNUmber = lastIdNumber + 1;
            String nextIdString = String.format(tableString + "%03d", nextIdNUmber);

            return nextIdString;
        }
        return tableString + "001";
    }

    @Override
    public ArrayList<Donor> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from donor");

        ArrayList<Donor> entity = new ArrayList<>();
        while (resultSet.next()) {
//            DonorDto donorDto = new DonorDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    resultSet.getDouble(6)
//            );
            entity.add(new Donor(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6)));
        }
        return entity;
    }
}
