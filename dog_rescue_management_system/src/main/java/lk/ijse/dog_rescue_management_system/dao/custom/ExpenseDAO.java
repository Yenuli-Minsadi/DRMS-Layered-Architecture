package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.dao.CrudDAO;
import lk.ijse.dog_rescue_management_system.entity.Expense;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExpenseDAO extends CrudDAO<Expense> {

    public ArrayList<Expense> getAll() throws SQLException, ClassNotFoundException;

    public boolean save(Expense expenseDto) throws Exception;

    public boolean update(Expense expenseDto) throws SQLException, ClassNotFoundException;

    public boolean delete(String expenseId) throws Exception;

    public String getNextId() throws Exception;
}
