package lk.ijse.dog_rescue_management_system.dao.custom.impl;

import lk.ijse.dog_rescue_management_system.dao.custom.ExpenseDAO;
import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.entity.Expense;
import lk.ijse.dog_rescue_management_system.dao.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExpenseDAOImpl implements ExpenseDAO {

    @Override
    public ArrayList<Expense> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from expense");

        ArrayList<Expense> entity = new ArrayList<>();
        while (resultSet.next()) {
//            ExpenseDto expenseDto = new ExpenseDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    resultSet.getDate(6).toLocalDate(), // Convert java.sql.Date to LocalDate
//                    resultSet.getDouble(7),
//                    resultSet.getString(8),
//                    resultSet.getString(9)
//            );
            entity.add(new Expense(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDate(6).toLocalDate(), // Convert java.sql.Date to LocalDate
                    resultSet.getDouble(7),
                    resultSet.getString(8),
                    resultSet.getString(9)));
        }
        return entity;
    }

    @Override
    public boolean save(Expense entity) throws Exception {

            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "INSERT INTO expense (exp_id, dog_id, receipt_reference, payment_method, exp_description, exp_date, exp_amount, exp_category, expense_status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, entity.getExpenseId());
            pst.setString(2, entity.getDogId());
            pst.setString(3, entity.getReceiptReference());
            pst.setString(4, entity.getPaymentMethod());
            pst.setString(5, entity.getExpenseDescription());
            pst.setString(6, String.valueOf(entity.getExpenseDate()));
            pst.setString(7, String.valueOf(entity.getExpenseAmount()));
            pst.setString(8, entity.getExpenseCategory());
            pst.setString(9, entity.getExpenseStatus());

            int i = pst.executeUpdate();
            boolean isSaved = i > 0;
            return isSaved;
        }

    @Override
    public boolean update(Expense entity) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute(
                    "update expense set dog_id=?, receipt_reference=?, payment_method=?, exp_description = ?, exp_date = ?, exp_amount = ?, exp_category = ?, expense_status = ? where exp_id=?",
                    entity.getDogId(),
                    entity.getReceiptReference(),
                    entity.getPaymentMethod(),
                    entity.getExpenseDescription(),
                    entity.getExpenseDate(),
                    entity.getExpenseAmount(),
                    entity.getExpenseCategory(),
                    entity.getExpenseStatus(),
                    entity.getExpenseId()
            );
    }

    @Override
    public boolean delete(String expenseId) throws Exception {

            return CrudUtil.execute(
                    "delete from expense where exp_id=?",
                    expenseId
            );
    }

    @Override
    public String getNextId() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pst = connection.prepareStatement(
                "select exp_id from expense order by exp_id desc limit 1"
        );

        ResultSet resultSet = pst.executeQuery();

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1); // skip "DNT"
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNUmber = lastIdNumber + 1;
            String nextIdString = String.format("E%03d", nextIdNUmber);

            return nextIdString;
        }
        return "E001";
    }
}
