package cz.uhk.pro2.todo.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class Task {
    private LocalDate dueDate;
    private String description;
    private boolean finished;
    public Task() {
    }

    public Task(LocalDate dueDate, String description) {
        this.dueDate = dueDate;
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return finished;
    }

    public @NotNull Task setFinished(boolean finished) {
        this.finished = finished;
        return this;
    }

    @Override
    public String toString() {
        return "Task{" +
                "dueDate=" + dueDate +
                ", description='" + description + '\'' +
                ", finished=" + finished +
                '}';
    }
}
