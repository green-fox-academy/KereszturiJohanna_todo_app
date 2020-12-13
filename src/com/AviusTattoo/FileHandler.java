package com.AviusTattoo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    public static Path createFiles() {
        File userManual = new File("userManual.txt");
        File toDoList = new File("toDoList.txt");
        if (!userManual.exists()) {

            ArrayList<String> manualLines = new ArrayList<>(Arrays.asList("Parancssori Todo applikáció",
                    "=============================", " ", "Parancssori argumentumok:", "    -l   Kilistázza a feladatokat",
                    "    -a   Új feladatot ad hozzá", "    -r   Eltávolít egy feladatot", "    -c   Teljesít egy feladatot"));

            Path manual = Paths.get("userManual.txt");
            fileWriter(manualLines, manual, "UserManual cannot be found :/");
        }

        try {
            toDoList.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't create new file: toDoList.txt");
        }
        return Paths.get("toDoList.txt");
    }

    public static void readManual(){
        Path manual = Paths.get("userManual.txt");
        try {
            List<String> manualLines = Files.readAllLines(manual);
            for (String manualLine : manualLines) {
                System.out.println(manualLine);
            }
        } catch (IOException e) {
            System.out.println("UserManual cannot be found :/");
        }
    }

    static void fileWriter(List<String> list, Path path, String errorMsg) {
        try {
            Files.write(path, list);
        } catch (IOException e) {
            System.out.println(errorMsg);
        }
    }
}
