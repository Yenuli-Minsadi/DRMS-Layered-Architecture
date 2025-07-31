package lk.ijse.dog_rescue_management_system.dao.custom.impl;

import lk.ijse.dog_rescue_management_system.dao.custom.DogDAO;
import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.DogDto;
import lk.ijse.dog_rescue_management_system.entity.Dog;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DogDAOImpl implements DogDAO {
    @Override
    public boolean save(Dog entity) throws Exception {
        return CrudUtil.execute(
                "insert into dog (dog_id, rescue_request_id, dog_name, dog_breed, dog_color, dog_size, dog_gender, dog_status, estimated_age) values (?,?,?,?,?,?,?,?,?)",
                entity.getDogId(),
                entity.getRescueRequestId(),
                entity.getDogName(),
                entity.getDogBreed(),
                entity.getDogColor(),
                entity.getDogSize(),
                entity.getDogGender(),
                entity.getDogStatus(),
                entity.getDogEstAge()
        );
    }

    @Override
    public boolean delete(String requestId) throws Exception {

        return CrudUtil.execute(
                "delete from dog where dog_id=?",
                requestId
        );
    }

    @Override
    public boolean update(Dog entity) throws Exception {

        return CrudUtil.execute(
                "update dog set rescue_request_id =?, dog_name =?, dog_breed =?, dog_color =?, dog_size =?, dog_gender =?, dog_status =?, estimated_age =? where dog_id =?",
                entity.getRescueRequestId(),
                entity.getDogName(),
                entity.getDogBreed(),
                entity.getDogColor(),
                entity.getDogSize(),
                entity.getDogGender(),
                entity.getDogStatus(),
                entity.getDogEstAge(),
                entity.getDogId()
        );
    }

    @Override
    public ArrayList<Dog> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM dog");
        ResultSet resultSet = pst.executeQuery();

        ArrayList<Dog> entity = new ArrayList<>();
        while (resultSet.next()) {
            try {
                // Get column values by name instead of index to avoid column order issues
                String dogId = resultSet.getString("dog_id");
                String rescueRequestId = resultSet.getString("rescue_request_id");
                String dogName = resultSet.getString("dog_name");
                String dogBreed = resultSet.getString("dog_breed");
                String dogColor = resultSet.getString("dog_color");
                String dogSize = resultSet.getString("dog_size");
                String dogGender = resultSet.getString("dog_gender");
                String dogStatus = resultSet.getString("dog_status");
                String dogEstAge = resultSet.getString("estimated_age");

//                DogDto dogDto = new DogDto(
//                        dogId,
//                        rescueRequestId,
//                        dogName,
//                        dogBreed,
//                        dogColor,
//                        dogSize,
//                        dogGender,
//                        dogStatus,
//                        dogEstAge
//                );
                entity.add(new Dog(
                        dogId,
                        rescueRequestId,
                        dogName,
                        dogBreed,
                        dogColor,
                        dogSize,
                        dogGender,
                        dogStatus,
                        dogEstAge
                ));
            } catch (SQLException e) {
                System.out.println("Error processing row: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return entity;
    }

    @Override
    public String getNextId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("select dog_id from dog order by dog_id desc limit 1");
        String tableString = "D";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);//skip D
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNUmber = lastIdNumber + 1;
            String nextIdString = String.format(tableString + "%03d", nextIdNUmber);
            return nextIdString;
        }
        return tableString + "001";
    }

    @Override
    public int getDogCount() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) FROM dog");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }
}
