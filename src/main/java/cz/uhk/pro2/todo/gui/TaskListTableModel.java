package cz.uhk.pro2.todo.gui;

import cz.uhk.pro2.todo.model.TaskList;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;

public class TaskListTableModel extends AbstractTableModel {

    private TaskList taskList;

    public TaskListTableModel(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public int getRowCount() {
        return taskList.getTasks().size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var task = taskList.getTasks().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return task.getDueDate();
            case 1:
                return task.getDescription();
            case 2:
                return task.isFinished();
            default:
                throw new IllegalArgumentException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Datum";
                case 1:
                    return "Úkol";
                case 2:
                    return "Splněno";
                default:
                    throw new IllegalArgumentException("Invalid column index: " + column);
            }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0: return LocalDate.class;
            case 1: return String.class;
            case 2: return Boolean.class;
            default: throw new IllegalArgumentException("Invalid column index: " + columnIndex);
        }
    }
}
