package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AddBus {
    String[] attribute;
    String str;
    public AddBus(String input, String[] attribute) {
        str = input.substring(7);
        this.attribute = attribute;
    }

    public void action() throws Exception{
        addToStartPlaces();
        addToEndPlaces();
        addtoVehicleList();
    }

    public void addToStartPlaces() throws IOException {
        FileWriter fileWriter = new FileWriter("startplaces.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        printWriter.println(attribute[1]);
        printWriter.close();

    }

    public void addToEndPlaces() throws IOException {
        FileWriter fileWriter = new FileWriter(attribute[1]+".txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        printWriter.println(attribute[2]);
        printWriter.close();

    }

    public void addtoVehicleList() throws IOException{
        FileWriter fileWriter = new FileWriter("saturday.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        printWriter.println(str);
        printWriter.close();

    }
}
