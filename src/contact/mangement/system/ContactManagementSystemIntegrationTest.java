package contact.mangement.system;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.List;

class ContactManagementSystemIntegrationTest {

    @Test
    void testIsDuplicateUsingReflection() throws Exception {
        // Create an instance of ContactManager and add a contact
        ContactManager manager = new ContactManager();
        GeneralContact contact = new GeneralContact("John Doe", "20123456789", "john.doe@example.com", "123 Street");
        manager.addContact(contact);

        // Use Reflection to access the private isDuplicate method
        Method isDuplicateMethod = ContactManager.class.getDeclaredMethod("isDuplicate", String.class, String.class);
        isDuplicateMethod.setAccessible(true); // Allow access to the private method

        // Test the reflection invocation for the "name" field
        boolean result = (boolean) isDuplicateMethod.invoke(manager, "name", "John Doe");
        assertAll("Check for existing contact",
                () -> assertTrue(result, "isDuplicate should return true for existing contact name")
        );

        // Test the reflection invocation for a non-existing contact
        boolean result2 = (boolean) isDuplicateMethod.invoke(manager, "name", "Jane Doe");
        assertAll("Check for non-existing contact",
                () -> assertFalse(result2, "isDuplicate should return false for non-existing contact name")
        );
    }

    @Test
    void testAddAndSearchContact() {
        ContactManager manager = new ContactManager();
        AbstractContact contact = new GeneralContact("Jane Doe", "20123456789", "jane.doe@example.com", "123 Street");
        manager.addContact(contact);

        List<AbstractContact> results = manager.getContacts();

        assertAll("Check for added contact",
                () -> assertEquals(1, results.stream()
                        .filter(c -> c.getName().equals("Jane Doe"))
                        .count(), "Only one 'Jane Doe' contact should exist.")
        );
    }

    @Test
    void testUpdateContact() {
        ContactManager manager = new ContactManager();
        GeneralContact contact = new GeneralContact("John Doe", "20123456789", "john.doe@example.com", "123 Street");
        manager.addContact(contact);
        manager.updateContact("John Doe");

        AbstractContact updatedContact = manager.getContacts().stream()
                .filter(c -> c.getName().equals("John Doe"))
                .findFirst()
                .orElse(null);

        assertAll("Check updated contact",
                () -> assertNotNull(updatedContact, "Updated contact should not be null"),
                () -> assertEquals("John Doe", updatedContact.getName(), "Contact name should remain the same after update")
        );
    }

    @Test
    void testDuplicateDetectionAcrossModules() {
        ContactManager manager = new ContactManager();
        GeneralContact contact = new GeneralContact("Jane Doe", "20123456789", "jane.doe@example.com", "123 Street");
        manager.addContact(contact);

        // Test for duplicate phone number
        boolean isDuplicate = manager.isDuplicate("phone", "20123456789");
        assertAll("Check duplicate detection",
                () -> assertTrue(isDuplicate, "The phone number should be detected as a duplicate.")
        );
    }
}
