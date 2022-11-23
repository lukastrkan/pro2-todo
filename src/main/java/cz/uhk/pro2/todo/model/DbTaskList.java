package cz.uhk.pro2.todo.model;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbTaskList implements TaskList {

    public DbTaskList() {
        init();
    }

    private PreparedStatement insert;
    private PreparedStatement select;
    private PreparedStatement update;
    private PreparedStatement delete;

    public void init() {
        try {
            Connection con = DriverManager.getConnection("jdbc:hsqldb:file:tasksdb");
            select = con.prepareStatement("select id, due_date, description, finished from tasks order by id asc");
            insert = con.prepareStatement("insert into tasks(due_date, description, finished) values (?,?,?)");
            update = con.prepareStatement("update tasks set due_date=?, description=?, finished=? where id=?");
            delete = con.prepareStatement("delete from tasks where id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //TODO: prepare statements for other operations CRUD
    }

    @Override
    public void addTask(@NotNull Task task) {

    }

    @Override
    public void removeTask(@NotNull Task task) {

    }

    @Override
    public List<Task> getTasks() {
        try {
            var rs = select.executeQuery();
            var tasks = new ArrayList<Task>();
            while (rs.next()) {
                var task = new Task(
                        rs.getLong("id"),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getString("description"),
                        rs.getBoolean("finished")
                );
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countUnfinished() {
        return 0;
    }
}
