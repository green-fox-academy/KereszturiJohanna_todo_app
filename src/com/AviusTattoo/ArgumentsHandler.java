package com.AviusTattoo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ArgumentsHandler {

    public static void taskLister(List<ToDoList> toDos) {
        if (toDos.size() > 0) {
            for (int i = 0; i < toDos.size(); i++) {
                System.out.println(i + 1 + " - " + toDos.get(i));
            }
        } else {
            System.out.println("Nincs mára tennivalód! :)");
        }
    }

    public static List<ToDoList> addToDo(String newToDo, List<ToDoList> toDos, List<String> toDoLines, Path toDoPath) {
        toDos.add(new ToDoList(newToDo));
        toDoLines.add(newToDo);
        FileHandler.fileWriter(toDoLines, toDoPath, "ToDo list cannot be found");
        ArgumentsHandler.taskLister(toDos);
        return toDos;
    }

    public static List<ToDoList> removeToDo(String toRemove, List<ToDoList> toDos, List<String> toDoLines, Path toDoPath) {
        try {
        int removeIndex = Integer.parseInt(toRemove) - 1;
        if (toDos.size() >= removeIndex) {
            toDos.remove(removeIndex);
            toDoLines.remove(removeIndex);
            FileHandler.fileWriter(toDoLines, toDoPath, "ToDo list cannot be found");
            ArgumentsHandler.taskLister(toDos);
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

}
