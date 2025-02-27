package util;

import util.Role;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreateStaffViewUtil {
    public static void fillComboBoxRolesOptions(ComboBox roles) {
        roles.getItems().addAll(Arrays.asList(Role.values()));
    }
}
