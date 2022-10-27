package cz.uhk.pro2.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.uhk.pro2.todo.gui.TaskListTableModel;
import cz.uhk.pro2.todo.model.Task;
import cz.uhk.pro2.todo.model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TodoMain extends JFrame {


    private final TaskList taskList = new TaskList();
    private final DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("d.M.yyyy");
    private final TaskListTableModel taskListTableModel = new TaskListTableModel(taskList, dateFormater);
    private final JTable tblTasks = new JTable(taskListTableModel);


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

        JButton btnAdd = new JButton("Přidat úkol");
        panel.add(btnAdd);
        panel.add(btnSave);
        btnAdd.addActionListener(e -> addTask(taskList));
        btnSave.addActionListener(e -> saveTasks());

        //txtOutput.setText(taskList.getTasks().toString());

        pack();
    }

    private void saveTasks() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("data.json"), taskList);
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
        tblTasks.updateUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TodoMain tm = new TodoMain();
        });

    }
}
