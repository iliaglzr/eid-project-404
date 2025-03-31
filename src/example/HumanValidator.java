package example;

import db.Entity;
import db.Validator;
import db.exception.InvalidEntityException;

public class HumanValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if(!(entity instanceof Human)) {
            throw new IllegalArgumentException ("Wrong Entity");
        }
        if(((Human) entity).name =="" || ((Human) entity).name ==null) {
            throw new InvalidEntityException("Error in name assignment");
        }
        if(((Human) entity).age<0) {
            throw new InvalidEntityException("Error in age assignment");
        }
    }
}
