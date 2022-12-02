package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{

    Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            action();
        }catch (Exception e) {

        }
    }

    private void action() throws IOException, ClassNotFoundException{
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        while(!socket.isClosed()) {
            String input = dataInputStream.readUTF();
            String[] attribute = input.split("#");
            System.out.println(attribute[0]);
            if (attribute[0].equals("login")){

                LogIn logIn = new LogIn(attribute, dataInputStream, dataOutputStream);
                logIn.logIn();
            }
            if (attribute[0].equals("signup")){
                SignUp signUp = new SignUp(socket);
                signUp.action();
            }
            if (attribute[0].equals("setprofile")) {
                SetProfile setProfile = new SetProfile(socket, attribute[1]);
                setProfile.action();
            }
            if (attribute[0].equals("loadcombo1")) {
                File file = new File("startplaces.txt");
                Scanner sc = new Scanner(file);
                String str = "";
                while (sc.hasNext()) {
                    str += sc.next()+"#";
                }
                dataOutputStream.writeUTF(str);
            }
            if (attribute[0].equals("loadcombo2")) {
                File file = new File(attribute[1]+".txt");
                Scanner sc = new Scanner(file);
                String str = "";
                while (sc.hasNext()) {
                    str += sc.next()+"#";
                }
                dataOutputStream.writeUTF(str);
            }
            if (attribute[0].equals("search")) {
                File file = new File(attribute[1]+".txt");
                Scanner sc = new Scanner(file);
                sc.useDelimiter("\n");
                String str;
                while (sc.hasNext()){
                    str = sc.next();
                    if (str.contains(attribute[2]+"#"+attribute[3]))
                        dataOutputStream.writeUTF(str);
                }
                dataOutputStream.writeUTF("end");
            }
            if (attribute[0].equals("addbus")) {
                AddBus addBus = new AddBus(input, attribute);
                try {
                    addBus.action();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
