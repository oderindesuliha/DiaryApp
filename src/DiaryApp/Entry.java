
package DiaryApp;

import java.time.LocalDateTime;

public class Entry {
    private String title;
    private String bodyOfText;
    private int id;
    private LocalDateTime dateCreated;
    private LocalDateTime newDate;

    public Entry(int id,String bodyOfText, String title) {
        this.id = id;
        this.bodyOfText = bodyOfText;
        this.title = title;
        this.dateCreated = LocalDateTime.now();
        this.newDate = dateCreated;
    }

    public void setId(int id) {
        if (id >= 1) this.id = id;

    }
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    public String getBodyOfText() {
        return bodyOfText;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getNewDate(LocalDateTime now){
        return newDate;
    }


}


