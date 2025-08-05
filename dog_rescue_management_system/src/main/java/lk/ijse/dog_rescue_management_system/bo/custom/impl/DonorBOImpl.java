package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.DonorBO;
import lk.ijse.dog_rescue_management_system.dao.DAOFactory;
import lk.ijse.dog_rescue_management_system.dao.custom.DonorDAO;
import lk.ijse.dog_rescue_management_system.dto.DonorDto;
import lk.ijse.dog_rescue_management_system.entity.Donor;

import java.sql.SQLException;
import java.util.ArrayList;

public class DonorBOImpl implements DonorBO {
    DonorDAO donorDAO = (DonorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DONOR);
    @Override
    public boolean saveDonor(DonorDto donorDto) throws Exception {
            return donorDAO.save(new Donor(
                    donorDto.getDonorId(),donorDto.getDonorName(), donorDto.getDonorContact(),
                    donorDto.getDonorEmail(),donorDto.getDonorAddress(),donorDto.getDonorAmount()));
    }

    @Override
    public boolean updateDonor(DonorDto donorDto) throws Exception {
            return donorDAO.update(new Donor(
                    donorDto.getDonorId(),donorDto.getDonorName(), donorDto.getDonorContact(),
                    donorDto.getDonorEmail(),donorDto.getDonorAddress(),donorDto.getDonorAmount()));
    }

    @Override
    public boolean deleteDonor(String donorId) throws Exception {

            return donorDAO.delete(donorId);
    }

    @Override
    public String getNextDonorId() throws Exception {
       return donorDAO.getNextId();
    }

    @Override
    public ArrayList<DonorDto> getAllDonors() throws SQLException, ClassNotFoundException {
//        return donorDAO.getAll();
        ArrayList<Donor>entity=donorDAO.getAll();
        ArrayList<DonorDto>donorDto=new ArrayList<>();
        for( Donor donor :entity){
            donorDto.add(new DonorDto(
                    donor.getDonorId(),donor.getDonorName(), donor.getDonorContact(),
                    donor.getDonorEmail(),donor.getDonorAddress(),donor.getDonorAmount()));
        }
        return donorDto;
    }
}
