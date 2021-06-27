package me.sparkwings.passwd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.sparkwings.passwd.common.PasswordManager;

/**
 * TODO: Implement UI
 * TODO: Password Generation Functionality
 * TODO: Random Location Password Storage
 */
public class Dashboard extends Application {

    private static final String HOME_PATH = System.getProperty("user.home");

    public static void main(String[] args) {
        try {
            final PasswordManager passwordUtil = new PasswordManager();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * launch(args);
         */

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
