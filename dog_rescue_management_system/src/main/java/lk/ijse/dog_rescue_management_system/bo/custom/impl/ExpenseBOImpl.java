package lk.ijse.dog_rescue_management_system.bo.custom.impl;

import lk.ijse.dog_rescue_management_system.bo.custom.ExpenseBO;
import lk.ijse.dog_rescue_management_system.dao.DAOFactory;
import lk.ijse.dog_rescue_management_system.dao.custom.ExpenseDAO;
import lk.ijse.dog_rescue_management_system.dto.ExpenseDto;
import lk.ijse.dog_rescue_management_system.entity.Expense;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExpenseBOImpl implements ExpenseBO {
    ExpenseDAO expenseDAO = (ExpenseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EXPENSE);

    @Override
    public ArrayList<ExpenseDto> getAllExpenses() throws SQLException, ClassNotFoundException {
//        return expenseDAO.getAll();
        ArrayList<Expense>entity=expenseDAO.getAll();
        ArrayList<ExpenseDto>expenseDto=new ArrayList<>();
        for( Expense expense :entity){
            expenseDto.add(new ExpenseDto(
                    expense.getExpenseId(),expense.getDogId(), expense.getReceiptReference(),
                    expense.getPaymentMethod(),expense.getExpenseDescription(),expense.getExpenseDate(),
                    expense.getExpenseAmount(),expense.getExpenseCategory(),expense.getExpenseStatus()));
        }
        return expenseDto;
    }

    @Override
    public boolean saveExpense(ExpenseDto expenseDto) throws Exception {
        return expenseDAO.save(new Expense(
                expenseDto.getExpenseId(),expenseDto.getDogId(), expenseDto.getReceiptReference(),
                expenseDto.getPaymentMethod(),expenseDto.getExpenseDescription(),expenseDto.getExpenseDate(),
                expenseDto.getExpenseAmount(),expenseDto.getExpenseCategory(),expenseDto.getExpenseStatus()));
    }

    @Override
    public boolean updateExpense(ExpenseDto expenseDto) throws SQLException, ClassNotFoundException {
            return expenseDAO.update(new Expense(
                    expenseDto.getExpenseId(),expenseDto.getDogId(), expenseDto.getReceiptReference(),
                    expenseDto.getPaymentMethod(),expenseDto.getExpenseDescription(),expenseDto.getExpenseDate(),
                    expenseDto.getExpenseAmount(),expenseDto.getExpenseCategory(),expenseDto.getExpenseStatus()));
    }

    @Override
    public boolean deleteExpense(String expenseId) throws Exception {

            return expenseDAO.delete(expenseId);
    }

    @Override
    public String getNextExpenseId() throws Exception {
        return expenseDAO.getNextId();
    }
}
