package cz.uhk.pro2.todo.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TaskList {
    /**
     * Add task to storage
     * @param task to be added
     */
    public void addTask(@NotNull Task task);

    /**
     * Remove task from storage
     * @throws TaskNotFoundException when task is not in the storage
     * @param task to be removed
     */
    public void removeTask(@NotNull Task task);

    /**
     * Fetch all tasks from the storage.
     * @return list of tasks
     */
    public List<Task> getTasks();

    /**
     * Count unfinished tasks
     * @return tasks count
     */
    public int countUnfinished();
}
