package lk.ijse.dog_rescue_management_system.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.dog_rescue_management_system.bo.BOFactory;
import lk.ijse.dog_rescue_management_system.bo.custom.MedicalRecordBO;
import lk.ijse.dog_rescue_management_system.bo.custom.QueryBO;
import lk.ijse.dog_rescue_management_system.dao.custom.AdoptionProcessDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.AppointmentDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.DogDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.impl.AdoptionProcessDAOImpl;
import lk.ijse.dog_rescue_management_system.dao.custom.impl.AppointmentDAOImpl;
import lk.ijse.dog_rescue_management_system.dao.custom.impl.DogDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {


    @FXML
    private Label lblTotScheduledAppt;

    @FXML
    private Label lblAdoptedDogs;

    @FXML
    private Label lblDogCount;

//    DogRegisterModel dogRegisterModel = new DogRegisterModel();
//    DogDAO dogDAO = new DogDAOImpl();

//    private final AdoptionModel adoptionProcessModel = new AdoptionModel();
//    AdoptionProcessDAO adoptionProcessDAO = new AdoptionProcessDAOImpl();
    QueryBO queryBO = (QueryBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.QUERY);
//    private final AppointmentModel appointmentModel = new AppointmentModel();
//    AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDogCount();
        loadAdoptedDogCount();
        loadScheduledAppointmentCount();

    }

    private void loadDogCount() {
        try {
            int count = queryBO.getDogCount();
            lblDogCount.setText(String.valueOf(count));
        } catch (SQLException | ClassNotFoundException e) {
            lblDogCount.setText("Error");
            e.printStackTrace();
        }
    }

    private void loadAdoptedDogCount() {
        try {
            int count = queryBO.getAdoptedDogCount();
            lblAdoptedDogs.setText(String.valueOf(count));
        } catch (SQLException | ClassNotFoundException e) {
            lblAdoptedDogs.setText("Error");
            e.printStackTrace();
        }
    }

    private void loadScheduledAppointmentCount() {
        try {
            int count = queryBO.getScheduledAppointmentCount();
            lblTotScheduledAppt.setText(String.valueOf(count));
        } catch (SQLException | ClassNotFoundException e) {
            lblTotScheduledAppt.setText("Error");
            e.printStackTrace();
        }
    }


}
