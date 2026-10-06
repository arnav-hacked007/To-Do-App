import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private String fileName = "tasks.txt";

    public void saveTasks(ArrayList<Task> tasks) {

        try {
            BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileName)
            );

            for (Task task : tasks) {

                writer.write(
                    task.getTitle() + "|" + task.isCompleted()
                );

                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }


    public ArrayList<Task> loadTasks() {

        ArrayList<Task> tasks = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(
                new FileReader(fileName)
            );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                String title = data[0];
                boolean completed = Boolean.parseBoolean(data[1]);

                Task task = new Task(title);
                task.setCompleted(completed);

                tasks.add(task);
            }

            reader.close();

        } catch (FileNotFoundException e) {

            // First time running the application.
            // No file exists yet.

        } catch (IOException e) {

            System.out.println("Error loading tasks.");
        }

        return tasks;
    }
}