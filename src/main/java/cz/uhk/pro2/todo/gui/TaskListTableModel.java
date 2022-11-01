package cz.uhk.pro2.todo.gui;

import cz.uhk.pro2.todo.TodoMain;
import cz.uhk.pro2.todo.model.TaskList;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskListTableModel extends AbstractTableModel {

    private TaskList taskList;

    private DateTimeFormatter dateTimeFormatter;

    public TaskListTableModel(TaskList taskList, DateTimeFormatter dateFormater) {
        this.taskList = taskList;
        this.dateTimeFormatter = dateFormater;
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
                return task.getDueDate().format(dateTimeFormatter);
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
        switch (columnIndex) {
            case 0:
            case 1:
                return String.class;
            case 2:
                return Boolean.class;
            default:
                throw new IllegalArgumentException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
        //TODO: edit all fields
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 2) {
            taskList.getTasks().get(rowIndex).setFinished((boolean) aValue);
        }

        if (columnIndex == 1){
            taskList.getTasks().get(rowIndex).setDescription((String) aValue);
        }

        if (columnIndex == 0){
            taskList.getTasks().get(rowIndex).setDueDate(LocalDate.from(dateTimeFormatter.parse((String) aValue)));
        }
    }
}
