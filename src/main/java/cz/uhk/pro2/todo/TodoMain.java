package cz.uhk.pro2.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.uhk.pro2.todo.gui.TaskListTableModel;
import cz.uhk.pro2.todo.model.DbTaskList;
import cz.uhk.pro2.todo.model.Task;
import cz.uhk.pro2.todo.model.InMemoryTaskList;
import cz.uhk.pro2.todo.model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TodoMain extends JFrame {


    private TaskList taskList = new DbTaskList();
    private final DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("d.M.yyyy");
    private final TaskListTableModel taskListTableModel = new TaskListTableModel(taskList, dateFormater);
    private final JTable tblTasks = new JTable(taskListTableModel);

    private final ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
    private String jsonFilename = "data.json";


    public TodoMain() {
        setTitle("ToDo list");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);

        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        this.add(panel, BorderLayout.NORTH);
        this.add(new JScrollPane(tblTasks), BorderLayout.CENTER);

        var btnSave = new JButton("Uložit do jsonu");
        var btnLoad = new JButton("Načíst z JSON");
        JButton btnAdd = new JButton("Přidat úkol");

        panel.add(btnAdd);
        panel.add(btnSave);
        panel.add(btnLoad);
        btnAdd.addActionListener(e -> addTask(taskList));
        btnSave.addActionListener(e -> saveTasks());
        btnLoad.addActionListener(e -> loadTasks());
        pack();
    }

    private void loadTasks() {
        try {
            taskList = mapper.readValue(new File(jsonFilename), InMemoryTaskList.class);
            taskListTableModel.setTaskList(taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveTasks() {
        try {
            mapper.writeValue(new File(jsonFilename), taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addTask(TaskList taskList) {
        // Kontrola správnosti dat
        try {
            // Zadání od uživatele
            String description = JOptionPane.showInputDialog(null, "Zadejte popis úkolu:").trim();
            String dateStr = JOptionPane.showInputDialog(null, "Zadejte datum ve tvaru: d.M.yyyy").trim();

            LocalDate date;
            if (dateStr.isEmpty()) {
                date = LocalDate.now();
            } else {
                date = LocalDate.parse(dateStr, dateFormater);
            }

            // Vytvoření úkolu a přidání úkolu do tasklistu
            taskList.addTask(new Task(date, description));
            //txtOutput.setText(taskList.getTasks().toString());
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(null, "Nesprávně zadané datum.", "Upozornění", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Nezadali jste žádnou hodnotu! \n\n" + ex.getMessage(), "Upozornění", JOptionPane.ERROR_MESSAGE);
        }
        taskListTableModel.fireTableDataChanged();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TodoMain tm = new TodoMain();
        });

    }
}
