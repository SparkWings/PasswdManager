package me.sparkwings.passwd;

import com.lambdaworks.crypto.SCryptUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import me.sparkwings.passwd.common.JSONUtil;
import me.sparkwings.passwd.common.PasswordUtil;

import java.io.IOException;

public class Dashboard extends Application {

    private static final String HOME_PATH = System.getProperty("user.home");

    public static void main(String[] args) {

        //Initialize password util class
        final PasswordUtil passwordUtil = new PasswordUtil();
        JSONUtil.readPasswordsFromFile("C:\\Users\\jerem\\IdeaProjects\\PasswdManager\\test.json");

        launch(args);



        
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
