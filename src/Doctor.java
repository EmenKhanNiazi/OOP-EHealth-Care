import java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends Person implements User {

    private static ArrayList<Doctor> docList = new ArrayList<>();
    private ArrayList<Appointment> doctorappointments = new ArrayList<>();
    private String username;
    private String password;
    private String specialization;
    private String institutespecialization;

    Doctor() {}

    Doctor(String n, String g, String m, String e, String a, String s, String is, String u, String p) {
        super(n, g, m, e, a);
        this.specialization = s;
        this.institutespecialization = is;
        this.username = u;
        this.password = p;
    }

    @Override
    public void signup() {
        // Implement signup logic here
    }

    @Override
    public boolean signin(String username, String password) {
        // Implement signin logic here
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSpecialization: " + specialization +
                "\nFrom: " + institutespecialization + "\nUserame" + username + "\nPassword" + password;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getInstitutespecialization() {
        return institutespecialization;
    }

    public void setInstitutespecialization(String institutespecialization) {
        this.institutespecialization = institutespecialization;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addAppointment(Appointment appointment) {
        doctorappointments.add(appointment);
    }

    public ArrayList<Appointment> getDoctorappointments() {
        return doctorappointments;
    }
}

// Doctor Manager
class DoctorManager {
    private ArrayList<Doctor> doctorList;

    DoctorManager() {
        // Initialize the doctorList by reading from the file
        doctorList = FileOperationsDoctor.readAllFromFile();
        if (doctorList == null) {
            doctorList = new ArrayList<>();
        }
    }

    public ArrayList<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(ArrayList<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public void signup() {
        // Implement signup logic here
    }

    public boolean signin(String username, String password) {
        ArrayList<Doctor> doctors = FileOperationsDoctor.readAllFromFile();
        for (Doctor doctor : doctors) {
            if (doctor.getUsername().equals(username) && doctor.getPassword().equals(password)) {
                return true;
            }
        }
        System.out.println("Doctor signin failed. Invalid credentials.");
        return false;
    }

    public void displayAllDoctors() {
        System.out.println("All Doctors:");
        ArrayList<Doctor> doc = FileOperationsDoctor.readAllFromFile();
        for (Doctor doctor : doc) {
            System.out.println(doctor.toString());
            System.out.println("---------------------------------");
        }
    }

    public static void displayAllAppointments(Doctor doctor) {
        System.out.println("Displaying All appointments of the logged in Doctor");
        for (Appointment appointment : doctor.getDoctorappointments()) {
            System.out.println("Type of Doctor: " + appointment.getTypeofdoctor() +
                    ", Doctor ID: " + appointment.getDoctorid() +
                    ", Time: " + appointment.getTime() +
                    ", Date: " + appointment.getDate());
        }
    }

    public static void updateusername(Scanner scanner) {
        // Implement updateusername logic here
    }

    public static void requestleave(Scanner scanner) {
        // Implement requestleave logic here
    }

    public static void updatepassword(Scanner scanner) {
        // Implement updatepassword logic here
    }

    public static Doctor findDoctorByUsername(String username) {
        ArrayList<Doctor> doctors = FileOperationsDoctor.readAllFromFile();
        for (Doctor doctor : doctors) {
            if (doctor.getUsername().equals(username)) {
                return doctor;
            }
        }
        return null; // If not found
    }

    public static void doctorMenu(Scanner scanner, DoctorManager doctorManager, PatientManager patientManager) {
        // Implement doctorMenu logic here
    }

    private static void doctorSignIn(Scanner scanner, DoctorManager doctorManager, PatientManager patientManager) {
        // Implement doctorSignIn logic here
    }
}

class DoctorWrapper {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DoctorManager doctorManager = new DoctorManager();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Doctor's Menu");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");

            choice = getChoice();

            switch (choice) {
                case 1:
                    doctorSignIn();
                    break;
                case 2:
                    doctorManager.signup();
                    break;
                case 3:
                    System.out.println("Exiting Doctor Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 3);
    }

    private static int getChoice() {
        System.out.print("Enter your choice: ");
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Consume the newline character
            return 0;
        }
    }

    private static void doctorSignIn() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (doctorManager.signin(username, password)) {
            System.out.println("Successful login! Proceeding with the program.");
            doctorDashboard();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void doctorDashboard() {
        int choice;
        do {
            System.out.println("Doctor's Dashboard");
            System.out.println("1. Update Username");
            System.out.println("2. Update Password");
            System.out.println("3. Show All Doctors");
            System.out.println("4. Exit");

            choice = getChoice();

            switch (choice) {
                case 1:
                    System.out.println("Updating username:");
                    DoctorManager.updateusername(scanner);
                    break;
                case 2:
                    System.out.println("Updating password:");
                    DoctorManager.updatepassword(scanner);
                    break;
                case 3:
                    doctorManager.displayAllDoctors();
                    break;
                case 4:
                    System.out.println("Exiting Doctor Dashboard.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 4);
    }
}
