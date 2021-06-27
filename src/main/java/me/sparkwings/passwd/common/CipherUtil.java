package me.sparkwings.passwd.common;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class CipherUtil {

    public static void generateRSAKeypair() throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        KeyPair pair = gen.generateKeyPair();

        PrivateKey pk = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        //Store
        File pubFile = new File(PasswordManager.determineConfigLocation() + "pwdm.pub");
        pubFile.getParentFile().mkdirs();
        if (!pubFile.exists()) {
            pubFile.createNewFile();
        }
        File priFile = new File(PasswordManager.determineConfigLocation() + "pwdm.ppk");
        priFile.getParentFile().mkdirs();
        if (!priFile.exists()) {
            priFile.createNewFile();
        }

        // Public Key
        try (FileOutputStream fos = new FileOutputStream((pubFile))) {
            fos.write(pub.getEncoded());
        }
        // Private Key
        try (FileOutputStream fos = new FileOutputStream((priFile))) {
            fos.write(pk.getEncoded());
        }

        return;
    }

    public static PublicKey getPublicKey() throws Exception {

        File pubKey = new File(PasswordManager.determineConfigLocation() + "pwdm.pub");
        byte[] keyBytes = Files.readAllBytes(pubKey.toPath());

        KeyFactory keyf = KeyFactory.getInstance("RSA");
        EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(keyBytes);
        return keyf.generatePublic(pubKeySpec);
    }

    public static PrivateKey getPrivateKey() throws Exception {

        File priKey = new File(PasswordManager.determineConfigLocation() + "pwdm.ppk");
        byte[] keyBytes = Files.readAllBytes(priKey.toPath());

        KeyFactory keyf = KeyFactory.getInstance("RSA");
        EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        return keyf.generatePrivate(priKeySpec);
    }

    public static void encrypt(File file) throws Exception {
        Path path = file.toPath();
        byte[] fileBytes = Files.readAllBytes(path);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
        byte[] newFileBytes = cipher.doFinal(fileBytes);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(newFileBytes);
        }

    }

    public static void decrypt(File file) throws Exception {
        Path path = file.toPath();
        byte[] fileBytes = Files.readAllBytes(path);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
        byte[] newFileBytes = cipher.doFinal(fileBytes);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(newFileBytes);
        }

    }

}
