package me.sparkwings.passwd.common;

import me.sparkwings.passwd.Passwd;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class JSONUtil {

    public static ArrayList<Passwd> readPasswordsFromFile(String filePath) throws Exception {
        ArrayList<Passwd> returnList = new ArrayList<>();
        File file = new File(filePath);
        CipherUtil.decrypt(file);
        JSONParser parser = new JSONParser();

        try {
            Object o = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) o;
            JSONArray jsonArray = (JSONArray) jsonObject.get("Passwords");

            for (JSONObject nextObject : (Iterable<JSONObject>) jsonArray) {
                String label = (String) nextObject.get("label");
                String value = (String) nextObject.get("value");
                Passwd pass = new Passwd(label, value);
                returnList.add(pass);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CipherUtil.encrypt(file);
            return returnList;
        }
    }

    public static String getConfigLocation() {


        return "";
    }


}
