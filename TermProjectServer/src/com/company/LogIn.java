package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class LogIn {
    private File file;
    private HashMap<String, String> map = new HashMap<>();
    private Scanner sc;
    String[] attribute;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;


    public LogIn (String[] attribute, DataInputStream dataInputStream,
            DataOutputStream dataOutputStream) {

        this.attribute = attribute;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;

        file = new File(attribute[1]);
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    public void loadMap() {
        while (sc.hasNext()) {
            map.put(sc.next(), sc.next());
        }
    }

    public void logIn()throws IOException {
        loadMap();
        if (map.containsKey(attribute[2]) && map.get(attribute[2]).equals(attribute[3])) {
            dataOutputStream.writeUTF("success");
        }
        else {
            dataOutputStream.writeUTF("failed");
        }
        dataOutputStream.flush();
    }
}
