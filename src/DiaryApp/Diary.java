package DiaryApp;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Diary {
    private boolean unlocked;
    private String userName;
    private String password;
    private List<Entry> entries;
    private int id;

    public Diary(String userName, String password) {
        validateUsername(userName);
        this.userName = userName;
        this.password = password;
        this.entries = new ArrayList<>();
        this.unlocked = false;
        this.id = 1;
    }

    public void validateUsername(String userName) {
        if (userName.isBlank()) throw new IllegalArgumentException("Username cannot be blank");

    }

    public String getUserName() {
        if(userName.isBlank()) return "Username cannot be blank";
        return userName;
    }

    public String checkPassword() {
        if(this.password.isBlank()) return "Password cannot be blank";
        return this.password;
    }

    public List<Entry> getEntries() {
        if(unlocked) {
            return entries;
        }
        throw new IllegalArgumentException("Diary is locked!");
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String newPassword) {

        this.password = newPassword;
    }
    public void lockDiary() {
        unlocked = false;
    }

    public boolean unlockDiary(String correctPassword) {
        if(correctPassword.equals(password)) unlocked = true;
        return false;
    }

    public boolean isLocked() {
        return !unlocked;
    }

    public void createEntry(String title, String bodyOfText) {
        if(unlocked) {
            Entry entry = new Entry(id, title, bodyOfText);
            entries.add(entry);
            id += 1;
        }
    }

    public void deleteEntry(int id) {
        if(unlocked) {
            for(int counter = 0; counter < entries.size(); counter++) {
                Entry entry = entries.get(counter);
                if(entry.getId() == id) {
                    entries.remove(counter);
                    break;
                }
            }
        }
    }

    public Entry findEntryById(int id) {
        if(!unlocked) {
            throw new IllegalArgumentException("Diary is locked!");
        }
        for (Entry entry : entries) {
            if (entry.getId() == id) return entry;
        }
        return null;

    }

    public void updateEntry(int id, String title, String bodyOfText) {
        Entry entry = findEntryById(id);
        if(entry != null){
            entry.getTitle();
            entry.getBodyOfText();
            entry.getNewDate(LocalDateTime.now());
        }

    }

}


