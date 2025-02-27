package view;

import controller.CreateStaffController;
import controller.EditStaffController;
import model.Employee;
import service.StaffService;
import util.Role;
import view.View;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static util.CreateStaffViewUtil.fillComboBoxRolesOptions;

public class EditStaffView extends View {
    private static final StaffService staffService;

    static {
        staffService = new StaffService();
    }

    private AnchorPane anchorPane;
    private Button back;
    private TextField name;
    private TextField username;
    private PasswordField password;
    private TextField phone;
    private TextField email;
    private DatePicker birthday;
    private TextField salary;
    private ComboBox roles;
    private Button update;
    private Button delete;
    private final String id;
    private final Employee employee;

    public EditStaffView(String id) {
        this.id = id;
        this.employee = staffService.findById(id);

        anchorPane = new AnchorPane();
        back = new Button();
        name = new TextField();
        username = new TextField();
        password = new PasswordField();
        phone = new TextField();
        email = new TextField();
        birthday = new DatePicker();
        salary = new TextField();
        roles = new ComboBox();
        update = new Button();
        delete = new Button();

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);

        back.setOnAction(CreateStaffController::back);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        name.setAlignment(javafx.geometry.Pos.CENTER);
        name.setLayoutX(100.0);
        name.setLayoutY(115.0);
        name.setPrefWidth(300.0);
        name.setPromptText("Name");
        name.setText(employee.getName());

        username.setAlignment(javafx.geometry.Pos.CENTER);
        username.setLayoutX(100.0);
        username.setLayoutY(180.0);
        username.setPrefWidth(300.0);
        username.setPromptText("Username");
        username.setText(employee.getUsername());

        password.setAlignment(javafx.geometry.Pos.CENTER);
        password.setLayoutX(600.0);
        password.setLayoutY(180.0);
        password.setPrefWidth(300.0);
        password.setPromptText("Password");
        password.setText(employee.getPassword());

        phone.setAlignment(javafx.geometry.Pos.CENTER);
        phone.setLayoutX(100.0);
        phone.setLayoutY(255.0);
        phone.setPrefWidth(300.0);
        phone.setPromptText("Phone");
        phone.setText(employee.getPhone());

        email.setAlignment(javafx.geometry.Pos.CENTER);
        email.setLayoutX(600.0);
        email.setLayoutY(255.0);
        email.setPrefWidth(300.0);
        email.setPromptText("Email");
        email.setText(employee.getEmail());

        birthday.setLayoutX(100.0);
        birthday.setLayoutY(330.0);
        birthday.setPrefWidth(300.0);
        birthday.setPromptText("birthday");
        birthday.setValue(employee.getBirthday());

        salary.setAlignment(javafx.geometry.Pos.CENTER);
        salary.setLayoutX(600.0);
        salary.setLayoutY(330.0);
        salary.setPrefWidth(300.0);
        salary.setPromptText("Salary");
        salary.setText(String.valueOf(employee.getSalary()));

        update.setLayoutX(400.0);
        update.setLayoutY(450.0);
        update.setOnAction((ActionEvent actionEvent) -> EditStaffController.update(actionEvent, this.id));
        update.setPrefHeight(40.0);
        update.setPrefWidth(200.0);
        update.getStyleClass().add("button-secondary");
        update.setText("Update");

        delete.setLayoutX(400.0);
        delete.setLayoutY(525.0);
        delete.setOnAction((ActionEvent actionEvent) -> EditStaffController.delete(actionEvent, this.id));
        delete.setPrefHeight(40.0);
        delete.setPrefWidth(200.0);
        delete.getStyleClass().add("button-danger");
        delete.setText("Delete");

        roles.setLayoutX(600.0);
        roles.setLayoutY(115.0);
        roles.setPrefWidth(300.0);
        roles.setPromptText("Role");
        fillComboBoxRolesOptions(roles);
        roles.setValue(employee.getRole());
        roles.valueProperty().addListener((ChangeListener<Role>) (observableValue, oldValue, newValue) -> {
            if (newValue == Role.MANAGER || newValue == Role.CASHIER) {
                if (!anchorPane.getChildren().contains(phone)) {
                    anchorPane.getChildren().add(phone);
                    anchorPane.getChildren().add(email);
                    anchorPane.getChildren().add(birthday);
                    anchorPane.getChildren().add(salary);
                }
            } else {
                anchorPane.getChildren().remove(phone);
                anchorPane.getChildren().remove(email);
                anchorPane.getChildren().remove(birthday);
                anchorPane.getChildren().remove(salary);
            }

            phone.setText("");
            email.setText("");
            birthday.setValue(null);
            salary.setText("");
        });

        anchorPane.getChildren().add(phone);
        anchorPane.getChildren().add(email);
        anchorPane.getChildren().add(birthday);
        anchorPane.getChildren().add(salary);
        anchorPane.getChildren().add(back);
        anchorPane.getChildren().add(name);
        anchorPane.getChildren().add(username);
        anchorPane.getChildren().add(password);
        anchorPane.getChildren().add(update);
        anchorPane.getChildren().add(delete);
        anchorPane.getChildren().add(roles);

        getChildren().add(anchorPane);
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }

    public ComboBox getRoles() {
        return roles;
    }

    public void setRoles(ComboBox roles) {
        this.roles = roles;
    }

    public TextField getPhone() {
        return phone;
    }

    public void setPhone(TextField phone) {
        this.phone = phone;
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public DatePicker getBirthday() {
        return birthday;
    }

    public void setBirthday(DatePicker birthday) {
        this.birthday = birthday;
    }

    public TextField getSalary() {
        return salary;
    }

    public void setSalary(TextField salary) {
        this.salary = salary;
    }
}
