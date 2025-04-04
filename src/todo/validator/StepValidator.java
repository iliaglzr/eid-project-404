package todo.validator;

import db.Entity;
import db.Validator;
import todo.entity.Step;
import todo.entity.Task;

import static db.Database.entities;

public class StepValidator implements Validator {
    @Override
    public void validate(Entity entity) {
        if (!(entity instanceof Step)) {
            throw new IllegalArgumentException("Entity is not a Step instance.");
        }



        if (((Step) entity).title ==null) {
            throw new IllegalArgumentException("Step title cannot be null or empty.");
        }

        boolean taskExists = false;
        for (Entity e : entities) {
            if (e instanceof Task task && task.id == entity.id ) {
                taskExists = true;
                break;
            }
        }

        if (!taskExists) {
            throw new IllegalArgumentException("No Task with id " + ((Step) entity).taskRef + " found.");
        }
    }
}

