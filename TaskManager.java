import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks;
    private FileManager fileManager;

    public TaskManager() {
        tasks = new ArrayList<>();
        fileManager = new FileManager();
    }

    public void addTask(String title) {
        Task task = new Task(title);
        tasks.add(task);
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public void editTask(int index, String newTitle) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setTitle(newTitle);
        }
    }

    public void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setCompleted(true);
        }
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void saveTasks() {
        fileManager.saveTasks(tasks);
    }

    public void loadTasks() {
        tasks = fileManager.loadTasks();
    }
}