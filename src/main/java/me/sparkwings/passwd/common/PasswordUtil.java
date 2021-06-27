package me.sparkwings.passwd.common;

import com.lambdaworks.crypto.SCryptUtil;
import me.sparkwings.passwd.Passwd;

import java.util.ArrayList;

public class PasswordUtil {

    public static volatile ArrayList<Passwd> passwordList;

    public PasswordUtil() {
        passwordList = new ArrayList<>();
        System.out.println("[*] Initialized password list in memory.");
    }

    public static String hashPassword(String unhashedPasswd) {
        return SCryptUtil.scrypt(unhashedPasswd, 16, 16, 16);
    }

    public static boolean checkPasswd(String attemptedPasswd, String hashedPassword) {
        return SCryptUtil.check(attemptedPasswd, hashedPassword);
    }

}
