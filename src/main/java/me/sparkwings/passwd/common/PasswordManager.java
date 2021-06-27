package me.sparkwings.passwd.common;

import com.lambdaworks.crypto.SCryptUtil;
import me.sparkwings.passwd.Passwd;

import javax.crypto.Cipher;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordManager {

    public static String CONFIG_PATH = "";
    public static String DATA_PATH = "";
    private static final String USER_HOME = System.getProperty("user.home");
    public static volatile ArrayList<Passwd> passwordList;

    public PasswordManager() throws Exception {
        passwordList = new ArrayList<>();

        File configDir = new File(determineConfigLocation());
        File dataDir = new File(DATA_PATH);
        File[] listOfFiles = dataDir.listFiles();

        // If a file exists, parse into memory
        if (listOfFiles.length > 0) {
            File passFile = listOfFiles[0];
            JSONUtil.readPasswordsFromFile(passFile.getAbsolutePath());
        }
        // Otherwise, generate new file with random UUID and encrypt it
        else {
            File newPassFile = new File(DATA_PATH + UUID.randomUUID().toString() + ".json");
            CipherUtil.encrypt(newPassFile);
        }

        System.out.println("[*] Initialized password list in memory.");
    }

    public static String hashUnlockPassword(String unhashedPasswd) {
        return SCryptUtil.scrypt(unhashedPasswd, 16384, 16, 16);
    }

    public static boolean checkUnlockPasswd(String attemptedPasswd, String hashedPassword) {
        return SCryptUtil.check(attemptedPasswd, hashedPassword);
    }

    public static String determineConfigLocation() {
        switch (System.getProperty("os.name").split(" ")[0]) {
            // Windows
            case "Windows":
                CONFIG_PATH = USER_HOME + "\\AppData\\Local\\PasswdMgr\\";
                DATA_PATH = CONFIG_PATH + "Data\\";
                return CONFIG_PATH;
            // MAC OS
            case "Mac":
                CONFIG_PATH = "~/Library/Application Support/PasswdMgr/";
                DATA_PATH = CONFIG_PATH + "data/";
                return CONFIG_PATH;
            // All other (most likely Linux) OS types
            default:
                CONFIG_PATH = "/etc/passwdmgr/";
                DATA_PATH = CONFIG_PATH + "data/";
                return CONFIG_PATH;
        }
    }

}
