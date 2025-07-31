package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.RescuerBO;
import lk.ijse.dog_rescue_management_system.dao.custom.AdminDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.DAOFactory;
import lk.ijse.dog_rescue_management_system.dao.custom.RescuerDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.impl.RescuerDAOImpl;
import lk.ijse.dog_rescue_management_system.dto.AdopterDto;
import lk.ijse.dog_rescue_management_system.dto.RescuerDto;
import lk.ijse.dog_rescue_management_system.entity.Adopter;
import lk.ijse.dog_rescue_management_system.entity.Rescuer;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RescuerBOImpl implements RescuerBO {
    RescuerDAO rescuerDAO = (RescuerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESCUER);

    @Override
    public boolean saveRescuer(RescuerDto rescuerDto) throws Exception {

            return rescuerDAO.save(new Rescuer(
                    rescuerDto.getRescuerId(),rescuerDto.getRescuerName(), rescuerDto.getRescuerSpecialty(),
                    rescuerDto.getRescuerStatus(),rescuerDto.getRescuerEmail(),rescuerDto.getRescuerAddress(),
                    rescuerDto.getRescuerContact(),rescuerDto.getRescuerSalary()));
    }

    @Override
    public boolean deleteRescuer(String rescuerId) throws Exception {

            return rescuerDAO.delete(rescuerId);
    }

    @Override
    public boolean updateRescuer(RescuerDto rescuerDto) throws SQLException, ClassNotFoundException {

            return rescuerDAO.update(new Rescuer(
                    rescuerDto.getRescuerId(),rescuerDto.getRescuerName(), rescuerDto.getRescuerSpecialty(),
                    rescuerDto.getRescuerStatus(),rescuerDto.getRescuerEmail(),rescuerDto.getRescuerAddress(),
                    rescuerDto.getRescuerContact(),rescuerDto.getRescuerSalary()));
    }

    @Override
    public String getNextRescuerId() throws Exception {
        return rescuerDAO.getNextId();
    }

    @Override
    public ArrayList<RescuerDto> getAllRescuers() throws SQLException, ClassNotFoundException {
//        return rescuerDAO.getAll();
        ArrayList<Rescuer>entity=rescuerDAO.getAll();
        ArrayList<RescuerDto>rescuerDto=new ArrayList<>();
        for(Rescuer rescuer :entity){
            rescuerDto.add(new RescuerDto(
                    rescuer.getRescuerId(),rescuer.getRescuerName(), rescuer.getRescuerSpecialty(),
                    rescuer.getRescuerStatus(),rescuer.getRescuerEmail(),rescuer.getRescuerAddress(),
                    rescuer.getRescuerContact(),rescuer.getRescuerSalary()));
        }
        return rescuerDto;
    }
}
