package com.AviusTattoo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path toDoPath = createFiles();
        readArg(args, toDoPath);
    }

    public static void readArg(String[] arg, Path toDoPath) {
        if (arg.length == 0) {
            readManual();
        } else {
            List<String> toDos = new ArrayList<>();
            try {
                toDos = createToDoList();
            } catch (IOException e) {
                System.out.println("ToDo list cannot be found");
            }
            if (arg[0].equals("-l")) {
               ArgumentsHandler.taskLister(toDos);

            } else if(arg[0].equals("-a")){
                if(arg.length > 1){
                    String str = arg[1];
                   toDos = ArgumentsHandler.addToDo(str, toDos, toDoPath);
                }else{
                    System.out.println("Nem lehetséges új feladat hozzáadása:");
                    System.out.println("nincs megadva feladat!");
                }
            }else if(arg[0].equals("-r")){
                if(arg.length>1){
                    String str = arg[1];
                    toDos = ArgumentsHandler.removeToDo(str, toDos, toDoPath);
                }else{
                    System.out.println("Nem lehetséges az eltávolítás:");
                    System.out.println("nem adott meg indexet!");
                }
            }else if(arg[0].equals("-c")){
                System.out.println("Ez a funkció jelenleg nem elérhető :(");
            }else{
                System.out.println("nem támogatott argumentum!");
                System.out.println();
                readManual();
            }
        }
    }

    public static List<String> createToDoList() throws IOException {
        Path toDoList = Paths.get("toDoList.txt");
        return Files.readAllLines(toDoList);
    }

    public static Path createFiles() {
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

}
