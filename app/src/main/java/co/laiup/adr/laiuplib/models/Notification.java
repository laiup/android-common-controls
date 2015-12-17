package co.laiup.adr.laiuplib.models;

/**
 * Project android-common-controls
 * Created by Ha on 11/24/2015.
 */
public class Notification {
    private String id;
    private String content;

    public Notification() {
    }

    public Notification(String id, String content) {

        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
