package example;

import db.Entity;
import db.Trackable;

import java.util.Date;

public class Document extends Entity  implements Trackable {
    public String content;
    public Date creationdate = new Date(),LastModificationDate=new Date();
    public final int HUMAN_ENTITY_CODE=15;
    public Document(String content) {
            this.content = content ;
    }

    public Entity copy() {
        Document copy = new Document(content);
        copy.id = id;
        return copy;
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
