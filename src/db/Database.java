package db;

import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Database {
    public static int i=0;
    private static ArrayList<Entity>entities=new ArrayList<>();
    private static HashMap<Integer, Validator> validators=new HashMap<>();

    public static void add(Entity entity) throws InvalidEntityException {

        i++;
        entity.id=i;
        Validator validator = validators.get(entity.getEntityCode());
        if (validator != null) {
            validator.validate(entity);
        }

        entities.add(entity.copy());
        if (entity instanceof Trackable trackable) {
            Date now = new Date();
            trackable.setCreationDate(now);
            trackable.setLastModificationDate(now);
        }

    }


    public static Entity get(int id) {
        for (Entity entity : entities) {
            if (entity.id == id) {
            return entity.copy();
            }
        }
        var excepation = new EntityNotFoundException(id);
        throw excepation;
    }
    public static void delete (int id ) {
        entities.removeIf( entity-> entity.id == id);
        }
        public static void update (Entity e) throws InvalidEntityException {
        int status=0;
            Validator validator = validators.get(e.getEntityCode());
            if (validator != null) {
                validator.validate(e);
            }
            boolean updated = false;
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i).id == e.id) {
                    entities.set(i, e.copy());
                    status=1;
                    updated = true;
                    break;
                }
            }
            if (e instanceof Trackable trackable) {
                Date now = new Date();
                trackable.setCreationDate(now);
                trackable.setLastModificationDate(now);
            }

        if(status == 0) {
            var excepation = new EntityNotFoundException ("EntityNotFoundException");
            throw excepation;
        }
        }

    public static void registerValidator(int entityCode, Validator validator) {
            if (validators.containsKey(entityCode)) {
                throw new IllegalArgumentException("Validator for entityCode " + entityCode + " already exists.");
            }
            validators.put(entityCode, validator);
    }

    }

