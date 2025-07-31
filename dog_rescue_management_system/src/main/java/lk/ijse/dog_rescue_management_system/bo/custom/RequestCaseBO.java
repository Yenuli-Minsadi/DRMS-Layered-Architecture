package lk.ijse.dog_rescue_management_system.bo.custom;

import lk.ijse.dog_rescue_management_system.dto.RequestCaseDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RequestCaseBO extends SuperBO {

    public ArrayList<RequestCaseDto> getAllRequests() throws SQLException, ClassNotFoundException;
    public boolean saveRequest(RequestCaseDto requestCaseDto) throws Exception;
    public boolean deleteRequest(String requestId) throws Exception;
    public boolean updateRequest(RequestCaseDto requestCaseDto) throws SQLException, ClassNotFoundException;
    public String getNextRequestId() throws Exception;
}
