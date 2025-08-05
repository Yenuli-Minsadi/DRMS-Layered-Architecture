package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.VetBO;
import lk.ijse.dog_rescue_management_system.dao.DAOFactory;
import lk.ijse.dog_rescue_management_system.dao.custom.VetDAO;
import lk.ijse.dog_rescue_management_system.dto.VetDto;
import lk.ijse.dog_rescue_management_system.entity.Vet;

import java.sql.SQLException;
import java.util.ArrayList;

public class VetBOImpl implements VetBO {
    VetDAO vetDAO = (VetDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VET);
    @Override
    public boolean saveVet(VetDto vetDto) throws Exception {
            return vetDAO.save(new Vet(
                    vetDto.getVetId(),vetDto.getVetName(), vetDto.getVetClinicName(),
                    vetDto.getVetSpecialization(),vetDto.getVetLicenseNum(),vetDto.getVetContact(),
                    vetDto.getVetEmail(),vetDto.getVetAddress(),vetDto.getVetAvailability(),vetDto.getVetSalary()));
    }

    @Override
    public boolean deleteVet(String vetId) throws Exception {
            return vetDAO.delete(vetId);
    }

    @Override
    public boolean updateVet(VetDto vetDto) throws SQLException, ClassNotFoundException {

            return vetDAO.update(new Vet(
                    vetDto.getVetId(),vetDto.getVetName(), vetDto.getVetClinicName(),
                    vetDto.getVetSpecialization(),vetDto.getVetLicenseNum(),vetDto.getVetContact(),
                    vetDto.getVetEmail(),vetDto.getVetAddress(),vetDto.getVetAvailability(),vetDto.getVetSalary()));
    }

    @Override
    public String getNextVetId() throws Exception {
        return vetDAO.getNextId();
    }

    @Override
    public ArrayList<VetDto> getAllVets() throws SQLException, ClassNotFoundException {
//        return vetDAO.getAll();
        ArrayList<Vet>entity=vetDAO.getAll();
        ArrayList<VetDto>vetDto=new ArrayList<>();
        for( Vet vet :entity){
            vetDto.add(new VetDto(
                    vet.getVetId(),vet.getVetName(), vet.getVetClinicName(),
                    vet.getVetSpecialization(),vet.getVetLicenseNum(),vet.getVetContact(),
                    vet.getVetEmail(),vet.getVetAddress(),vet.getVetAvailability(),vet.getVetSalary()));
        }
        return vetDto;
    }
}
