import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Person {
    private static ArrayList<Admin> adminList = new ArrayList<>();
    private String username;
    private String password;
    private HealthCareGUI.BackgroundPanel backgroundPanel;
    Admin(String n, String g, String m, String e, String a, String u, String p) {
        super(n, g, m, e, a);
        this.username = u;
        this.password = p;
    }

    Admin() {
    }

    public static ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public static void setAdminList(ArrayList<Admin> adminList) {
        Admin.adminList = adminList;
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
    public static Admin signup() {
        Scanner s1 = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = s1.nextLine();
        System.out.print("Enter Gender: ");
        String gender = s1.nextLine();
        System.out.print("Enter Mobile Number: ");
        String mobile = s1.nextLine();
        System.out.print("Enter Email: ");
        String email = s1.nextLine();
        System.out.print("Enter Address:");
        String address = s1.nextLine();

        System.out.print("Enter username: ");
        String username = s1.nextLine();
        System.out.print("Enter password: ");
        String password = s1.nextLine();

        Admin newAdmin = new Admin(name, gender, mobile, email, address, username, password);
        adminList.add(newAdmin);
        FileOperationsAdmin.writeToFile(adminList);
        System.out.println("Admin signed up successfully!");

        return newAdmin;
    }

    public static boolean signin(String username, String password) {
        ArrayList<Admin> admins =  FileOperationsAdmin.readAllFromFile();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void adminMenu(Scanner scanner, DoctorManager doctorManager, PatientManager patientManager) {
        int choice;
        do {
            System.out.println("Admin Menu");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
                choice = 0;
            }

            switch (choice) {
                case 1:
                    adminSignIn(scanner, doctorManager, patientManager);
                    break;
                case 2:
                    signup();
                    break;
                case 3:
                    System.out.println("Exiting Admin Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 3);
    }

    private static void adminSignIn(Scanner scanner, DoctorManager doctorManager, PatientManager patientManager) {

        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            System.out.print("Enter username: ");
            String user = scanner.nextLine();
            System.out.print("Enter password: ");
            String pass = scanner.nextLine();

            if (signin(user, pass)) {
                System.out.println("Successful login! Proceeding with the program.");

                int choice2;
                do {
                    System.out.println("1.Add new Doctor");
                    System.out.println("2.Add new Patient");
                    System.out.println("3.Add new Nurse");
                    System.out.println("4.Show All Doctors");
                    System.out.println("5.Show All Patients");
                    System.out.println("6.Show All Appointments");
                    System.out.println("7.Show All Nurses");
                    System.out.println("8.Exit");

                    try {
                        choice2 = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.nextLine();
                        choice2 = 0;
                    }

                    System.out.println("Choice2: " + choice2);

                    switch (choice2) {
                        case 1:
                            doctorManager.signup();
                            break;
                        case 2:
                            patientManager.signup();
                            break;
                        case 3:
                            // Handle nurse creation
                            break;
                        case 4:
                            doctorManager.displayAllDoctors();
                            break;
                        case 5:
                            patientManager.displayingAllPatients();
                            break;
                        case 6:
                            // Handle displaying all appointments
                            break;
                        case 7:
                            // Handle displaying all nurses
                            break;
                        case 8:
                            System.out.println("Exiting Admin Menu.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                } while (choice2 != 8);

            } else {
                System.out.println("Invalid username or password. Please try again.");
                attempts++;
            }
        }

        System.out.println("Maximum login attempts reached. Exiting Admin Menu.");
    }
}