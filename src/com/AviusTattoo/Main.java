package com.AviusTattoo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        createFiles();
        readArg(args);
    }

    public static void readArg(String[] arg) {
        if (arg.length == 0) {
            Path manual = Paths.get("userManual.txt");
            try {
                List<String> manualLines = Files.readAllLines(manual);
                for (String manualLine : manualLines) {
                    System.out.println(manualLine);
                }
            } catch (IOException e) {
                System.out.println("UserManual cannot be found :/");
            }
        } else {
            if (arg[0].equals("-l")) {
                Path toDoList = Paths.get("toDoList.txt");
                try {
                    List<String> toDos = Files.readAllLines(toDoList);
                    if (toDos.size() > 0) {
                        for(int i = 0; i< toDos.size(); i++) {
                            System.out.println(i+1 + " - " + toDos.get(i));
                        }
                    } else {
                        System.out.println("Nincs mára tennivalód! :)");
                    }
                } catch (IOException e) {
                    System.out.println("ToDo list cannot be found");
                }
            }
        }
    }

    public static void createFiles() {
        File userManual = new File("userManual.txt");
        File toDoList = new File("toDoList.txt");
        if (!userManual.exists()) {

            ArrayList<String> manualLines = new ArrayList<>(Arrays.asList("Parancssori Todo applikáció",
                    "=============================", " ", "Parancssori argumentumok:", "    -l   Kilistázza a feladatokat",
                    "    -a   Új feladatot ad hozzá", "    -r   Eltávolít egy feladatot", "    -c   Teljesít egy feladatot"));

            Path manual = Paths.get("userManual.txt");
            try {
                Files.write(manual, manualLines);
            } catch (IOException e) {
                System.out.println("UserManual cannot be found :/");
            }
        }

        try {
            toDoList.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't create new file: toDoList.txt");
        }
    }

}