package me.sparkwings.passwd.common;

import javafx.collections.ObservableList;
import me.sparkwings.passwd.Passwd;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class JSONUtil {

    static ArrayList<Passwd> passwordList = new ArrayList<>();

    public static void readPasswordsFromFile(String filePath) {

        JSONParser parser = new JSONParser();

        try {
            Object o = parser.parse(new FileReader("C:/Users/jerem/Desktop/test.json"));

            JSONObject jsonObject = (JSONObject) o;
            JSONArray jsonArray = (JSONArray) jsonObject.get("Passwords");

            for (JSONObject nextObject : (Iterable<JSONObject>) jsonArray) {
                String label = (String) nextObject.get("label");
                String value = (String) nextObject.get("value");

                Passwd pass = new Passwd(label, value);
                PasswordUtil.passwordList.add(pass);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            //TODO: Test case. Remove before release
            PasswordUtil.passwordList.forEach(password -> {
                System.out.println(password.getLabel() + ":" + password.getContent());
                System.out.println(PasswordUtil.hashPassword(password.getContent()));
                    });

        }

    }


}
