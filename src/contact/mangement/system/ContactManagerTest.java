package contact.mangement.system;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {

    @Test
    void testIsDuplicateUsingReflection() throws Exception {
        ContactManager manager = new ContactManager();
        manager.addContact(new GeneralContact("John Doe", "20123456789", "john.doe@example.com", "123 Street"));

        // Use Reflection to access the private isDuplicate method
        Method isDuplicateMethod = ContactManager.class.getDeclaredMethod("isDuplicate", String.class, String.class);
        isDuplicateMethod.setAccessible(true);

        // Invoke the private method and assert the result
        boolean result = (boolean) isDuplicateMethod.invoke(manager, "name", "John Doe");
        assertTrue(result, "isDuplicate should return true for existing contact name");

        boolean result2 = (boolean) isDuplicateMethod.invoke(manager, "name", "Jane Doe");
        assertFalse(result2, "isDuplicate should return false for non-existing contact name");
    }

    @Test
    void testAddContact() {
        ContactManager manager = new ContactManager();
        int initialCount = manager.countContacts();
        manager.addContact(new GeneralContact("John Doe", "20123456789", "john.doe@example.com", "123 Street"));
        assertEquals(initialCount + 1, manager.countContacts());
    }

    @Test
    void testDeleteContact() {
        ContactManager manager = new ContactManager();
        manager.addContact(new GeneralContact("John Doe", "20123456789", "john.doe@example.com", "123 Street"));
        manager.deleteContact("John Doe");
        assertNull(manager.getContacts().stream()
                .filter(contact -> contact.getName().equals("John Doe"))
                .findFirst()
                .orElse(null));
    }

    @Test
    void testAddContact_CheckDuplicateByName() {
        ContactManager manager = new ContactManager();
        manager.addContact(new GeneralContact("John Doe", "20123456789", "john.doe@example.com", "123 Street"));

        // Attempt to add a duplicate contact by name
        manager.addContact(new GeneralContact("John Doe", "20198765432", "jane.doe@example.com", "456 Avenue"));
        assertEquals(1, manager.countContacts(), "Duplicate contact by name should not be added");
    }

    @Test
    void testAddContact_CheckDuplicateByPhone() {
        ContactManager manager = new ContactManager();
        manager.addContact(new GeneralContact("John Doe", "20123456789", "john.doe@example.com", "123 Street"));

        // Attempt to add a duplicate contact by phone
        manager.addContact(new GeneralContact("Jane Doe", "20123456789", "jane.doe@example.com", "456 Avenue"));
        assertEquals(1, manager.countContacts(), "Duplicate contact by phone should not be added");
    }

    @Test
    void testViewContacts() {
        ContactManager manager = new ContactManager();
        manager.addContact(new GeneralContact("John Doe", "20123456789", "john.doe@example.com", "123 Street"));
        assertEquals(1, manager.getContacts().size());
    }

    @Test
    void testImportContactsFromCSV() {
        ContactManager manager = new ContactManager();
        String csvFilePath = "test_contacts.csv"; // Create this file with valid test data
        manager.importContactsFromCSV(csvFilePath);
        assertTrue(manager.countContacts() > 0);
    }

    @Test
    void testExportContactsToCSV() {
        ContactManager manager = new ContactManager();
        manager.addContact(new GeneralContact("John Doe", "20123456789", "john.doe@example.com", "123 Street"));
        String csvFilePath = "exported_contacts.csv";
        manager.exportContactsToCSV(csvFilePath);
        File file = new File(csvFilePath);
        assertTrue(file.exists());
    }
}
