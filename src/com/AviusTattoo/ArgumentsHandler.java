package com.AviusTattoo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ArgumentsHandler {

    public static void taskLister(List<String> toDos){
        if (toDos.size() > 0) {
            for(int i = 0; i< toDos.size(); i++) {
                System.out.println(i+1 + " - " + toDos.get(i));
            }
        } else {
            System.out.println("Nincs mára tennivalód! :)");
        }
    }

    public static List<String> addToDo(String newToDo, List<String> toDos, Path toDoPath){
        toDos.add(newToDo);
        try {
            Files.write(toDoPath, toDos);
        } catch (IOException e) {
            System.out.println("ToDo list cannot be found");
        }
        taskLister(toDos);
        return toDos;
    }
}
