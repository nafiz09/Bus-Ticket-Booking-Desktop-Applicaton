package com.company;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class SignUp {

    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    ObjectInputStream objectInputStream;

    public SignUp(Socket socket) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void action() throws IOException, ClassNotFoundException{

        HashMap<String, String> userData = (HashMap<String, String>) objectInputStream.readObject();

        if (checkValidity(userData.get("filename"), userData.get("username"))){
            FileWriter fileWriter = new FileWriter(userData.get("filename"), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(userData.get("username")+" "+userData.get("password"));
            printWriter.close();

            FileWriter fileWriter2 = new FileWriter(userData.get("username") + ".txt", true);
            BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
            PrintWriter printWriter2 = new PrintWriter(bufferedWriter2);
            printWriter2.println(userData.get("name"));
            printWriter2.println(userData.get("number"));
            printWriter2.println(userData.get("mail"));
            printWriter2.println(userData.get("age"));
            printWriter2.println(userData.get("add"));
            printWriter2.println(userData.get("gender"));
            printWriter2.close();

            dataOutputStream.writeUTF("true");
        }
        else dataOutputStream.writeUTF("false");
        dataOutputStream.flush();
    }

    private boolean checkValidity(String filename, String username) throws IOException{

        File file;
        HashMap<String, String> map = new HashMap<>();
        Scanner sc;
        file = new File(filename);
        sc = new Scanner(file);

        while (sc.hasNext()) {
            map.put(sc.next(), sc.next());
        }

        return !map.containsKey(username);
    }
}
