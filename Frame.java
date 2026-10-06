import javax.swing.*;

public class Frame {
    int width, height;
    JFrame frame;
    JLabel name;
    JTextField task, editField;
    JButton menu, add, delete, edit, complete;
    JPanel bar;
    JList<Task> list;
    DefaultListModel<Task> model;
    JDialog editWindow;
    TaskManager taskManager;
    Frame(int width, int height) {

        this.height = height;
        this.width = width;

        frame = new JFrame();

        name = new JLabel("Add a task: ");

        add = new JButton("Add");
        delete = new JButton("Delete");
        edit = new JButton("Edit");
        menu = new JButton("MENU");
        complete = new JButton("Mark Complete");

        bar = new JPanel();

        model = new DefaultListModel<>();

        list = new JList<>(model);

        taskManager = new TaskManager();
        taskManager.loadTasks();
        for(int i = 0;i<taskManager.getTaskCount();i++){
            model.addElement(taskManager.getTask(i));
        }
        editWindow = new JDialog();

        editField = new JTextField();


        // Complete button

        complete.setBounds(260, 425, 140, 40);

        complete.addActionListener(e -> {

            int index = list.getSelectedIndex();

            if (index != -1) {

                taskManager.completeTask(index);

                list.repaint();
            }
        });


        // Edit window

        editField.setBounds(40, 65, 310, 40);

        editWindow.setSize(400, 220);

        editWindow.setLayout(null);

        editWindow.setResizable(false);

        editWindow.setLocationRelativeTo(frame);

        editWindow.setTitle("Edit Task");


        JLabel editLabel = new JLabel("Edit your task:");

        editLabel.setBounds(40, 30, 150, 30);


        JButton confirm = new JButton("Confirm");

        confirm.setBounds(40, 120, 150, 40);


        JButton cancel = new JButton("Cancel");

        cancel.setBounds(200, 120, 150, 40);


        editWindow.add(confirm);

        editWindow.add(cancel);

        editWindow.add(editField);

        editWindow.add(editLabel);


        // Confirm edit

        confirm.addActionListener(e -> {

            int index = list.getSelectedIndex();

            String text = editField.getText();

            if (index != -1 && !text.isEmpty()) {

                taskManager.editTask(index, text);

                list.repaint();

                taskManager.saveTasks();

                editField.setText("");

                editWindow.dispose();
            }
        });


        // Cancel edit

        cancel.addActionListener(e -> {

            editField.setText("");

            editWindow.dispose();
        });


        // List

        list.setBounds(50, 160, 450, 250);


        // Menu bar

        bar.setBounds(width - 150, 45, 150, height - 45);

        bar.setLayout(null);

        bar.setVisible(false);


        JLabel update = new JLabel("MENU");

        update.setBounds(40, 20, 100, 30);

        bar.add(update);


        // Main UI

        name.setBounds(50, 20, 150, 30);

        add.setBounds(50, 100, 80, 40);

        delete.setBounds(40, 425, 90, 40);

        edit.setBounds(150, 425, 90, 40);

        menu.setBounds(width - 100, 5, 80, 35);


        task = new JTextField();

        task.setBounds(50, 55, 230, 40);


        // Add task

        add.addActionListener(e -> {

            String input = task.getText();

            if (!input.isEmpty()) {

                taskManager.addTask(input);

                int index = taskManager.getTaskCount() - 1;

                model.addElement(taskManager.getTask(index));

                taskManager.saveTasks();

                task.setText("");                
            }
        });


        // Delete task

        delete.addActionListener(e -> {

            int index = list.getSelectedIndex();

            if (index != -1) {

                taskManager.deleteTask(index);

                model.remove(index);

                taskManager.saveTasks();
            }
        });


        // Edit task

        edit.addActionListener(e -> {

            int index = list.getSelectedIndex();

            if (index != -1) {

                editField.setText(taskManager.getTask(index).getTitle());

                editWindow.setVisible(true);

                editWindow.setModal(true);
            }
        });


        // Menu

        menu.addActionListener(e -> {

            bar.setVisible(!bar.isVisible());
        });


        // Default frame

        frame.add(name);
        frame.add(add);
        frame.add(menu);
        frame.add(task);
        frame.add(bar);
        frame.add(list);
        frame.add(delete);
        frame.add(edit);
        frame.add(complete);

        frame.setTitle("To-Do-App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLayout(null);
        frame.setVisible(true);

        //Do not edit the above 5 lines
    }
}