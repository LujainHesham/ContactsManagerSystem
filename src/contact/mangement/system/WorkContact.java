package contact.mangement.system;
import java.util.Scanner;

public class WorkContact extends AbstractContact  {
    private String jobTitle;
    Scanner input = new Scanner(System.in);
    
    public WorkContact() {
        super();
        System.out.print("Enter job title: ");
        jobTitle = input.nextLine(); 
        setJobTitle(jobTitle);
    }
    public WorkContact(String name, String phoneNumber, String email, String address, String jobTitle) {
        super(name, phoneNumber, email, address);
        this.jobTitle = (jobTitle == null || jobTitle.isEmpty()) ? "Not Provided" : jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return super.toString() + ", Job Title: " + jobTitle;
    }
    

    
}