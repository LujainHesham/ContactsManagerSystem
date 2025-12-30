package contact.mangement.system;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;


public abstract class AbstractContact {

    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private final Date createdDate;

    public AbstractContact() {
        this.createdDate = new Date();
    }

    public AbstractContact(String name, String phoneNumber, String email, String address) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }   
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = (email == null || email.isEmpty()) ? "Not Provided" : email; // Default email
        this.address = (address == null || address.isEmpty()) ? "Not Provided" : address; // Default address
        this.createdDate = new Date();
    }

    public Date getCreatedDate() { return createdDate; }

    public String getName() { return name; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getEmail() { return email; }

    public String getAddress() { return address; }

    @Override
    public String toString() {
        return "Name: " + name +
               ", Phone: " + phoneNumber +
               ", Email: " + email +
               ", Address: " + address ;
    }

    public void initializeContact(ContactManager manager, Scanner scanner) {
        setNameWithCheck(manager, scanner);
        setPhoneNumberWithCheck(manager, scanner);
        setEmailWithCheck(manager, scanner);

        System.out.print("Enter address (or press Enter to skip): ");
        String inputAddress = scanner.nextLine();
        this.address = (inputAddress.isEmpty()) ? "Not Provided" : inputAddress;
    }
    
    public void setAddress(String address) { this.address = address; }

    public void setNameWithCheck(ContactManager manager, Scanner scanner) {
        while (true) {
            System.out.print("Enter name: ");
            String inputName = scanner.nextLine().trim(); 
            //The trim() method in Java is used to remove any leading and trailing whitespace characters 

            if (inputName.isEmpty()) { 
                System.out.println("Name cannot be empty or blank. Please enter a valid name.");
                continue; 
            }

            if (!isDuplicate(manager, "name", inputName)) {
                this.name = inputName;
                break;
            } else {
                System.out.println("A contact with this name already exists. Please try again.");
            }
        }
    }


    public void setPhoneNumberWithCheck(ContactManager manager, Scanner scanner) {
        while (true) {
            System.out.print("Enter phone number: ");
            String inputPhone = scanner.nextLine();

            if (!isValidPhoneNumber(inputPhone)) {
                System.out.println("Invalid phone number. Must be 11 to 15 digits");
                continue;
            }

            if (!isDuplicate(manager, "phone", inputPhone)) {
                this.phoneNumber = inputPhone;
                break;
            } else {
                System.out.println("A contact with this phone number already exists. Please try again.");
            }
        }
    }


    public void setEmailWithCheck(ContactManager manager, Scanner scanner) {
        while (true) {
            System.out.print("Enter email (or press Enter to skip): ");
            String inputEmail = scanner.nextLine();

            if (inputEmail.isEmpty()) {
                this.email = "Not Provided";
                break;
            }

            if (!isValidEmail(inputEmail)) {
                System.out.println("Invalid email format.");
                continue;
            }

            if (!isDuplicate(manager, "email", inputEmail)) {
                this.email = inputEmail;
                break;
            } else {
                System.out.println("A contact with this email already exists. Please try again.");
            }
        }
    }

    public boolean isDuplicate(ContactManager manager, String fieldType, String value) {
        for (AbstractContact contact : manager.getContacts()) {
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

    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{11,15}");
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
}
