package todo.entity;

import db.Entity;

import java.util.Date;

public class Step extends Entity {
    public final int HUMAN_ENTITY_CODE=15;
    private  Date creationDate,lastModificationDate;

    @Override
    public Entity copy() {
        return null;
    }
    public Step(int taskRef, String title) {
        this.taskRef = taskRef;
        this.title = title;
        this.status = Status.NotStarted;
        this.creationDate = new Date();
        this.lastModificationDate = new Date();
    }

    @Override
    public int getEntityCode() {
        return HUMAN_ENTITY_CODE;
    }

    public enum Status {
        NotStarted,Completed;
    }
    public String title;
    public Status status;
    public int taskRef;
}
