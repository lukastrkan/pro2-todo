package cz.uhk.pro2.todo.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(@NotNull Task task) {
        tasks.add(task);
    }

    public void removeTask(@NotNull Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public int countUnfinished() {
        return (int) tasks.stream().filter(t -> !t.isFinished()).count();
    }
}
