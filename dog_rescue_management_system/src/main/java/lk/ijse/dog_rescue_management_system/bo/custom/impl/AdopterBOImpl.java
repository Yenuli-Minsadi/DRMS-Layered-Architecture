package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.AdopterBO;
import lk.ijse.dog_rescue_management_system.dao.custom.AdopterDAO;
import lk.ijse.dog_rescue_management_system.dao.DAOFactory;
import lk.ijse.dog_rescue_management_system.dto.AdopterDto;
import lk.ijse.dog_rescue_management_system.entity.Adopter;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdopterBOImpl implements AdopterBO {

    AdopterDAO adopterDAO = (AdopterDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADOPTER);

    @Override
    public ArrayList<AdopterDto> getAllAdopters() throws SQLException, ClassNotFoundException {
//        return adopterDAO.getAll();
        ArrayList<Adopter>entity=adopterDAO.getAll();
        ArrayList<AdopterDto>adopterDto=new ArrayList<>();
        for( Adopter adopter :entity){
            adopterDto.add(new AdopterDto(
                    adopter.getAdopterId(),adopter.getAdopterName(), adopter.getAdopterContact(),
                    adopter.getAdopterAddress(),adopter.getAdopterEmail(),adopter.getAdopterHasOtherPets(),
                    adopter.getAdopterApplyDate(),adopter.getAdopterIncomeStatus()));
        }
        return adopterDto;
    }

    @Override
    public String getNextAdopterId() throws Exception {
       return adopterDAO.getNextId();
    }

    @Override
    public boolean saveAdopter(AdopterDto adopterDto) throws Exception {
       return adopterDAO.save(new Adopter(
               adopterDto.getAdopterId(),adopterDto.getAdopterName(), adopterDto.getAdopterContact(),
               adopterDto.getAdopterAddress(),adopterDto.getAdopterEmail(),adopterDto.getAdopterHasOtherPets(),
               adopterDto.getAdopterApplyDate(),adopterDto.getAdopterIncomeStatus()));
    }

    @Override
    public boolean updateAdopter(AdopterDto adopterDto) throws Exception {
        return adopterDAO.update(new Adopter(
                adopterDto.getAdopterId(),adopterDto.getAdopterName(), adopterDto.getAdopterContact(),
                adopterDto.getAdopterAddress(),adopterDto.getAdopterEmail(),adopterDto.getAdopterHasOtherPets(),
                adopterDto.getAdopterApplyDate(),adopterDto.getAdopterIncomeStatus()));
    }

    @Override
    public boolean deleteAdopter(String adopterId) throws Exception {
        return adopterDAO.delete(adopterId);
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
