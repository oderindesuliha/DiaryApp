package DiaryApp;

import java.util.ArrayList;
import java.util.List;

public class Diaries {
    private List<Diary> diaries;
    private String userName;
    private String password;

    public Diaries() {
        this.diaries = new ArrayList<Diary>();
        this.userName = userName;
    }

    public void addDiary(String userName, String password) {
        Diary diary = new Diary(userName, password);
        diaries.add(diary);
    }

    public Diary findDiaryByUserName(String userName) {
        for (Diary diary : diaries) {
            if(diary.getUserName().equals(userName)) {
                return diary;
            }
        }
        return null;
    }

    public void deleteDiary(String userName, String password) {
        for(Diary diary : diaries) {
            if (diary.getUserName().equals(userName) && diary.checkPassword().equals(password)) {
                diaries.remove(diary);
                break;
            }
        }
    }
}