package DiaryApp;

import javax.swing.*;

public class DiaryPrototype {
    public static Diaries diaries = new Diaries();
    public static Diary diary;

    public static void main(String[] args) {

        while (true) {
            String choice = JOptionPane.showInputDialog("""
                            === Digital Diary App ===
                            1) Create Diary
                            2) Add Entry
                            3) Update Entry
                            4) Delete Entry
                            5) Find Entry
                            6) View Entries
                            7) Find Diary
                            8) Delete Diary
                            9) Lock Diary
                            10) Unlock Diary
                            11) Exit
                    """);
            if (choice == null || choice.equals("11")) {
                JOptionPane.showMessageDialog(null, "Exiting...");
                break;
            }
            try {
                processChoice(choice);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    private static void processChoice(String choice) {
        switch (choice) {
            case "1":
                createDiary();
                break;
            case "2":
                addEntry();
                break;
            case "3":
                updateEntry();
                break;
            case "4":
                deleteEntry();
                break;
            case "5":
                findEntry();
                break;
            case "6":
                viewEntries();
                break;
            case "7":
                findDiary();
                break;
            case "8":
                deleteDiary();
                break;
            case "9":
                lockDiary();
                break;
            case "10":
                unlockDiary();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice!");
        }
    }


    private static void createDiary() {
        String userName = JOptionPane.showInputDialog("Enter username:");
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        }

        String password = JOptionPane.showInputDialog("Enter password:");
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty.");
            return;
        }

        try {
            diaries.addDiary(userName, password);
            JOptionPane.showMessageDialog(null, "Diary created for " + userName);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }


    private static void addEntry() {
        String userName = JOptionPane.showInputDialog("Enter username:");
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        }

        Diary diary = diaries.findDiaryByUserName(userName);
        if (diary == null) {
            JOptionPane.showMessageDialog(null, "Diary not found.");
            return;
        }

        String password = JOptionPane.showInputDialog("Enter password:");
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty.");
            return;
        }

        if (!diary.unlockDiary(password)) {
            JOptionPane.showMessageDialog(null, "Invalid password.");
            return;
        }

        String idNumber = JOptionPane.showInputDialog("Enter entry ID:");
        int id = Integer.parseInt(idNumber);

        String title = JOptionPane.showInputDialog("Enter title:");
        if (title == null || title.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Title cannot be empty.");
            return;
        }

        String body = JOptionPane.showInputDialog("Enter text:");
        if (body == null || body.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Text cannot be empty.");
            return;
        }

        try {
            Entry entry = new Entry(id, title, body);
            JOptionPane.showMessageDialog(null, "Entry created!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }


    private static void updateEntry() {
        String userName = JOptionPane.showInputDialog("Enter username:");
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        }

        Diary diary = diaries.findDiaryByUserName(userName);
        if (diary == null) {
            JOptionPane.showMessageDialog(null, "Diary not found.");
            return;
        }

        String password = JOptionPane.showInputDialog("Enter password:");
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty.");
            return;
        }

        if (!diary.unlockDiary(password)) {
            JOptionPane.showMessageDialog(null, "Invalid password.");
            return;
        }

        String idInput = JOptionPane.showInputDialog("Enter entry ID:");
        if (idInput == null || idInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Entry ID cannot be empty.");
            return;
        }

        try {
            int id = Integer.parseInt(idInput);
            String newTitle = JOptionPane.showInputDialog("Enter new title:");
            if (newTitle == null || newTitle.isEmpty()) {
                JOptionPane.showMessageDialog(null, "New title cannot be empty.");
                return;
            }

            String newBody = JOptionPane.showInputDialog("Enter new text:");
            if (newBody == null || newBody.isEmpty()) {
                JOptionPane.showMessageDialog(null, "New text cannot be empty.");
                return;
            }

            diary.updateEntry(id, newTitle, newBody);
            JOptionPane.showMessageDialog(null, "Entry updated!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid entry ID format.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }


    private static void deleteEntry() {
        String userName = JOptionPane.showInputDialog("Enter username:");
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        }

        Diary diary = diaries.findDiaryByUserName(userName);
        if (diary == null) {
            JOptionPane.showMessageDialog(null, "Diary not found.");
            return;
        }

        String password = JOptionPane.showInputDialog("Enter password:");
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty.");
            return;
        }

        if (!diary.unlockDiary(password)) {
            JOptionPane.showMessageDialog(null, "Invalid password.");
            return;
        }


        String idNumber = JOptionPane.showInputDialog("Enter entry ID:");
        int id = Integer.parseInt(idNumber);
        JOptionPane.showMessageDialog(null, "Entry ID cannot be empty.");

        try {
            diary.deleteEntry(id);
            JOptionPane.showMessageDialog(null, "Entry deleted!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid entry ID format.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }


    private static void findEntry() {
        String userName = JOptionPane.showInputDialog("Enter username:");
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        }

        Diary diary = diaries.findDiaryByUserName(userName);
        if (diary == null) {
            JOptionPane.showMessageDialog(null, "Diary not found.");
            return;
        }

        String password = JOptionPane.showInputDialog("Enter password:");
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty.");
            return;
        }

        if (!diary.unlockDiary(password)) {
            JOptionPane.showMessageDialog(null, "Invalid password.");
            return;
        }

        String idInput = JOptionPane.showInputDialog("Enter entry ID:");
        if (idInput == null || idInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Entry ID cannot be empty.");
            return;
        }

        try {
            int id = Integer.parseInt(idInput);
            Entry entry = diary.findEntryById(id);
            JOptionPane.showMessageDialog(null, entry != null ? "Entry found: " + entry.getTitle() : "Entry not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid entry ID format.");
        }
    }


    private static void viewEntries() {
        String userName = JOptionPane.showInputDialog("Enter username:");
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        }

        Diary diary = diaries.findDiaryByUserName(userName);
        if (diary == null) {
            JOptionPane.showMessageDialog(null, "Diary not found.");
            return;
        }

        String password = JOptionPane.showInputDialog("Enter password:");
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty.");
            return;
        }

        if (!diary.unlockDiary(password)) {
            JOptionPane.showMessageDialog(null, "Invalid password.");
        }

    }


    private static void findDiary() {
        String userName = JOptionPane.showInputDialog("Enter username:");
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        }

        Diary diary = diaries.findDiaryByUserName(userName);
        JOptionPane.showMessageDialog(null, diary != null ? "Diary found for " + userName : "Diary not found!");
    }

