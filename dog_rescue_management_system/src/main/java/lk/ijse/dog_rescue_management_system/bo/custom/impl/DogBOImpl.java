package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.DogBO;
import lk.ijse.dog_rescue_management_system.dao.custom.AdminDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.DAOFactory;
import lk.ijse.dog_rescue_management_system.dao.custom.DogDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.impl.DogDAOImpl;
import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.AdopterDto;
import lk.ijse.dog_rescue_management_system.dto.DogDto;
import lk.ijse.dog_rescue_management_system.entity.Adopter;
import lk.ijse.dog_rescue_management_system.entity.Dog;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DogBOImpl implements DogBO {

    DogDAO dogDAO = (DogDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DOG);
    @Override
    public boolean saveDog(DogDto dogDto) throws Exception {
        return dogDAO.save(new Dog(
                dogDto.getDogId(),dogDto.getRescueRequestId(), dogDto.getDogName(),
                dogDto.getDogBreed(),dogDto.getDogColor(),dogDto.getDogSize(),
                dogDto.getDogGender(),dogDto.getDogStatus(),dogDto.getDogEstAge()));
    }

    @Override
    public boolean deleteDog(String requestId) throws Exception {
        return dogDAO.delete(requestId);
    }

    @Override
    public boolean updateDog(DogDto dogDto) throws Exception {
        return dogDAO.update(new Dog(
                dogDto.getDogId(),dogDto.getRescueRequestId(), dogDto.getDogName(),
                dogDto.getDogBreed(),dogDto.getDogColor(),dogDto.getDogSize(),
                dogDto.getDogGender(),dogDto.getDogStatus(),dogDto.getDogEstAge()));
    }

    @Override
    public ArrayList<DogDto> getAllDogs() throws SQLException, ClassNotFoundException {
//        return dogDAO.getAll();
        ArrayList<Dog>entity=dogDAO.getAll();
        ArrayList<DogDto>dogDto=new ArrayList<>();
        for( Dog dog :entity){
            dogDto.add(new DogDto(
                    dog.getDogId(),dog.getRescueRequestId(), dog.getDogName(),
                    dog.getDogBreed(),dog.getDogColor(),dog.getDogSize(),
                    dog.getDogGender(),dog.getDogStatus(),dog.getDogEstAge()));
        }
        return dogDto;
    }

    @Override
    public String getNextDogId() throws Exception {
        return dogDAO.getNextId();
    }

    @Override
    public int getDogCount() throws SQLException, ClassNotFoundException {
        return dogDAO.getDogCount();
    }
}
