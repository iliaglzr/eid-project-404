package todo.service;

import db.Database;
import db.exception.InvalidEntityException;
import todo.entity.Step;



import todo.entity.Step;
import db.Database;

public class StepService {
    public static void saveStep(int taskRef, String title) throws InvalidEntityException {
        Step step = new Step(taskRef, title);
        Database.add(step);
    }

    public static void updateStep(Step step, String field, String newValue) throws InvalidEntityException {
        switch (field.toLowerCase()) {
            case "title":
                step.title=newValue;
                break;
            case "state":
                step.status = Step.Status.valueOf(newValue);
                break;
            default:
                throw new IllegalArgumentException("Invalid field name.");
        }
        Database.update(step);
    }
}

