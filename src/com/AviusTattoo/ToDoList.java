package com.AviusTattoo;

import java.nio.file.Path;
import java.util.List;

public class ToDoList {
    String line;
    String task;
    boolean isFinished;

    public ToDoList(String line) {
        this.line = line;
        this.task = line.substring(0, line.indexOf(";"));
        String status = line.substring(line.indexOf(";"));
        if(status.contains("true")){
            isFinished = true;
        }else{
            isFinished = false;
        }
    }

    public static List<ToDoList> finishTask(String str, List<ToDoList> toDos, List<String> toDoLines, Path toDoPath){
        try {
            int finishIndex = Integer.parseInt(str) - 1;
            if (toDos.size() >= finishIndex) {
                toDos.get(finishIndex).isFinished = true;
                toDoLines.get(finishIndex).concat("true");
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

    @Override
    public String toString() {
        if(isFinished){
            return "[x] - " + line;
        }else{
            return "[ ] - " + line;
        }
    }
}
