package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.RequestCaseBO;
import lk.ijse.dog_rescue_management_system.dao.custom.AdminDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.DAOFactory;
import lk.ijse.dog_rescue_management_system.dao.custom.RequestCaseDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.impl.RequestCaseDAOImpl;
import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.AdopterDto;
import lk.ijse.dog_rescue_management_system.dto.RequestCaseDto;
import lk.ijse.dog_rescue_management_system.entity.Adopter;
import lk.ijse.dog_rescue_management_system.entity.RequestCase;
import lk.ijse.dog_rescue_management_system.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RequestCaseBOImpl implements RequestCaseBO {
    RequestCaseDAO requestCaseDAO = (RequestCaseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REQUESTCASE);

    @Override
    public ArrayList<RequestCaseDto> getAllRequests() throws SQLException, ClassNotFoundException {
//       return requestCaseDAO.getAll();
        ArrayList<RequestCase>entity=requestCaseDAO.getAll();
        ArrayList<RequestCaseDto>requestCaseDto=new ArrayList<>();
        for( RequestCase requestCase :entity){
            requestCaseDto.add(new RequestCaseDto(
                    requestCase.getRequestId(),requestCase.getRescuerId(), requestCase.getLocation(),
                    requestCase.getReason(),requestCase.getCaseType(),requestCase.getUrgencyLevel(),
                    requestCase.getRequestDate(),requestCase.getRequestStatus(),
                    requestCase.getRequestNote(), requestCase.getRequestContact()));
        }
        return requestCaseDto;

    }

    @Override
    public boolean saveRequest(RequestCaseDto requestCaseDto) throws Exception {

            return requestCaseDAO.save(new RequestCase(
                    requestCaseDto.getRequestId(),requestCaseDto.getRescuerId(), requestCaseDto.getLocation(),
                    requestCaseDto.getReason(),requestCaseDto.getCaseType(),requestCaseDto.getUrgencyLevel(),
                    requestCaseDto.getRequestDate(),requestCaseDto.getRequestStatus(),
                    requestCaseDto.getRequestNote(), requestCaseDto.getRequestContact()));
    }

    @Override
    public boolean deleteRequest(String requestId) throws Exception {
            return requestCaseDAO.delete(requestId);
    }

    @Override
    public boolean updateRequest(RequestCaseDto requestCaseDto) throws SQLException, ClassNotFoundException {

            return requestCaseDAO.update(new RequestCase(
                    requestCaseDto.getRequestId(),requestCaseDto.getRescuerId(), requestCaseDto.getLocation(),
                    requestCaseDto.getReason(),requestCaseDto.getCaseType(),requestCaseDto.getUrgencyLevel(),
                    requestCaseDto.getRequestDate(),requestCaseDto.getRequestStatus(),
                    requestCaseDto.getRequestNote(), requestCaseDto.getRequestContact()));
    }

    @Override
    public String getNextRequestId() throws Exception {
        return requestCaseDAO.getNextId();
    }
}