    private static void deleteDiary() {
        String userName = JOptionPane.showInputDialog("Enter username:");
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        }

        String password = JOptionPane.showInputDialog("Enter password:");
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty.");
            return;
        }

        try {
            diaries.deleteDiary(userName, password);
            JOptionPane.showMessageDialog(null, "Diary deleted!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Diary not found or incorrect password!");
        }
    }


    private static void lockDiary() {
        String userName = JOptionPane.showInputDialog("Enter username:");
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        }

        Diary diary = diaries.findDiaryByUserName(userName);
        if (diary == null) {
            JOptionPane.showMessageDialog(null, "Diary not found!");
            return;
        }

        diary.lockDiary();
        JOptionPane.showMessageDialog(null, "Diary locked!");
    }

    private static void unlockDiary() {
        String userName = JOptionPane.showInputDialog("Enter username:");
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        }

        Diary diary = diaries.findDiaryByUserName(userName);
        if (diary == null) {
            JOptionPane.showMessageDialog(null, "Diary not found!");
            return;
        }

        String password = JOptionPane.showInputDialog("Enter password:");
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty.");
            return;
        }

        if (diary.unlockDiary(password)) {
            JOptionPane.showMessageDialog(null, "Diary unlocked!");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid password!");
        }
    }
}


