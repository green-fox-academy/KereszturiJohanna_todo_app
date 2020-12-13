package com.AviusTattoo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path toDoPath = FileHandler.createFiles();
        readArg(args, toDoPath);
    }

    public static void readArg(String[] arg, Path toDoPath) {
        if (arg.length == 0) {
            FileHandler.readManual();
        } else {
            List<ToDoList> toDos = new ArrayList<>();
            List<String> toDoLines = new ArrayList<>();
            try {
                toDoLines = createToDoList();
                for (int i = 0; i < toDoLines.size(); i++) {
                    toDos.add(new ToDoList(toDoLines.get(i)));
                }
            } catch (IOException e) {
                System.out.println("ToDo list cannot be found");
            }
            if (arg[0].equals("-l")) {
                ArgumentsHandler.taskLister(toDos);

            } else if (arg[0].equals("-a")) {
                if (arg.length > 1) {
                    String str = arg[1].concat(";");
                    toDos = ArgumentsHandler.addToDo(str, toDos, toDoLines, toDoPath);
                } else {
                    System.out.println("Nem lehetséges új feladat hozzáadása:");
                    System.out.println("nincs megadva feladat!");
                }
            } else if (arg[0].equals("-r") || (arg[0].equals("-c"))) {
                if (arg.length > 1) {
                    String str = arg[1];
                    if ((arg[0].equals("-r"))) {
                        toDos = ArgumentsHandler.removeToDo(str, toDos, toDoLines, toDoPath);
                    }
                    if((arg[0].equals("-c"))){
                        toDos = ToDoList.finishTask(str, toDos, toDoLines, toDoPath);
                    }
                } else {
                    System.out.println("Nem lehetséges az eltávolítás:");
                    System.out.println("nem adott meg indexet!");
                }
            } else {
                System.out.println("nem támogatott argumentum!");
                System.out.println();
                FileHandler.readManual();
            }
        }
    }

    public static List<String> createToDoList() throws IOException {
        Path toDoList = Paths.get("toDoList.txt");
        return Files.readAllLines(toDoList);
    }


}
