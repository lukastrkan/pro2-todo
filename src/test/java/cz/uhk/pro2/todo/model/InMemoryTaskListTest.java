package cz.uhk.pro2.todo.model;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class InMemoryTaskListTest {
    @Test
    public void countUnfinished() {
        var taskList = new InMemoryTaskList();
        taskList.addTask(new Task(LocalDate.of(2020, 1, 1), "Task 1").setFinished(true));
        taskList.addTask(new Task(LocalDate.of(2020, 1, 1), "Task 2").setFinished(false));
        taskList.addTask(new Task(LocalDate.of(2020, 1, 1), "Task 3").setFinished(false));
        taskList.addTask(new Task(LocalDate.of(2020, 1, 1), "Task 4").setFinished(false));
        taskList.addTask(new Task(LocalDate.of(2020, 1, 1), "Task 5").setFinished(true));

        assertEquals(taskList.countUnfinished(), 3);
    }

    @Test
    public void countUnfinished0(){
        var taskList = new InMemoryTaskList();
        assertEquals(taskList.countUnfinished(), 0);
    }
}