package cz.uhk.pro2.todo;

import cz.uhk.pro2.todo.gui.TaskListTableModel;
import cz.uhk.pro2.todo.model.Task;
import cz.uhk.pro2.todo.model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TodoMain extends JFrame {

    private TaskList taskList = new TaskList();

    //private JTextArea txtOutput = new JTextArea(20,40);


    private TaskListTableModel taskListTableModel = new TaskListTableModel(taskList);

    private JTable tblTasks = new JTable(taskListTableModel);

    public TodoMain() {
        setTitle("ToDo list");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);

        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        this.add(panel, BorderLayout.NORTH);
        this.add(new JScrollPane(tblTasks), BorderLayout.CENTER);
        JButton btnAdd = new JButton("Přidat úkol");
        panel.add(btnAdd);
        btnAdd.addActionListener(e -> addTask(taskList));

        //txtOutput.setText(taskList.getTasks().toString());

        pack();
    }

    private void addTask(TaskList taskList) {
        // Kontrola správnosti dat
        try {
            // Zadání od uživatele
            String description = JOptionPane.showInputDialog(null, "Zadejte popis úkolu:").trim();
            if (description.isEmpty())
                return;
            LocalDate date = LocalDate.parse(JOptionPane.showInputDialog(null, "Zadejte datum ve tvaru: dd/MM/yyyy").trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

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
