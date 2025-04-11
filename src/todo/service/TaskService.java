package todo.service;

import db.Database;
import db.exception.InvalidEntityException;
import todo.entity.Step;
import todo.entity.Task;

import java.util.Date;

public class TaskService {
    public static todo.service.StepService StepService;
    public String title;
    public Step.Status status;
    public int taskRef;

    public static void updateTask(Task task, String field, String newValue) throws Exception {
        switch (field.toLowerCase()) {
            case "title":
                task.title = newValue;
                break;
            case "description":
                task.description = newValue;
                break;

            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }
        task.setLastModificationDate(new Date());


        Database.update(task);
    }



}
