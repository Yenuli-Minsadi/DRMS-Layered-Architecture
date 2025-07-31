package lk.ijse.dog_rescue_management_system.dao.custom.impl;

import lk.ijse.dog_rescue_management_system.dao.custom.VetDAO;
import lk.ijse.dog_rescue_management_system.dto.VetDto;
import lk.ijse.dog_rescue_management_system.entity.Vet;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VetDAOImpl implements VetDAO {
    @Override
    public boolean save(Vet entity) throws Exception {
            return CrudUtil.execute(
                    "insert into vet set vet_id=?, vet_name=?, clinic_name=?, specialization =?, vet_license_num=?, vet_contact = ?, vet_email = ?, vet_address = ?, availability = ?, salary = ?",
                    entity.getVetId(),
                    entity.getVetName(),
                    entity.getVetClinicName(),
                    entity.getVetSpecialization(),
                    entity.getVetLicenseNum(),
                    entity.getVetContact(),
                    entity.getVetEmail(),
                    entity.getVetAddress(),
                    entity.getVetAvailability(),
                    entity.getVetSalary()
            );
    }

    @Override
    public boolean delete(String vetId) throws Exception {
            return CrudUtil.execute(
                    "delete from vet where vet_id=?",
                    vetId
            );
    }

    @Override
    public boolean update(Vet entity) throws SQLException, ClassNotFoundException {

            return CrudUtil.execute(
                    "update vet set vet_name=?, clinic_name=?, specialization =?, vet_license_num=?, vet_contact = ?, vet_email = ?, vet_address = ?, availability = ?, salary = ? where vet_id =?",
                    entity.getVetName(),
                    entity.getVetClinicName(),
                    entity.getVetSpecialization(),
                    entity.getVetLicenseNum(),
                    entity.getVetContact(),
                    entity.getVetEmail(),
                    entity.getVetAddress(),
                    entity.getVetAvailability(),
                    entity.getVetSalary(),
                    entity.getVetId()
            );
    }

    @Override
    public String getNextId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("select vet_id from vet order by vet_id desc limit 1");
        String tableString = "V";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1); // skip "V"
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNUmber = lastIdNumber + 1;
            String nextIdString = String.format(tableString + "%03d", nextIdNUmber);

            return nextIdString;
        }
        return tableString + "001";
    }

    @Override
    public ArrayList<Vet> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from vet");

        ArrayList<Vet> entity = new ArrayList<>();
        while (resultSet.next()) {
//            VetDto vetDto = new VetDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    resultSet.getString(6),
//                    resultSet.getString(7),
//                    resultSet.getString(8),
//                    resultSet.getString(9),
//                    resultSet.getDouble(10)
//            );
            entity.add(new Vet(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getDouble(10)
            ));
        }
        return entity;
    }
}
