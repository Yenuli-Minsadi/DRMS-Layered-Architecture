package lk.ijse.dog_rescue_management_system.dao.custom.impl;

import lk.ijse.dog_rescue_management_system.dao.custom.ShelterDAO;
import lk.ijse.dog_rescue_management_system.entity.Shelter;
import lk.ijse.dog_rescue_management_system.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShelterDAOImpl implements ShelterDAO {
    @Override
    public boolean save(Shelter entity) throws Exception {
            return CrudUtil.execute(
                    "insert into shelter set  shelter_id=?, shelter_location=?, shelter_capacity=?, current_shelter_occupancy=?, status=?, resources=?",
                    entity.getShelterId(),
                    entity.getShelterLocation(),
                    entity.getShelterCapacity(),
                    entity.getCurrentShelterOccupancy(),
                    entity.getShelterStatus(),
                    entity.getShelterResources()
            );
    }

    @Override
    public boolean update(Shelter entity) throws Exception {
            return CrudUtil.execute(
                    "update shelter set shelter_location=?, shelter_capacity=?, current_shelter_occupancy=?, status=?, resources=? where shelter_id=?",
                    entity.getShelterLocation(),
                    entity.getShelterCapacity(),
                    entity.getCurrentShelterOccupancy(),
                    entity.getShelterStatus(),
                    entity.getShelterResources(),
                    entity.getShelterId()
            );
    }

    @Override
    public boolean delete(String shelterId) throws Exception {
            return CrudUtil.execute(
                    "delete from shelter where shelter_id=?",
                    shelterId
            );
    }

    @Override
    public String getNextId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("select shelter_id from shelter order by shelter_id desc limit 1");
        String tableString = "S";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1); // skip "S"
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNUmber = lastIdNumber + 1;
            String nextIdString = String.format(tableString + "%03d", nextIdNUmber);

            return nextIdString;
        }
        return tableString + "001";
    }

    @Override
    public ArrayList<Shelter> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from shelter");

        ArrayList<Shelter> entity = new ArrayList<>();
        while (resultSet.next()) {
//            ShelterDto shelterDto = new ShelterDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getInt(3),
//                    resultSet.getInt(4),
//                    resultSet.getString(5),
//                    resultSet.getString(6)
//            );
            entity.add(new Shelter(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return entity;
    }
}
