package com.AviusTattoo;

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

    @Override
    public String toString() {
        if(isFinished){
            return "[x] - " + line;
        }else{
            return "[ ] - " + line;
        }
    }
}
