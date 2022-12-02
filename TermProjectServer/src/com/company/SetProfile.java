package com.company;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SetProfile {

    Socket socket;
    File file;
    DataOutputStream dataOutputStream;
    Scanner sc;

    public SetProfile(Socket socket, String filename) throws IOException {
        this.socket = socket;
        file = new File(filename);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        sc = new Scanner(file);
    }

    public void action() throws IOException {
        String str = "";
        sc.useDelimiter("\n");
        while(sc.hasNext())
            str+= sc.next() +"#";
        dataOutputStream.writeUTF(str);
    }
}
