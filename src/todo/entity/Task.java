package todo.entity;

import db.Entity;
import db.Trackable;
import example.Document;
import jdk.jshell.Snippet;

import java.util.Date;

public class Task extends Entity implements Trackable {

    public String title;
    public String description;
    Date dueDate;
    Status status;
    public enum Status {
        NOT_STARTED, IN_PROGRESS, COMPLETED
    }
    public Task(String title, String description, Date dueDate) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty.");
        }

        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = Status.NOT_STARTED;
    }

    public Date creationdate = new Date(),LastModificationDate=new Date();
    public final int HUMAN_ENTITY_CODE=15;
    public Entity copy() {

        return null;
    }


    @Override
    public int getEntityCode() {
        return  HUMAN_ENTITY_CODE;
    }

    @Override
    public void setCreationDate(Date creationdate) {
        this.creationdate= creationdate;
    }

    @Override
    public Date getCreationDate() {
        return creationdate;
    }

    @Override
    public void setLastModificationDate(Date date) {
        this.LastModificationDate =LastModificationDate;
    }

    @Override
    public Date getLastModificationDate() {
        return LastModificationDate;
    }
}