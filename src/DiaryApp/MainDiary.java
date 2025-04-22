package DiaryApp;

import javax.swing.JOptionPane;

public class MainDiary {
    private static Diaries diaries = new Diaries();

    public static void main(String[] args) {
        while (true) {
            String choice = JOptionPane.showInputDialog("""
                            ====================================
                            Welcome to the Digital Diary App!
                            ====================================
                            Choose an option:
                            ____________________________________
                            1) Create a Diary
                            2) Add an Entry
                            3) Update an Entry
                            4) Delete an Entry
                            5) Find an Entry
                            6) View Entries
                            7) Find a Diary
                            8) Delete a Diary
                            9) Lock a Diary
                            10) Unlock a Diary
                            11) Exit
                           \s""");

            if (choice == null || choice.equals("11")) {
                JOptionPane.showMessageDialog(null, "Exiting...");
                break;
            }

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
                    JOptionPane.showMessageDialog(null, "Invalid choice! Please select a valid option.");
            }
        }
    }

    public static void createDiary() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        diaries.addDiary(username, password);
        JOptionPane.showMessageDialog(null, "Diary successfully created for " + username);
    }

    public static void addEntry() {
        String username = JOptionPane.showInputDialog("Enter username:");
        Diary diary = diaries.findDiaryByUserName(username);

        String password = JOptionPane.showInputDialog("Enter password:");
        diary.unlockDiary(password);

        if (diary == null) {
            JOptionPane.showMessageDialog(null, "Diary not found for the given username.");
        }
        if (diary != null && !diary.unlockDiary(password)) {
            JOptionPane.showMessageDialog(null, "Invalid password");
        }

        String title = JOptionPane.showInputDialog("Enter title:");
        String bodyOfText = JOptionPane.showInputDialog("Enter text:");

        diary.createEntry(title, bodyOfText);
        JOptionPane.showMessageDialog(null, "Your entry has been successfully added!");



        diary.lockDiary();
    }

    public static void updateEntry() {
        String username = JOptionPane.showInputDialog("Enter username:");
        Diary diary = diaries.findDiaryByUserName(username);

        String password = JOptionPane.showInputDialog("Enter password:");
        diary.unlockDiary(password);

        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter entry ID:"));
        String newTitle = JOptionPane.showInputDialog("Enter new title:");
        String newBody = JOptionPane.showInputDialog("Enter new text:");

        diary.updateEntry(id, newTitle, newBody);
        JOptionPane.showMessageDialog(null, "Entry updated!");

        diary.lockDiary();
    }

    public static void deleteEntry() {
        String username = JOptionPane.showInputDialog("Enter username:");
        Diary diary = diaries.findDiaryByUserName(username);

        String password = JOptionPane.showInputDialog("Enter password:");
        diary.unlockDiary(password);

        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter entry ID:"));
        diary.deleteEntry(id);
        JOptionPane.showMessageDialog(null, "Entry deleted!");

        diary.lockDiary();
    }

    public static void findEntry() {
        String username = JOptionPane.showInputDialog("Enter username:");
        Diary diary = diaries.findDiaryByUserName(username);

        String password = JOptionPane.showInputDialog("Enter password:");
        diary.unlockDiary(password);

        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter entry ID:"));
        Entry entry = diary.findEntryById(id);

        JOptionPane.showMessageDialog(null, entry != null ? "Entry found: " + entry.getTitle() : "Entry not found!");

        diary.lockDiary();
    }

    public static void viewEntries() {
        String username = JOptionPane.showInputDialog("Enter username:");
        Diary diary = diaries.findDiaryByUserName(username);

        String password = JOptionPane.showInputDialog("Enter password:");
        diary.unlockDiary(password);

        
        diary.lockDiary();
    }

    public static void findDiary() {
        String username = JOptionPane.showInputDialog("Enter username:");
        Diary diary = diaries.findDiaryByUserName(username);
        JOptionPane.showMessageDialog(null, diary != null ? "Diary found for " + username : "Diary not found!");
    }

    public static void deleteDiary() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        Diary diary = diaries.findDiaryByUserName(username);
        if (diary != null && diary.checkPassword().equals(password)) {
            diaries.deleteDiary(username, password);
            JOptionPane.showMessageDialog(null, "Diary deleted!");
        } else {
            JOptionPane.showMessageDialog(null, "Diary not found or incorrect password!");
        }
    }

    public static void lockDiary() {
        String username = JOptionPane.showInputDialog("Enter username:");
        Diary diary = diaries.findDiaryByUserName(username);
        diary.lockDiary();
        JOptionPane.showMessageDialog(null, "Diary locked!");
    }

    public static void unlockDiary() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        Diary diary = diaries.findDiaryByUserName(username);
        diary.unlockDiary(password);
        JOptionPane.showMessageDialog(null, diary.isLocked() ? "Incorrect password!" : "Diary unlocked!");
    }
}
