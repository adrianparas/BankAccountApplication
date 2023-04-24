package com.snva;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserialization {
    public static void deserialize(String filename) {
        try {
            BankAccount bankAccount = null;
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            bankAccount = (BankAccount) objectInputStream.readObject();
            System.out.println(bankAccount);
            objectInputStream.close();
            fileInputStream.close();
        } catch(IOException | ClassNotFoundException exception) {
            System.out.println(exception);
        }

    }
}
