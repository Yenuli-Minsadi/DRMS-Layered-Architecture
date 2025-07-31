package lk.ijse.dog_rescue_management_system.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import lk.ijse.dog_rescue_management_system.bo.custom.BOFactory;
import lk.ijse.dog_rescue_management_system.bo.custom.DonorBO;
import lk.ijse.dog_rescue_management_system.bo.custom.ExpenseBO;
import lk.ijse.dog_rescue_management_system.bo.custom.impl.DonorBOImpl;
import lk.ijse.dog_rescue_management_system.bo.custom.impl.ExpenseBOImpl;
import lk.ijse.dog_rescue_management_system.dao.custom.DonorDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.ExpenseDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.impl.DonorDAOImpl;
import lk.ijse.dog_rescue_management_system.dao.custom.impl.ExpenseDAOImpl;
import lk.ijse.dog_rescue_management_system.dto.DonorDto;
import lk.ijse.dog_rescue_management_system.dto.ExpenseDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DonVsExpController implements Initializable {
//    DonorDAO donorDAO = new DonorDAOImpl();
//    DonorBOImpl donorBO = new DonorBOImpl();
    DonorBO donorBO = (DonorBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.DONOR);
//    ExpenseDAO expenseDAO = new ExpenseDAOImpl();
//    ExpenseBOImpl expenseBO =  new ExpenseBOImpl();
    ExpenseBO expenseBO = (ExpenseBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.EXPENSE);
    @FXML
    private PieChart donationExpensePieChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadDonationExpensePieChart();
    }

    private void loadDonationExpensePieChart() {
        try {
            // Get total donations
            List<DonorDto> donors = new DonorBOImpl().getAllDonors();
            double totalDonations = donors.stream()
                    .mapToDouble(DonorDto::getDonorAmount)
                    .sum();

            // Get total expenses
            List<ExpenseDto> expenses = new ExpenseBOImpl().getAllExpenses();
            double totalExpenses = expenses.stream()
                    .mapToDouble(ExpenseDto::getExpenseAmount)
                    .sum();

            // Prepare PieChart data
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Total Donations", totalDonations),
                    new PieChart.Data("Total Expenses", totalExpenses)
            );

            donationExpensePieChart.setData(pieChartData);
            donationExpensePieChart.setTitle("Donations vs Expenses");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // You can show an Alert here
        }
    }
}
