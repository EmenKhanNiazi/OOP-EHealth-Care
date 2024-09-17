import java.util.ArrayList;
import java.util.Scanner;

public class Patient extends Person implements User {

    private static ArrayList<Patient> patientList = new ArrayList<>();
    private  ArrayList<Appointment> patientappointments = new ArrayList<>();
    private String patientID;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    Patient(){}
//    Patient(String n, String g, String m, String e, String a, String pID,String dID, String d, String t, String dname, String specialization,String u,String p) {
    Patient(String n, String g, String m, String e, String a, String pID, String u,String p) {
        super(n, g, m, e, a);
        this.patientID = pID;
        this.username=u;
        this.password=p;
//        // Create and set an appointment for the patient
//        Appointment appointment = new Appointment();
//        appointment.setDate(d);
//        appointment.setTime(t);
//        appointment.setDoctorname(dname);
//        appointment.setTypeofdoctor(specialization);
//        this.patientappointments.add(appointment);

    }

    public static ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public static void setPatientList(ArrayList<Patient> patientList) {
        Patient.patientList = patientList;
    }

    public ArrayList<Appointment> getPatientappointments() {
        return patientappointments;
    }

    public void setPatientappointments(ArrayList<Appointment> patientappointments) {
        this.patientappointments = patientappointments;
    }

    void addpatient(String n, String g, String m, String e, String a, String pID, String dID, String d, String t, String dname, String specialization) {
        patientList.add(new Patient(n, g, m, e, a, pID ,username,password));
    }

    @Override
    public void signup() {
        // Implement Doctor signup logic
        // Add new doctor to doctorList or perform other necessary actions
    }

    @Override
    public boolean signin(String username, String password) {
        // Implement Doctor signin logic
        // Check credentials against doctorList or perform other necessary actions
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString() +
                "\nPatient ID: " + patientID +
                "\nAppointment Schedule:");

        for (Appointment appointment : patientappointments) {
            result.append("\nDate: ").append(appointment.getDate())
                    .append("\nTime: ").append(appointment.getTime())
                    .append("\nDoctor's Name: ").append(appointment.getDoctorname())
                    .append("\nType Of Doctor: ").append(appointment.getTypeofdoctor())
                    .append("\n---------------");
        }

        return result.toString();
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
    public void addAppointment(Appointment appointment) {
        patientappointments.add(appointment);
    }

    public ArrayList<Appointment> getpatientappointments() {
        return patientappointments;
    }
    public void changeAppointmentDay(Scanner scanner) {
        System.out.println("Enter the details for changing the appointment day:");

        System.out.print("Enter the current appointment date: ");
        String currentDate = scanner.nextLine();

        // Assuming the patient can have multiple appointments and we need to find the correct one
        Appointment targetAppointment = findAppointmentByDate(currentDate);

        if (targetAppointment != null) {
            System.out.print("Enter the new appointment date: ");
            String newDate = scanner.nextLine();

            // Perform the update in the patient's appointment list
            targetAppointment.setDate(newDate);

            // Update the appointment date for the corresponding doctor
            ArrayList<Doctor> doctors = FileOperationsDoctor.readAllFromFile();
            for (Doctor doctor : doctors) {
                for (Appointment docAppointment : doctor.getDoctorappointments()) {
                    if (docAppointment.equals(targetAppointment)) {
                        docAppointment.setDate(newDate);
                        break; // Assuming each appointment is unique, exit the loop after finding the match
                    }
                }
            }

            System.out.println("Appointment day successfully changed!");
        } else {
            System.out.println("No appointment found for the given date.");
        }
    }

