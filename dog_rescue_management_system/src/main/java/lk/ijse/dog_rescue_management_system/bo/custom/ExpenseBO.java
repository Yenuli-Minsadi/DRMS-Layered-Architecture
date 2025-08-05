package lk.ijse.dog_rescue_management_system.bo.custom;

import lk.ijse.dog_rescue_management_system.bo.SuperBO;
import lk.ijse.dog_rescue_management_system.dto.ExpenseDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExpenseBO extends SuperBO {

    public ArrayList<ExpenseDto> getAllExpenses() throws SQLException, ClassNotFoundException;

    public boolean saveExpense(ExpenseDto expenseDto) throws Exception;

    public boolean updateExpense(ExpenseDto expenseDto) throws SQLException, ClassNotFoundException;

    public boolean deleteExpense(String expenseId) throws Exception;

    public String getNextExpenseId() throws Exception;
}
