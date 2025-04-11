import db.Database;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import example.Document;
import example.Human;
import example.HumanValidator;
import todo.entity.Step;
import todo.entity.Task;
import todo.service.StepService;
import todo.service.TaskService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("Enter command: ");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "add task":
                    addTask(scanner);
                    break;
                case "add step":
                    addStep(scanner);
                    break;
                case "delete":
                    deleteEntity(scanner);
                    break;
                case "update task":
                    updateTask(scanner);
                    break;
                case "update step":
                    updateStep(scanner);
                    break;
                case "get task-by-id":
                    getTaskById(scanner);
                    break;
                /*case "get all-tasks":
                    getAllTasks();
                    break;
                case "get incomplete-tasks":
                    getIncompleteTasks();
                    break;*/
                case "exit":
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
    }

    private static void addTask(Scanner scanner) {
        try {
            System.out.print("Title: ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) throw new IllegalArgumentException("Task title cannot be empty.");

            System.out.print("Description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Due date (yyyy-MM-dd): ");
            String dueDateStr = scanner.nextLine().trim();
            Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateStr);

            Task task = new Task(title, description, dueDate);
            Database.add(task);
            System.out.println("Task saved successfully.\nID: " + task.id);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
        } catch (Exception e) {
            System.out.println("Cannot save task.\nError: " + e.getMessage());
        }
    }

    private static void addStep(Scanner scanner) {
        try {
            System.out.print("TaskID: ");
            int taskId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Title: ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) throw new IllegalArgumentException("Step title cannot be empty.");

            TaskService.StepService.saveStep(taskId, title);
            System.out.println("Step saved successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid TaskID. Please enter a number.");
        } catch (Exception e) {
            System.out.println("Cannot save step.\nError: " + e.getMessage());
        }
    }

    private static void deleteEntity(Scanner scanner) {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Database.delete(id);
            System.out.println("Entity with ID=" + id + " successfully deleted.");
        } catch (Exception e) {
            System.out.println("Cannot delete entity.\nError: " + e.getMessage());
        }
    }

    private static void updateTask(Scanner scanner) {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Field: ");
            String field = scanner.nextLine().trim();
            System.out.print("New Value: ");
            String newValue = scanner.nextLine().trim();

            Task task = (Task) Database.get(id);
            TaskService.updateTask(task, field, newValue);
            Database.update(task);

            System.out.println("Successfully updated the task.");
        } catch (Exception e) {
            System.out.println("Cannot update task.\nError: " + e.getMessage());
        }
    }

    private static void updateStep(Scanner scanner) {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Field: ");
            String field = scanner.nextLine().trim();
            System.out.print("New Value: ");
            String newValue = scanner.nextLine().trim();

            Step step = (Step) Database.get(id);
            StepService.updateStep(step, field, newValue);
            Database.update(step);

            System.out.println("Successfully updated the step.");
        } catch (Exception e) {
            System.out.println("Cannot update step.\nError: " + e.getMessage());
        }
    }

    private static void getTaskById(Scanner scanner) {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Task task = (Task) Database.get(id);
            System.out.println(task);
        } catch (Exception e) {
            System.out.println("Cannot find task.\nError: " + e.getMessage());
        }
    }

   /* private static void getAllTasks() {
        try {
            List<Task> tasks = TaskService.getAllTasks();
            tasks.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Cannot retrieve tasks.\nError: " + e.getMessage());
        }
    }

    private static void getIncompleteTasks() {
        try {
            List<Task> tasks = TaskService.getIncompleteTasks();
            tasks.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Cannot retrieve incomplete tasks.\nError: " + e.getMessage());
        }
    }*/
}