    private Appointment findAppointmentByDate(String date) {
        for (Appointment appointment : patientappointments) {
            if (appointment.getDate().equals(date)) {
                return appointment;
            }
        }
        return null; // If not found
    }
}
class PatientManager{
    private ArrayList<Patient> patientList;
    PatientManager(){

        // Initialize the doctorList by reading from the file
        patientList = FileOperationsPatient.readAllFromFile();
        if (patientList == null) {
            patientList = new ArrayList<>();
        }

    }
    public void signup() {
        Patient patient=new Patient();
        // Example: Prompt for Doctor details and call Doctor.signup()
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Patient's  ID");
        String pID=sc.nextLine();
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter Mobile Number: ");
        String mobile=sc.nextLine();
        System.out.print("Enter Email: ");
        String email=sc.nextLine();
        System.out.print("Enter Adress:");
        String adress=sc.nextLine();
        System.out.print("What date would you like to schedule your appointment");
        String date=sc.nextLine();
        System.out.print("Enter the time for that:");
        String time=sc.nextLine();
        System.out.print("Enter Doctor's ID");
        String did=sc.nextLine();
        System.out.print("Enter Doctor's Name");
        String dname=sc.nextLine();
        System.out.println("Enter Doctor's Specialization: ");

        ArrayList<Doctor> doc =  FileOperationsDoctor.readAllFromFile();
        for (Doctor doctor2 : doc) {
            System.out.println(doctor2.getSpecialization());
        }
        String specialization = sc.nextLine();
        // Add the appointment to the patient's list
        Appointment appointment = new Appointment(date,did, time, dname, specialization);
        patient.addAppointment(appointment);

        ArrayList<Doctor> doc2 =  FileOperationsDoctor.readAllFromFile();
        for (Doctor doctor2 : doc2) {
            if(doctor2.getSpecialization().equals(specialization)){
                doctor2.addAppointment(appointment);
            }
        }
        System.out.print("Enter username: ");
        String username=sc.nextLine();
        System.out.print("Enter password: ");
        String password=sc.nextLine();
        //String n,String g, String m,String e,String a,String u,String p,int pID,int dID,String d,String t,String dname,String specialization
        Patient patient1=new Patient(name,gender,mobile,email,adress,pID ,username,password);
        patientList.add(patient1);
        FileOperationsPatient.writeToFile(patientList);
        System.out.println("Patient signed up successfully!");

        // never close the Scanner
    }

