package lk.ijse.dog_rescue_management_system.dao.custom.impl;

import lk.ijse.dog_rescue_management_system.dao.custom.RescuerDAO;
import lk.ijse.dog_rescue_management_system.dto.RescuerDto;
import lk.ijse.dog_rescue_management_system.entity.Rescuer;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RescuerDAOImpl implements RescuerDAO {

    @Override
    public boolean save(Rescuer entity) throws Exception {

            return CrudUtil.execute(
                    "insert into rescuer set  rescuer_id =?, rescuer_name =?, rescuer_specialty =?, rescuer_status  =?, rescuer_email =?, rescuer_address  = ?, rescuer_contact = ?, salary = ?",
                    entity.getRescuerId(),
                    entity.getRescuerName(),
                    entity.getRescuerSpecialty(),
                    entity.getRescuerStatus(),
                    entity.getRescuerEmail(),
                    entity.getRescuerAddress(),
                    entity.getRescuerContact(),
                    entity.getRescuerSalary()
            );
    }

    @Override
    public boolean delete(String rescuerId) throws Exception {

            return CrudUtil.execute(
                    "delete from rescuer where rescuer_id=?",
                    rescuerId
            );
    }

    @Override
    public boolean update(Rescuer entity) throws SQLException, ClassNotFoundException {

            return CrudUtil.execute(
                    "update rescuer set rescuer_name =?, rescuer_specialty =?, rescuer_status  =?, rescuer_email =?, rescuer_address  = ?, rescuer_contact = ?, salary = ? where rescuer_id =?",
                    entity.getRescuerName(),
                    entity.getRescuerSpecialty(),
                    entity.getRescuerStatus(),
                    entity.getRescuerEmail(),
                    entity.getRescuerAddress(),
                    entity.getRescuerContact(),
                    entity.getRescuerSalary(),
                    entity.getRescuerId()
            );
    }

    @Override
    public String getNextId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("select rescuer_id from rescuer order by rescuer_id desc limit 1");
        String tableString = "R";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1); // skip "R"
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNUmber = lastIdNumber + 1;
            String nextIdString = String.format(tableString + "%03d", nextIdNUmber);

            return nextIdString;
        }
        return tableString + "001";
    }

    @Override
    public ArrayList<Rescuer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from rescuer");

        ArrayList<Rescuer> entity = new ArrayList<>();
        while (resultSet.next()) {
//            RescuerDto rescuerDto = new RescuerDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    resultSet.getString(6),
//                    resultSet.getString(7),
//                    resultSet.getDouble(8)
//            );
            entity.add(new Rescuer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getDouble(8)
            ));
        }
        return entity;
    }
}
