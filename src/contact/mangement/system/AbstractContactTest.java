package contact.mangement.system;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AbstractContactTest {

    @Test
    void testValidPhoneNumber() {
        AbstractContact contact = new GeneralContact("John Doe", "20123456789", "john.doe@example.com", "123 Street");
        assertTrue(contact.isValidPhoneNumber("20123456789"));
        assertFalse(contact.isValidPhoneNumber("123")); // Too short
        assertFalse(contact.isValidPhoneNumber("12345678901234567890")); // Too long
    }

    @Test
    void testValidEmail() {
        AbstractContact contact = new GeneralContact("John Doe", "20123456789", "john.doe@example.com", "123 Street");
        assertTrue(contact.isValidEmail("valid.email@example.com"));
        assertFalse(contact.isValidEmail("invalid-email")); // Missing domain
        assertFalse(contact.isValidEmail("@example.com")); // Missing username
    }

    @Test
    void testDefaultValues() {
        AbstractContact contact = new GeneralContact("John Doe", "20123456789", "", "");
        assertEquals("Not Provided", contact.getEmail());
        assertEquals("Not Provided", contact.getAddress());
    }

    @Test
    void testSetNameWithCheck() {
        ContactManager manager = new ContactManager();
        AbstractContact contact = new GeneralContact();
        contact.setNameWithCheck(manager, new Scanner("New Name\n"));
        assertEquals("New Name", contact.getName());
    }
}
