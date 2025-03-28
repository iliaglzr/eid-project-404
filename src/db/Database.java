package db;

import db.exception.EntityNotFoundException;

import java.util.ArrayList;

public class Database {
    public static int i=0;
    private static ArrayList<Entity>entities=new ArrayList<>();
    public static void add(Entity entity) {
        i++;
        entity.id=i;
        entities.add(entity);

    }
    public static Entity get(int id) {
        for (Entity entity : entities) {
            if (entity.id == id) {
            return entity;
            }
        }
        var excepation = new EntityNotFoundException(id);
        throw excepation;
    }
    public static void delete (int id ) {
        entities.removeIf( entity-> entity.id == id);
        }
        public static void update (Entity e) {
        int status=0;
        for(Entity entity : entities) {
            if(entity.id == e.id) {
                status=1;
                entities.set(e.id,e);
            }
        }
        if(status == 0) {
            var excepation = new EntityNotFoundException ("EntityNotFoundException");
            throw excepation;
        }
        }

    }

