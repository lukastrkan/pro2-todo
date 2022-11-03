package cz.uhk.pro2.todo.gui;

import cz.uhk.pro2.todo.model.InMemoryTaskList;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskListTableModel extends AbstractTableModel {

    private InMemoryTaskList inMemoryTaskList;

    private DateTimeFormatter dateTimeFormatter;

    public TaskListTableModel(InMemoryTaskList inMemoryTaskList, DateTimeFormatter dateFormater) {
        this.inMemoryTaskList = inMemoryTaskList;
        this.dateTimeFormatter = dateFormater;
    }

    @Override
    public int getRowCount() {
        return inMemoryTaskList.getTasks().size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var task = inMemoryTaskList.getTasks().get(rowIndex);

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
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        var t = inMemoryTaskList.getTasks().get(rowIndex);
        switch (columnIndex) {
            case 2:
                t.setFinished((boolean) aValue);
                break;
            case 1:
                t.setDescription((String) aValue);
                break;
            case 0:
                t.setDueDate(LocalDate.from(dateTimeFormatter.parse((String) aValue)));
                break;
        }
    }

    public void setTaskList(InMemoryTaskList inMemoryTaskList) {
        this.inMemoryTaskList = inMemoryTaskList;
        fireTableDataChanged();
    }
}
