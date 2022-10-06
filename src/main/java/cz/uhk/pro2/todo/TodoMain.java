package cz.uhk.pro2.todo;

import cz.uhk.pro2.todo.model.Task;
import cz.uhk.pro2.todo.model.TaskList;

import javax.swing.*;
import java.time.LocalDate;

public class TodoMain {
    public static void main(String[] args) {
        var taskList = new TaskList();

        // create tasks until user enters empty name
        while (true) {
            String description = JOptionPane.showInputDialog("Enter task name");
            if (description == null || description.isEmpty()) {
                break;
            }

            String date = JOptionPane.showInputDialog("Enter date (yyyy-MM-dd)");

            Task task = new Task(LocalDate.parse(date), description);
            taskList.addTask(task);
        }

        //print all tasks
        for (Task task : taskList.getTasks()) {
            System.out.println(task);
        }

    }
}
