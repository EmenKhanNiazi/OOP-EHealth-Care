import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {

            HealthCareGUI healthCareGUI = new HealthCareGUI();

            healthCareGUI.setVisible(true);
        });

        Admin admin = null;
        DoctorManager doctorManager = new DoctorManager();
        PatientManager patientManager = new PatientManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome to E-HEALTH CARE SYSTEM");
            System.out.println("1. Admin");
            System.out.println("2. Doctor");
            System.out.println("3. Patient");
            System.out.println("4. Exit");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consuming the newline character
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consuming the invalid input
                choice = 0;
            }

            switch (choice) {
                case 1:
                    //making admin menu static so it is easily accessible
                    Admin.adminMenu(scanner,doctorManager,patientManager);
                    break;

                case 2:
                    DoctorManager.doctorMenu(scanner,doctorManager,patientManager);
                    break;
                case 3:
                    PatientManager.patientMenu(scanner,doctorManager,patientManager);
                    break;
                case 4:
                    System.out.println("Exiting E-HEALTH CARE SYSTEM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 4);

        // Closing the Scanner when done
        scanner.close();
    }
}
