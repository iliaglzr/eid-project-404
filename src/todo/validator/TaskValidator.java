package todo.validator;

import db.Entity;
import db.Validator;
import db.exception.InvalidEntityException;
import todo.entity.Task;

public class TaskValidator implements Validator {

    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if (!(entity instanceof Task)) {
            var excepation = new IllegalArgumentException("it's must be Task");
            throw excepation;
        }
        if (((Task) entity).title == null) {
            throw new IllegalArgumentException("Step title cannot be null or empty.");
        }
    }
}
