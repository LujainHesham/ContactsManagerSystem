package contact.mangement.system;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ContactManager {
    private final List<AbstractContact> contacts = new ArrayList<>();
    private final List<String> activityLogs = new ArrayList<>();    
    private final Scanner scanner = new Scanner(System.in);

    public ContactManager() {
        preloadContacts();
    }

    private void preloadContacts() {
        contacts.add(new GeneralContact("DR Mohamed Mostafa ElTaweel", "20124578968", "Mohamed.BestDR.DR@example.com", "123 AAST Street"));
        logActivity("Predefined contact 'DR Mohamed Mostafa ElTaweel'.");
        contacts.add(new GeneralContact("ENG Enjy Ehab", "20124578963", "Enjy.BestTA.TA@example.com", "1233 AAST Street"));
        logActivity("Predefined contact 'ENG Enjy Ehab'.");
        contacts.add(new GeneralContact("ENG Youssef ElKassaby", "20124578967", "Youssef.BestTA2.TA2@example.com", "12334 AAST Street"));
        logActivity("Predefined contact 'ENG Youssef ElKassaby'.");
    }
    public int countContacts() {
        return contacts.size();
    }

    
    public void addContact(AbstractContact contact) {
        contact.initializeContact(this, scanner); 
        contacts.add(contact); 
        logActivity("Added contact: " + contact.getName());
        System.out.println("Contact added successfully!");
    }

    public void updateContact(String name) {
        for (AbstractContact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println("What would you like to update?");
                System.out.println("1. Name\n2. Phone Number\n3. Email\n4. Address");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1 -> contact.setNameWithCheck(this, scanner);
                    case 2 -> contact.setPhoneNumberWithCheck(this, scanner);
                    case 3 -> contact.setEmailWithCheck(this, scanner);
                    case 4 -> {
                        System.out.print("Enter new address: ");
                        contact.setAddress(scanner.nextLine());
                    }
                    default -> System.out.println("Invalid choice.");
                }

                logActivity("Contact " + name + " updated.");
                System.out.println("Contact updated successfully!");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void deleteContact(String name) {
        if (contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name))) {
            logActivity("Contact " + name + " deleted.");
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public final void logActivity(String activity) {
        String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        activityLogs.add(timestamp + " - " + activity);
    }

    public void searchContact(String name) {
    while (true) {
        boolean found = false;

        for (AbstractContact contact : contacts) {
            if (contact.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(contact);
                found = true;
            }
        }

        if (found) {
            return;
        } else {
            System.out.println("No contact found containing the name: " + name);
            System.out.print("Enter another name to search (or type 'exit' to cancel): ");
            name = scanner.nextLine();

            if (name.equalsIgnoreCase("exit")) {
                System.out.println("Search canceled.");
                return;
            }
        }
    }
}


    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        for (AbstractContact contact : contacts) {
            System.out.println(contact);
        }
        logActivity("Viewed all contacts");
    }

    public void sortContactsByName() {
        contacts.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        logActivity("Sorted contacts by name");
        System.out.println("Contacts sorted by name.");
    }

    public void sortContactsByDateCreated() {
        contacts.sort((c1, c2) -> c1.getCreatedDate().compareTo(c2.getCreatedDate()));
        logActivity("Sorted contacts by created date");
        System.out.println("Contacts sorted by created date.");
    }

    public void sortContacts() {
        System.out.println("Choose sorting criteria:");
        System.out.println("1. Sort by Name\n2. Sort by Created Date");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> sortContactsByName();
            case 2 -> sortContactsByDateCreated();
            default -> System.out.println("Invalid choice.");
        }
    }

    public void displayLogs() {
        if (activityLogs.isEmpty()) {
            System.out.println("No activities logged.");
            return;
        }
        for (String log : activityLogs) {
            System.out.println(log);
        }
    }

    public List<AbstractContact> getContacts() {
        return contacts;
    }

    public void listContactsByType(Class<?> contactType) { 

    //This parameter allows the caller to specify a type of class (or subclass) of AbstractContact.

        System.out.println("Contacts of type: " + contactType.getSimpleName());
        for (AbstractContact contact : contacts) {
            if (contactType.isInstance(contact)) {
                System.out.println(contact);
            }
        }
    }

    public void listGeneralContacts() {
        listContactsByType(GeneralContact.class);
    }

    public void listFriendContacts() {
        listContactsByType(FriendContact.class);
    }

    public void listWorkContacts() {
        listContactsByType(WorkContact.class);
    }

    public void importContactsFromCSV(String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");

            if (fields.length < 4) {
                System.out.println("Invalid CSV format. Skipping line: " + line);
                continue;
            }

            String contactType = fields[fields.length - 1].trim().toLowerCase();
            String additionalField = fields.length > 5 ? fields[fields.length - 2].trim() : null;
            String name = fields[0].trim();
            String phoneNumber = fields[1].trim();
            String email = fields[2].trim();
            String address = fields[3].trim();

            if (name.isEmpty()) {
                System.out.println("Name is required. Skipping line: " + line);
                continue;
            }

            if (!isValidPhoneNumber(phoneNumber)) {
                System.out.println("Invalid phone number format. Skipping line: " + line);
                continue;
            }

            if (!email.isEmpty() && !isValidEmail(email)) {
                System.out.println("Invalid email format. Skipping line: " + line);
                continue;
            }

            if (isDuplicate("name", name) || isDuplicate("phone", phoneNumber) ||
                    (!email.isEmpty() && isDuplicate("email", email))) {
                System.out.println("Duplicate contact found. Skipping line: " + line);
                continue;
            }

            AbstractContact newContact = null;
            switch (contactType) {
                case "friend" -> {
                    LocalDate birthdate = null;
                    if (additionalField != null) {
                        try {
                            birthdate = LocalDate.parse(additionalField);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format for birthdate. Skipping line: " + line);
                            birthdate = null; // Skip setting birthdate
                        }
                    }
                    newContact = new FriendContact(name, phoneNumber, email, address, birthdate);
                }
                case "work" -> newContact = new WorkContact(name, phoneNumber, email, address,
                        additionalField != null ? additionalField : "Not Provided");
                case "general" -> newContact = new GeneralContact(name, phoneNumber, email, address);
                default -> System.out.println("Unknown contact type: " + contactType + ". Skipping line: " + line);
            }

            if (newContact != null) {
                contacts.add(newContact);
                logActivity("Imported Contact: " + name);
            }
        }

        System.out.println("Contacts imported successfully!");
    } catch (IOException e) {
        System.out.println("Error reading CSV file: " + e.getMessage());
    }
}


    public void exportContactsToCSV(String exportPath) {
    while (!exportPath.matches("^[a-zA-Z]:\\\\\\\\(?:[^\\\\\\\\/:*?\"<>|]+\\\\\\\\)*[^\\\\\\\\/:*?\"<>|]+\\.csv$")) {
        System.out.println("Invalid file path format. Please enter a valid file path ending with .csv (e.g., C:\\folder\\contacts.csv):");
        exportPath = scanner.nextLine();
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(exportPath))) {
        for (AbstractContact contact : contacts) {
            StringBuilder line = new StringBuilder();
            line.append(contact.getName()).append(",");
            line.append(contact.getPhoneNumber()).append(",");
            line.append(contact.getEmail()).append(",");
            line.append(contact.getAddress()).append(",");

            switch (contact) {
                case FriendContact friendContact -> {
                    line.append(friendContact.getBirthDate() != null ? friendContact.getBirthDate() : "").append(",");
                    line.append("friend");
                }
                case WorkContact workContact -> {
                    line.append(workContact.getJobTitle() != null ? workContact.getJobTitle() : "").append(",");
                    line.append("work");
                }
                default -> line.append("general");
            }

            writer.write(line.toString());
            writer.newLine();
        }
        System.out.println("Exporting in Progress ... ");
        System.out.println("Contacts exported successfully to: " + exportPath);
    } catch (IOException e) {
        System.out.println("Error exporting contacts: " + e.getMessage());
    }
}

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{11,15}");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    boolean isDuplicate(String fieldType, String value) {
        for (AbstractContact contact : contacts) {
            switch (fieldType) {
                case "name" -> {
                    if (contact.getName().equalsIgnoreCase(value)) return true;
                }
                case "phone" -> {
                    if (contact.getPhoneNumber().equals(value)) return true;
                }
                case "email" -> {
                    if (contact.getEmail().equalsIgnoreCase(value)) return true;
                }
            }
        }
        return false;
    }
}

                    
                       