    public boolean signin(String username, String password) {
        ArrayList<Patient> patientList =  FileOperationsPatient.readAllFromFile();
        for (Patient patient : patientList) {
            if (patient.getUsername().equals(username) && patient.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    public ArrayList<Patient> getPatientList() {
        return patientList;
    }
    public void displayingAllPatients(){
        System.out.println("All Patients:");
        ArrayList<Patient> patients =  FileOperationsPatient.readAllFromFile();
        for (Patient patient : patients) {
            System.out.println(patient.toString());
        }
    }
    public static void updateusername(Scanner scanner){  ////also check if the username is  already taken by someone?
        System.out.println("Enter your current username: ");
        String curr_name=scanner.nextLine();
        boolean b=false; //boolean to check if the username already exists  or not
        ArrayList<Patient> patients =  FileOperationsPatient.readAllFromFile();
        for (Patient patient : patients) {
            System.out.println(patient.toString());
            if(patient.getUsername().equals(curr_name)){
                b=true; //name found
                System.out.println("Enter the new username: ");
                String new_name= scanner.nextLine();
                patient.setUsername(new_name);
            }
        }
        if(b==false){
            System.out.println("Username doesnt exists already");
        }

    }
    public static void displayAllAppointments(Patient patient){
        System.out.println("Displaying All appointments of the logged in patient");
        for(Appointment appointment: patient.getpatientappointments()){
            System.out.println(appointment.getTypeofdoctor()+
                    appointment.getDoctorid()+appointment.getTime()+appointment.getDate());
        }
    }
    public static void  addAppointment(Scanner sc,Patient patient){
        System.out.print("What date would you like to schedule your appointment");
        String date = sc.nextLine();
        System.out.print("Enter the time for that:");
        String time = sc.nextLine();
        System.out.print("Enter Doctor's Name");
        String dname = sc.nextLine();
        System.out.print("Enter Patient's  ID");
        String pID=sc.nextLine();
        System.out.print("Enter Doctor's Specialization from below: ");

        ArrayList<Doctor> doc =  FileOperationsDoctor.readAllFromFile();
        for (Doctor doctor2 : doc) {
            System.out.println(doctor2.getSpecialization());
        }
        String specialization = sc.next();
        // Add the appointment to the patient's list
        Appointment appointment = new Appointment(date,pID, time, dname, specialization);
        patient.addAppointment(appointment);

        ArrayList<Doctor> doc2 =  FileOperationsDoctor.readAllFromFile();
        for (Doctor doctor2 : doc2) {
            if(doctor2.getSpecialization().equals(specialization)){
                doctor2.addAppointment(appointment);
            }
        }
     }
    public static void updatepassword(Scanner scanner){  ////also check if the username is  already taken by someone?
        System.out.println("Enter your current username: ");
        String curr_name=scanner.nextLine();
        System.out.println("Enter your current password: ");
        String curr_pass=scanner.nextLine();
        boolean b=false; //boolean to check if the username already exists  or not
        ArrayList<Patient> patients =  FileOperationsPatient.readAllFromFile();
        for (Patient patient : patients) {
            System.out.println(patient.toString());
            if(patient.getUsername().equals(curr_name) && patient.getPassword().equals(curr_pass)){
                b=true; //name found
                System.out.println("Enter the new password: ");
                String new_pass= scanner.nextLine();
                patient.setPassword(new_pass);
            }
        }
        if(b==false){
            System.out.println("Username or Password doesnt exists already");
        }
    }
    public static void patientMenu(Scanner scanner, DoctorManager doctorManager, PatientManager patientManager) {
        int choice;
        do {
            System.out.println("Patient's Menu");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
                choice = 0;
            }

            switch (choice) {
                case 1:
                    patientSignIn(scanner, doctorManager, patientManager);
                    break;
                case 2:
                    patientManager.signup();
                    break;
                case 3:
                    System.out.println("Exiting Admin Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 3);
    }

    private static void patientSignIn(Scanner scanner, DoctorManager doctorManager, PatientManager patientManager) {
        // If admin is already signed up, allow sign-in
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            System.out.print("Enter username: ");
            String user = scanner.nextLine();
            System.out.print("Enter password: ");
            String pass = scanner.nextLine();

            if (patientManager.signin(user, pass)) {
                System.out.println("Successful login! Proceeding with the program.");
                Patient loggedinpatient=new Patient(); //an instance of the signned in Patient
                int choice2;
                do {
                    System.out.println("1.Update Username");
                    System.out.println("2.Update Password");
                    System.out.println("3.Add an Appointment");
                    System.out.println("4.Request for Change in Appointment Day");
                    System.out.println("5.Show All Patients");
                    System.out.println("6.Show All Appointments");
                    System.out.println("7.Exit");

                    try {
                        choice2 = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.nextLine(); // Consume the invalid input
                        choice2 = 0;
                    }

                    System.out.println("Choice2: " + choice2);

                    switch (choice2) {
                        case 1:
                            System.out.println("You want to update your username: ");
                            updateusername(scanner);
                            break;
                        case 2:  System.out.println("You want to update your password: ");
                            updatepassword(scanner);
                            break;
                        case 3:
                            addAppointment(scanner,loggedinpatient);
                            break;
                        case 4:
                            loggedinpatient.changeAppointmentDay(scanner);
                            break;
                        case 5:
                            patientManager.displayingAllPatients();
                            break;
                        case 6:
                            displayAllAppointments(loggedinpatient);
                            break;
                        case 7:
                            System.out.println("Exiting Admin Menu.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                } while (choice2 != 7);

            } else {
                System.out.println("Invalid username or password. Please try again.");
                attempts++;
            }
        }

        System.out.println("Maximum login attempts reached. Exiting Admin Menu.");
    }
}


