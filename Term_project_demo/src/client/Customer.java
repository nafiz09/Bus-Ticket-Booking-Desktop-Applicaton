package client;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Customer {
    private File file;
    private HashMap<String, String> map = new HashMap<>();
    private Scanner sc;

    public Customer (String filename) {
        file = new File("D:\\JavaFX\\Term_project_demo\\Text_Files\\" + filename);
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    public void Load_map() {
        while (sc.hasNext()) {
            map.put(sc.next(), sc.next());
        }
    }

    public boolean check_login(String name, String pass) {
        if(map.containsKey(name) && map.get(name).equals(pass))
            return true;
        return false;
    }

    public boolean checkUsernameValidity (String userName) {
        return map.containsKey(userName);
    }

    public boolean signUP(String[] ar) throws IOException {
        //Load_map();
        if (!checkUsernameValidity(ar[1])) {
            FileWriter fileWriter = new FileWriter("D:\\JavaFX\\Term_project_demo\\Text_Files\\" + ar[0], true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(ar[1]+" "+ar[2]);
            printWriter.close();

            FileWriter fileWriter2 = new FileWriter("D:\\JavaFX\\Term_project_demo\\Text_Files\\" + ar[1] + ".txt", true);
            BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
            PrintWriter printWriter2 = new PrintWriter(bufferedWriter2);
            printWriter2.println(ar[3]);
            printWriter2.println(ar[4]);
            printWriter2.println(ar[5]);
            printWriter2.println(ar[6]);
            printWriter2.println(ar[7]);
            printWriter2.println(ar[8]);
            printWriter2.close();
            return true;
        }
        else
            return false;
    }
}
