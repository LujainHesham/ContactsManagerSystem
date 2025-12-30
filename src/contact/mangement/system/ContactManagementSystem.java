package contact.mangement.system;
import java.util.*;

public class ContactManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager contactManager = new ContactManager();

        System.out.println("Total number of preloaded contacts: " + contactManager.countContacts());
        System.out.println("Preloaded contacts:");
        for (AbstractContact contact : contactManager.getContacts()) {
            System.out.println(contact);
        }
        
        while (true) {
            System.out.println("\n1. Add General Contact\n2. Add Friend Contact\n3. Add Work Contact\n4. View Contacts\n5. Update Contact\n6. Delete Contact\n7. View Logs\n8. Search Contact\n9. Sort Contacts\n10. List General Contacts\n11. List Friend Contacts\n12. List Work Contacts\n13. Total Number of Contacts\n14. Import Contacts from CSV File\n15. Export Contacts to CSV File\n16. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 10.");
                scanner.nextLine();
                continue;
            }
            

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> contactManager.addContact(new GeneralContact());
                case 2 -> contactManager.addContact(new FriendContact());
                case 3 -> contactManager.addContact(new WorkContact());
                case 4 -> contactManager.viewContacts();
                
                case 5 -> {
                    
                    System.out.print("Enter name to update: ");
                    String name = scanner.nextLine();
                    contactManager.updateContact(name);
                }
                case 6 -> {
                    
                    System.out.print("Enter name to delete: ");
                    String name = scanner.nextLine();
                    contactManager.deleteContact(name);
                }
                case 7 -> contactManager.displayLogs();
                
                case 8 -> {
                    
                    System.out.print("Search by Name: ");
                    String name = scanner.nextLine();
                    contactManager.searchContact(name);
                }
                case 9 -> contactManager.sortContacts();
                

                
                case 10 -> contactManager.listGeneralContacts();
                case 11 -> contactManager.listFriendContacts();
                case 12 -> contactManager.listWorkContacts();
                
                case 13 -> {System.out.println("Total Number of Contacts: " + contactManager.countContacts());}
                
                case 14 -> {
                    System.out.print("Enter CSV file path to import from: ");
                    String filePath = scanner.nextLine();
                    contactManager.importContactsFromCSV(filePath);
                }
                
                case 15 -> {
                    System.out.println("To Enter CSV file path to export to :   ");
                    System.out.println("Please ensure that file paths use double backslashes");
                    System.out.println("e.g.,( C:\\\\Users\\\\YourName\\\\contacts.csv ).");
                    System.out.println("Note: The last part of the path (e.g., 'contacts.csv') is the filename that will be created or modified.");
                    System.out.println("");
                    System.out.println("Enter CSV file path to export to:   ");

                    String exportPath = scanner.nextLine();
                    contactManager.exportContactsToCSV(exportPath);
                }
                
                
                
                case 16 -> {
                    
                    System.out.println("Exiting... Thank You for Testing Out Our System!");
                    System.out.println("Developed by:");
                    System.out.println("Lujain Hesham");
                    System.out.println("Lojain Maged");
                    System.out.println("Habiba Ahmed Saber");
                    return;
                }

                

                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
