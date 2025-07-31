package lk.ijse.dog_rescue_management_system.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.dog_rescue_management_system.dao.custom.AppointmentDAO;
import lk.ijse.dog_rescue_management_system.dao.custom.impl.AppointmentDAOImpl;
import lk.ijse.dog_rescue_management_system.dto.AppointmentDto;

import lk.ijse.dog_rescue_management_system.entity.Appointment;
import lk.ijse.dog_rescue_management_system.view.tdm.AppointmentTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VetAppointmentController implements Initializable {

    private AppointmentTM selectedAppointment;



    @FXML
    private ComboBox<String> comboStatus;

    @FXML
    private TableView<?> tblVetAppt;

    @FXML
    private TextField txtVetId;

    @FXML
    void btnMyApptOnAction(ActionEvent event) {
        String vetId = txtVetId.getText().trim();

        if (vetId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a Vet ID to search.").show();
            return;
        }

        try {
            ArrayList<Appointment> allAppointments = appointmentDAO.getAll();
            ObservableList<AppointmentTM> filteredAppointments = FXCollections.observableArrayList();

            for (Appointment entity : allAppointments) {
                if (entity.getVetId().equalsIgnoreCase(vetId)) {
                    filteredAppointments.add(new AppointmentTM(
                            entity.getAppointmentId(),
                            entity.getDogId(),
                            entity.getVetId(),
                            entity.getAppointmentDate(),
                            entity.getAppointmentReason(),
                            entity.getAppointmentStatus()
                    ));
                }
            }

            if (filteredAppointments.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "No appointments found for Vet ID: " + vetId).show();
            }

            tblAppt.setItems(filteredAppointments);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load appointments: " + e.getMessage()).show();
        }
    }


    public TableView<AppointmentTM> tblAppt;
    public TableColumn<AppointmentTM, String> colApptId;
    public TableColumn<AppointmentTM, String> colDogId;
    public TableColumn<AppointmentTM, String> colVetId;
    public TableColumn<AppointmentTM, String> colApptDate;
    public TableColumn<AppointmentTM, String> colReason;
    public TableColumn<AppointmentTM, String> colStatus;


//    private final AppointmentModel appointmentModel = new AppointmentModel();
    AppointmentDAO appointmentDAO = new AppointmentDAOImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // table column and tm class properties link
        colApptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colDogId.setCellValueFactory(new PropertyValueFactory<>("dogId"));
        colVetId.setCellValueFactory(new PropertyValueFactory<>("vetId"));
        colApptDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("appointmentReason"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("appointmentStatus"));

        try {
            resetPage();

            comboStatus.getItems().addAll("Scheduled", "In Progress", "Completed");

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }


    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<Appointment> appointmentDtoArrayList = appointmentDAO.getAll();
        ObservableList<AppointmentTM> appointmentTMS = FXCollections.observableArrayList();

        for (Appointment entity : appointmentDtoArrayList) {
            AppointmentTM appointmentTM = new AppointmentTM(
                    entity.getAppointmentId(),
                    entity.getDogId(),
                    entity.getVetId(),
                    entity.getAppointmentDate(),
                    entity.getAppointmentReason(),
                    entity.getAppointmentStatus()
            );
            appointmentTMS.add(appointmentTM);
        }
        tblAppt.setItems(appointmentTMS);
    }

    private void resetPage() {
        try {
            loadTableData();
            txtVetId.setText("");
            comboStatus.setValue(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }


    public void btnUpdateStatusOnAction(ActionEvent actionEvent) {
        if (selectedAppointment == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an appointment from the table.").show();
            return;
        }

        String newStatus = comboStatus.getValue();
        if (newStatus == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a new status.").show();
            return;
        }

        Appointment updatedDto = new Appointment(
                selectedAppointment.getAppointmentId(),
                selectedAppointment.getDogId(),
                selectedAppointment.getVetId(),
                selectedAppointment.getAppointmentDate(),
                selectedAppointment.getAppointmentReason(),
                newStatus // updated status
        );

        try {
            boolean isUpdated = appointmentDAO.update(updatedDto);
            if (isUpdated) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Appointment status updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update appointment status.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }



    public void btnApptResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }


    public void onClickTable(MouseEvent mouseEvent) {
        AppointmentTM selectedRequest = tblAppt.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {
            txtVetId.setText(selectedRequest.getVetId());
            comboStatus.setValue(selectedRequest.getAppointmentStatus());

            // Store appointmentTM somewhere accessible
            selectedAppointment = selectedRequest;
        }

    }

}
