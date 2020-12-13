package com.AviusTattoo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ArgumentsHandler {

    public static void taskLister(List<String> toDos) {
        if (toDos.size() > 0) {
            for (int i = 0; i < toDos.size(); i++) {
                System.out.println(i + 1 + " - " + toDos.get(i));
            }
        } else {
            System.out.println("Nincs mára tennivalód! :)");
        }
    }

    public static List<String> addToDo(String newToDo, List<String> toDos, Path toDoPath) {
        toDos.add(newToDo);
        fileWriter(toDos, toDoPath);
        return toDos;
    }

    public static List<String> removeToDo(String toRemove, List<String> toDos, Path toDoPath) {
        try {
        int removeIndex = Integer.parseInt(toRemove) - 1;
        if (toDos.size() >= removeIndex) {
            toDos.remove(removeIndex);
            fileWriter(toDos, toDoPath);
        }else{
            System.out.println("Nem lehetséges az eltávolítás:");
            System.out.println("túlindexelési probléma adódott!");
        }
        } catch (NumberFormatException e) {
            System.out.println("Nem lehetséges az eltávolítás:");
            System.out.println("a megadott index nem szám!");
        }
        return toDos;
    }

    private static void fileWriter(List<String> toDos, Path toDoPath) {
        try {
            Files.write(toDoPath, toDos);
        } catch (IOException e) {
            System.out.println("ToDo list cannot be found");
        }
        taskLister(toDos);
    }
}
