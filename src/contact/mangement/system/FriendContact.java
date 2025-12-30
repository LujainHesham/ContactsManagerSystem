package contact.mangement.system;
import java.time.LocalDate;
import java.util.Scanner;

public class FriendContact extends AbstractContact  {
    private LocalDate birthDate;

    public FriendContact(String name, String phoneNumber, String email, String address, LocalDate birthDate) {
        super(name, phoneNumber, email, address);
        this.birthDate = (birthDate != null) ? birthDate : LocalDate.of(0000, 1, 1);
    }
    
    public FriendContact() {
        super(); // Call Contact's constructor
        System.out.print("Do you want to enter the birthdate? (y/n): ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine().trim().toLowerCase();

        if (choice.equals("y")) {
            this.birthDate = inputBirthDate(input);
        } else {
            this.birthDate = null;
            System.out.println("Birthdate skipped.");
        }
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    private static LocalDate inputBirthDate(Scanner scanner) {
        LocalDate birthDate = null;
        while (birthDate == null) {
            try {
                System.out.print("Enter Year (e.g., 1990): ");
                int year = Integer.parseInt(scanner.nextLine().trim());

                System.out.print("Enter Month (1-12): ");
                int month = Integer.parseInt(scanner.nextLine().trim());

                System.out.print("Enter Day (1-31): ");
                int day = Integer.parseInt(scanner.nextLine().trim());

                birthDate = LocalDate.of(year, month, day);
            } catch (Exception e) {
                System.out.println("Invalid date. Please try again.");
            }
        }
        return birthDate;
    }
    public static LocalDate inputBirthDate() {
    Scanner scanner = new Scanner(System.in);
    LocalDate birthDate = null;

    while (birthDate == null) {
        try {
            System.out.print("Year (e.g., 1990): ");
            int year = scanner.nextInt();
            System.out.print("Month (1-12): ");
            int month = scanner.nextInt();
            System.out.print("Day (1-31): ");
            int day = scanner.nextInt();
            birthDate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            System.out.println("Invalid date. Please enter a valid birth date.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    return birthDate;
}


    // Override toString to include Birthdate
    @Override
    public String toString() {
        return super.toString() + (birthDate != null ? ", Birth Date: " + birthDate : ", Not Provided");
    }
}
