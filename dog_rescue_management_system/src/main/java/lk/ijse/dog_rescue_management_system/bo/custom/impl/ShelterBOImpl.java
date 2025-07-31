package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.ShelterBO;
import lk.ijse.dog_rescue_management_system.dao.custom.AdminDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.DAOFactory;
import lk.ijse.dog_rescue_management_system.dao.custom.ShelterDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.impl.ShelterDAOImpl;
import lk.ijse.dog_rescue_management_system.dto.AdopterDto;
import lk.ijse.dog_rescue_management_system.dto.ShelterDto;
import lk.ijse.dog_rescue_management_system.entity.Adopter;
import lk.ijse.dog_rescue_management_system.entity.Shelter;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShelterBOImpl implements ShelterBO {
    ShelterDAO shelterDAO = (ShelterDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SHELTER);
    @Override
    public boolean saveShelter(ShelterDto shelterDto) throws Exception {
            return shelterDAO.save(new Shelter(
                    shelterDto.getShelterId(),shelterDto.getShelterLocation(), shelterDto.getShelterCapacity(),
                    shelterDto.getCurrentShelterOccupancy(),shelterDto.getShelterStatus(),shelterDto.getShelterResources()));
    }

    @Override
    public boolean updateShelter(ShelterDto shelterDto) throws Exception {
            return shelterDAO.update(new Shelter(
                    shelterDto.getShelterId(),shelterDto.getShelterLocation(), shelterDto.getShelterCapacity(),
                    shelterDto.getCurrentShelterOccupancy(),shelterDto.getShelterStatus(),shelterDto.getShelterResources()));
    }

    @Override
    public boolean deleteShelter(String shelterId) throws Exception {
            return shelterDAO.delete(shelterId);
    }

    @Override
    public String getNextShelterId() throws Exception {
        return shelterDAO.getNextId();
    }

    @Override
    public ArrayList<ShelterDto> getAllShelters() throws SQLException, ClassNotFoundException {
//        return shelterDAO.getAll();
        ArrayList<Shelter>entity=shelterDAO.getAll();
        ArrayList<ShelterDto>shelterDto=new ArrayList<>();
        for( Shelter shelter :entity){
            shelterDto.add(new ShelterDto(
                    shelter.getShelterId(),shelter.getShelterLocation(), shelter.getShelterCapacity(),
                    shelter.getCurrentShelterOccupancy(),shelter.getShelterStatus(),shelter.getShelterResources()));
        }
        return shelterDto;
    }
}
