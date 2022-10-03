package cz.uhk.pro2.todo.model;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void testToString() {
        var task = new Task(LocalDate.of(2020, 1, 1), "Task 1");
        assertEquals(task.toString(), "Task{dueDate=2020-01-01, description='Task 1', finished=false}");
        task.setFinished(true);
        assertEquals(task.toString(), "Task{dueDate=2020-01-01, description='Task 1', finished=true}");
    }
}