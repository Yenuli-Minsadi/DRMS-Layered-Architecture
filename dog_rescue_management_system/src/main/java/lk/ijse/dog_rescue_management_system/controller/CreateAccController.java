package lk.ijse.dog_rescue_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.dog_rescue_management_system.db.DBConnection;
import lk.ijse.dog_rescue_management_system.dto.UserDto;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class CreateAccController implements Initializable {


    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private ComboBox<String> comboRole;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnSignUpOnClickAction(ActionEvent event) {

        try {
            String name = txtName.getText();
            String email = txtEmail.getText();
            String password = txtPassword.getText();
            String role = comboRole.getValue().toLowerCase();
            String contact = txtContact.getText();
            String address = txtAddress.getText();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty() || contact.isEmpty() || address.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "All fields are required to be filled!").show();
                return;
            }

            String userId = generateUserId();
            UserDto createNewUser = new UserDto(userId,name,email,password,contact,role,address);
            boolean isSaved = signUp(createNewUser);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Welcome!").show();
                Parent parent = FXMLLoader.load(getClass().getResource("/view/LogIn.fxml"));
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong. Please try again!").show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateUserId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT user_id FROM Users ORDER BY user_id DESC LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        var resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // U003
            int id = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("U%03d", id);
        } else {
            return "U001";
        }
    }


    private boolean signUp(UserDto user) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        // write a SQl
        String sql ="INSERT INTO Users VALUES (?,?,?,?,?,?,?,?)";
        System.out.println(connection);
        // INSERT INTO user VALUES('hiruna@gmail.com','Hiruna','Sankalpa','1234');
        // create statement
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUserId());
        statement.setString(2, user.getUserName());
        statement.setString(3, user.getUserEmail());
        statement.setString(4, user.getUserPassword());
        statement.setString(5, user.getUserContact());
        statement.setString(6, user.getUserRole());
        statement.setString(7, user.getUserAddress());
        statement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
        // set sql into the statement and execute
        return statement.executeUpdate()>0;
    }
    @FXML
    void btnLoginOnClickAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/LogIn.fxml"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            comboRole.getItems().addAll("admin","vet");
        } catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }
}
