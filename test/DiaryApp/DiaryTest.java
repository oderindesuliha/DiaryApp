package DiaryApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryTest {
    private Diary myDiary ;

        @BeforeEach
                void setUp() {
        myDiary = new Diary("Omobolanle", "correctPassword");
        }

    @Test
    public void testThatDiaryIsLocked() {
             myDiary.lockDiary();
             assertTrue(myDiary.isLocked());
    }

    @Test
    public void testToSetUserNameAndSetPassword() {
            myDiary.setUserName("Omobolanle");
            myDiary.setPassword("B1234");
            assertEquals("Omobolanle", myDiary.getUserName());
        assertEquals("B1234", myDiary.checkPassword());
    }

    @Test
    public void testThatDiaryIsUnlockedIfPasswordIsCorrect() {
            myDiary.setPassword("Pass1234");
            myDiary.unlockDiary("Pass1234");
            assertFalse(myDiary.isLocked());
    }

    @Test
    public void testThatDiaryIsLockedAndThenUnlockedWhenCorrectPasswordIsEntered() {
            myDiary.setPassword("2025");
            myDiary.lockDiary();
            myDiary.unlockDiary("2025");
            assertFalse(myDiary.isLocked());
    }

    @Test
    public void testThatDiaryRemainsLockedAfterAnIncorrectPasswordIsEntered() {
            myDiary.setPassword("1234");
            myDiary.lockDiary();
            myDiary.unlockDiary("2342");
            assertTrue(myDiary.isLocked());
    }

    @Test
    public void testThatDiaryRemainsLockedIfPasswordAndUsernameIsBlank() {
            myDiary.setUserName(" ");
            myDiary.setPassword(" ");
            myDiary.lockDiary();
            assertTrue(myDiary.isLocked());
            assertEquals("Username cannot be blank", myDiary.getUserName());
            assertEquals("Password cannot be blank", myDiary.checkPassword());
    }

    @Test
    public void testThatDiaryIsEmptyWhenEntryIsBlank() {
            myDiary.setPassword("1245");
            myDiary.lockDiary();
            myDiary.unlockDiary("1245");
            assertFalse(myDiary.isLocked());
            assertTrue(myDiary.getEntries().isEmpty());
        }

    @Test
    public void testThatDiaryIsNotEmpTyAfterEntryIsCreated() {
            myDiary.setPassword("1245");
            myDiary.lockDiary();
            myDiary.unlockDiary("1245");
            assertFalse(myDiary.isLocked());
            myDiary.createEntry("A day in my life", "I went to the beach");
            assertFalse(myDiary.getEntries().isEmpty());
    }

    @Test
    public void testThatDiaryCanTakeMoreThanOneEntry(){
            myDiary.setPassword("1245");
            myDiary.lockDiary();
            myDiary.unlockDiary("1245");
            assertFalse(myDiary.isLocked());
            myDiary.createEntry("A day in my life", "I went to the beach");
            myDiary.createEntry("My Project", "I worked on my project");
            assertEquals(2, myDiary.getEntries().size());
    }

    @Test
    public void testThatDiaryCannotTakeAnyEntryIfItIsLocked(){
            myDiary.setPassword("1245");
            myDiary.lockDiary();
            myDiary.unlockDiary("1245");
            assertFalse(myDiary.isLocked());
    }

    @Test
    public void testThatAnEntryCanBeDeletedFromTheDiary(){
            myDiary.setPassword("1245");
            myDiary.lockDiary();
            myDiary.unlockDiary("1245");
            assertFalse(myDiary.isLocked());

            myDiary.createEntry("A day in my life", "I went to the beach");
            myDiary.createEntry("My Project", "I worked on my project");
            assertEquals(2, myDiary.getEntries().size());

            myDiary.deleteEntry(2);
            assertEquals(1, myDiary.getEntries().size());
    }


    @Test
    public void testToDeleteADiaryEntryThatDoesNotExist(){
        myDiary.setPassword("1245");
        myDiary.lockDiary();
        myDiary.unlockDiary("1245");

        assertFalse(myDiary.isLocked());

        myDiary.createEntry("A day in my life", "I went to the beach");
        myDiary.createEntry("My Project", "I worked on my project");

        myDiary.deleteEntry(3);

        assertEquals(2, myDiary.getEntries().size());
    }
    @Test
    public void testToFindAnEntryInDiaryByItsId(){
        myDiary.setPassword("1245");
        myDiary.lockDiary();
        myDiary.unlockDiary("1245");
        assertFalse(myDiary.isLocked());

        myDiary.createEntry("A day in my life", "I went to the beach");
        myDiary.createEntry("My Project", "I worked on my project");
        assertEquals(2, myDiary.getEntries().size());

        assertEquals(1, myDiary.getEntries().get(0).getId());
        assertEquals(2, myDiary.getEntries().get(1).getId());

        assertNotNull(myDiary.findEntryById(2));
        assertNull(myDiary.findEntryById(3));
    }

    @Test
    public void testThatDiaryEntryCanBeUpdated(){
        myDiary.setPassword("1245");
        myDiary.lockDiary();
        myDiary.unlockDiary("1245");
        assertFalse(myDiary.isLocked());

        myDiary.createEntry("A day in my life", "I went to the beach");
        myDiary.createEntry("My Project", "I worked on my project");
        assertEquals(2, myDiary.getEntries().size());

        myDiary.updateEntry(1,"A day in my life", "I traveled 6hrs to see my parents");
        assertNotNull(myDiary.findEntryById(2));
        assertNull(myDiary.findEntryById(3));
    }

    @Test
    public void testThatDiaryEntryCannotBeUpdatedIfItDoesNotExist(){
        myDiary.setPassword("1245");
        myDiary.lockDiary();
        myDiary.unlockDiary("1245");
        assertFalse(myDiary.isLocked());

        myDiary.createEntry("A day in my life", "I went to the beach");
        myDiary.createEntry("My Project", "I worked on my project");
        assertEquals(2, myDiary.getEntries().size());

        myDiary.updateEntry(3,"A day in my life", "I traveled 6hrs to see my parents");
        assertNotNull(myDiary.findEntryById(2));
        assertNull(myDiary.findEntryById(3));
    }
}