import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class DoctorLoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DoctorManager doctorManager;
    private BackgroundPanel backgroundPanel;

    public DoctorLoginDialog(JFrame parentFrame, DoctorManager doctorManager, String backgroundImageName) {
        super(parentFrame, "Doctor Login", true);
        setSize(300, 150);

        backgroundPanel = new BackgroundPanel(backgroundImageName);
        setContentPane(backgroundPanel);

        setLocationRelativeTo(parentFrame);

        setLayout(new FlowLayout());

        this.doctorManager = doctorManager;

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if (doctorManager.signin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Logged in successfully!");
                    setVisible(false);
                    showOptionsDialog(username);
                    // Close the dialog after successful login
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                }
            }
        });

        add(new JLabel("Username: "));
        add(usernameField);
        add(new JLabel("Password: "));
        add(passwordField);
        add(loginButton);

        setVisible(true);
    }

    // ... (previous code)

    private void showOptionsDialog(String username) {
        JFrame optionsFrame = new JFrame("Doctor Options");
        optionsFrame.setSize(400, 200);
        optionsFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JButton updateUsernameButton = new JButton("Update Username");
        JButton updatePasswordButton = new JButton("Update Password");
        JButton showAppointmentsButton = new JButton("Show Appointments");
        JButton requestLeaveButton = new JButton("Request Leave");
        JButton showAllDoctorsButton = new JButton("Show All Doctors");
        JButton showAllPatientsButton = new JButton("Show All Patients");
        JButton showAllAppointmentsButton = new JButton("Show All Appointments");
        Scanner scanner = new Scanner(System.in);
        updateUsernameButton.addActionListener(e -> {
            // Handle updating username
              updateUsername(optionsFrame);
           // DoctorManager.updateusername(scanner);
        });

        updatePasswordButton.addActionListener(e -> {
            // Handle updating password
            updatePassword(optionsFrame);
        });

        showAppointmentsButton.addActionListener(e -> {
            // Handle showing appointments
            displayAllAppointments(optionsFrame);
        });

        requestLeaveButton.addActionListener(e -> {
            // Handle leave request
            requestleave(optionsFrame);
        });

        showAllDoctorsButton.addActionListener(e -> {
            displayAllDoctorsDialog(optionsFrame);


        });

        showAllPatientsButton.addActionListener(e -> {
            // Handle showing all patients
            displayAllPatientsDialog(optionsFrame);
        });

        showAllAppointmentsButton.addActionListener(e -> {
            Doctor loggedInDoctor = doctorManager.findDoctorByUsername(username);
            if (loggedInDoctor != null) {
                DoctorManager.displayAllAppointments(loggedInDoctor);
            } else {
                System.out.println("Logged-in doctor not found.");
            }
        });

        optionsFrame.add(updateUsernameButton);
        optionsFrame.add(updatePasswordButton);
        optionsFrame.add(showAppointmentsButton);
        optionsFrame.add(requestLeaveButton);
        optionsFrame.add(showAllDoctorsButton);
        optionsFrame.add(showAllPatientsButton);
        optionsFrame.add(showAllAppointmentsButton);

        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsFrame.setLocationRelativeTo(null);
        optionsFrame.setVisible(true);
    }
    private void requestleave(JFrame parentFrame){
        JOptionPane.showMessageDialog(null,"Your request is transfered");
    }
    private void updateUsername(JFrame parentFrame) {
        JDialog updateUsernameDialog = new JDialog(parentFrame, "Update Username", true);
        updateUsernameDialog.setSize(400, 250);
        updateUsernameDialog.setContentPane(new BackgroundPanel("docc.jpg"));
        updateUsernameDialog.setLayout(new FlowLayout());

        JLabel currentUsernameLabel = new JLabel("Enter your current username:");
        JTextField currentUsernameField = new JTextField(15);

        JLabel newUsernameLabel = new JLabel("Enter the new username:");
        usernameField = new JTextField(15);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentUsername = currentUsernameField.getText();
                String newUsername = usernameField.getText();

                if (!currentUsername.isEmpty() && !newUsername.isEmpty()) {
                    updateUsername(currentUsername, newUsername);
                    updateUsernameDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter both current and new usernames.");
                }
            }
        });

        updateUsernameDialog.add(currentUsernameLabel);
        updateUsernameDialog.add(currentUsernameField);
        updateUsernameDialog.add(newUsernameLabel);
        updateUsernameDialog.add(usernameField);
        updateUsernameDialog.add(updateButton);

        updateUsernameDialog.setVisible(true);
    }

    // Method to update username using the existing logic
    private void updateUsername(String currentUsername, String newUsername) {
        boolean usernameExists = false;
        ArrayList<Doctor> doc = FileOperationsDoctor.readAllFromFile();

        for (Doctor doctor : doc) {
            if (doctor.getUsername().equals(currentUsername)) {
                usernameExists = true;
                doctor.setUsername(newUsername);
                break; // Assuming there is only one doctor with the given username
            }
        }

        if (usernameExists) {
            JOptionPane.showMessageDialog(null,"Username updated successfully" );
        } else {
            JOptionPane.showMessageDialog(null,"You have entered Incorrect Username" );
        }
    }
    private void updatePassword(JFrame parentFrame) {
        JDialog updatePasswordDialog = new JDialog(parentFrame, "Update Password", true);
        updatePasswordDialog.setSize(400, 250);
        updatePasswordDialog.setContentPane(new BackgroundPanel("docc.jpg"));
        updatePasswordDialog.setLayout(new FlowLayout());

        JLabel currentPasswordLabel = new JLabel("Enter your current password:");
        JTextField currentPasswordField = new JTextField(15);
        JLabel newPasswordLabel = new JLabel("Enter your new password:");
        JTextField newPasswordField = new JTextField(15);
        JButton update = new JButton("Update");

        updatePasswordDialog.add(currentPasswordLabel);
        updatePasswordDialog.add(currentPasswordField);
        updatePasswordDialog.add(newPasswordLabel);
        updatePasswordDialog.add(newPasswordField);
        updatePasswordDialog.add(update);

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = currentPasswordField.getText();
                String newPassword = newPasswordField.getText();

                if (!oldPassword.isEmpty() && !newPassword.isEmpty()) {
                    updatePassword(parentFrame, oldPassword, newPassword);
                    updatePasswordDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter both current and new passwords.");
                }
            }
        });

        // Move this line here
        updatePasswordDialog.setVisible(true);
    }

        private void updatePassword(JFrame parentFrame,String oldp,String newp){
            ArrayList<Doctor> doctors =new ArrayList<>(FileOperationsDoctor.readAllFromFile());
            boolean found=false;
            for(Doctor doctor:doctors){
                if(doctor.getPassword().equals(oldp)){
                    found=true;
                    doctor.setPassword(newp);
                    break;
                }
            }
            if(found){

                JOptionPane.showMessageDialog(null,"Password updated successfully" );
            }
            else{
                JOptionPane.showMessageDialog(null,"You have entered Incorrect Password" );
            }
        }
    private void displayAllDoctorsDialog (JFrame parentFrame){
            JDialog allDoctorsDialog = new JDialog(parentFrame, "All Doctors", true);
            allDoctorsDialog.setSize(800, 500);

            // Set the background of the dialog
            allDoctorsDialog.setContentPane(new BackgroundPanel("docc.jpg"));

            DoctorManager doctorManager = new DoctorManager();
            String[] columnNames = {"Name", "Gender", "Mobile", "Email", "Address", "Specialization", "Institute"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // Add data to the table model
            for (Doctor doctor : doctorManager.getDoctorList()) {
                Object[] rowData = {doctor.getName(), doctor.getGender(), doctor.getMobilenumber(), doctor.getEmail(),
                        doctor.getAdress(), doctor.getSpecialization(), doctor.getInstitutespecialization()};
                model.addRow(rowData);
            }

            JTable doctorsTable = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(doctorsTable);

            allDoctorsDialog.add(scrollPane);
            allDoctorsDialog.setLocationRelativeTo(parentFrame);
            allDoctorsDialog.setVisible(true);
        }

        private void displayAllPatientsDialog (JFrame parentFrame){
            JDialog allPatientsDialog = new JDialog(parentFrame, "All Patients", true);
            allPatientsDialog.setSize(800, 500);

            // Set the background of the dialog
            allPatientsDialog.setContentPane(new BackgroundPanel("patient.jpg"));

            PatientManager patientManager = new PatientManager();
            String[] columnNames = {"Name", "Gender", "Mobile", "Email", "Address", "ID", "Appointment"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // Add data to the table model
            for (Patient patient : patientManager.getPatientList()) {
                Object[] rowData = {patient.getName(), patient.getGender(), patient.getMobilenumber(), patient.getEmail(),
                        patient.getAdress(), patient.getPatientID(), patient.getpatientappointments()};
                model.addRow(rowData);
            }

            JTable patientsTable = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(patientsTable);

            allPatientsDialog.add(scrollPane);
            allPatientsDialog.setLocationRelativeTo(parentFrame);
            allPatientsDialog.setVisible(true);
        }
    private void displayAllAppointments(JFrame parent) {
        JDialog appointmentsDialog = new JDialog(parent, "All Appointments", true);
        appointmentsDialog.setSize(800, 500);
        appointmentsDialog.setContentPane(new BackgroundPanel("docc.jpg"));

        String[] columnNames = {"Date", "Time", "Doctor ID", "Doctor Name", "Type of Doctor"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        ArrayList<Doctor> doctors = FileOperationsDoctor.readAllFromFile();
        Doctor currentDoc = new Doctor();

        for (Doctor doc : doctors) {
            if (doc.getUsername().equals(usernameField.getText())) {
                currentDoc = doc;
            }
        }

        for (Appointment appointment : currentDoc.getDoctorappointments()) {
            Object[] rowData = {appointment.getDate(), appointment.getTime(), appointment.getDoctorid(),
                    appointment.getDoctorname(), appointment.getTypeofdoctor()};
            model.addRow(rowData);
        }

        JTable appointmentsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(appointmentsTable);
        appointmentsDialog.add(scrollPane);

        appointmentsDialog.setVisible(true);
    }


        }
