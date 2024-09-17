import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PatientLoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private PatientManager patientManager;
    private BackgroundPanel backgroundPanel;

    public PatientLoginDialog(JFrame parentFrame, PatientManager patientManager, String backgroundImageName) {
        super(parentFrame, "Patient Login", true);
        setSize(300, 150);

        backgroundPanel = new BackgroundPanel(backgroundImageName);
        setContentPane(backgroundPanel);
        setLocationRelativeTo(parentFrame);

        setLayout(new FlowLayout());

        this.patientManager = patientManager;

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if (patientManager.signin(username, password)) {
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

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void showOptionsDialog(String username) {
        JFrame optionsFrame = new JFrame("Patient Options");
        optionsFrame.setSize(400, 200);
        optionsFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JButton updateUsernameButton = new JButton("Update Username");
        JButton updatePasswordButton = new JButton("Update Password");
        JButton addAppointmentButton = new JButton("Add Appointment");
        JButton showAllPatientsButton = new JButton("Show All Patients");


        updateUsernameButton.addActionListener(e -> {
            // Handle updating username
//            PatientManager.updateusername(new Scanner(System.in));
            updateUsername(optionsFrame);

        });

        updatePasswordButton.addActionListener(e -> {
            // Handle updating password
            //          PatientManager.updatepassword(new Scanner(System.in));
            updatePassword(optionsFrame);
        });

        addAppointmentButton.addActionListener(e -> {
            // Handle adding an appointment
            //        PatientManager.addAppointment(new Scanner(System.in), new Patient());
            addAppointment(optionsFrame);
        });


        showAllPatientsButton.addActionListener(e -> {
            // Handle showing all patients
          //  new PatientManager().displayingAllPatients();
            displayAllPatientsDialog(optionsFrame);
        });


        optionsFrame.add(updateUsernameButton);
        optionsFrame.add(updatePasswordButton);
        optionsFrame.add(addAppointmentButton);
        optionsFrame.add(showAllPatientsButton);


        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsFrame.setLocationRelativeTo(null);
        optionsFrame.setVisible(true);
    }
    private void updateUsername(JFrame parentFrame) {
            JDialog updateUsernameDialog = new JDialog(parentFrame, "Update Username", true);
            updateUsernameDialog.setSize(400, 250);
            updateUsernameDialog.setContentPane(new BackgroundPanel("patient.jpg"));
            updateUsernameDialog.setLayout(new FlowLayout());

            JLabel currentUsernameLabel = new JLabel("Enter your current username:");
            JTextField currentUsernameField = new JTextField(15);

            JLabel newUsernameLabel = new JLabel("Enter the new username:");
            JTextField newUsernameField = new JTextField(15);

            JButton updateButton = new JButton("Update");
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String currentUsername = currentUsernameField.getText();
                    String newUsername = newUsernameField.getText();

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
            updateUsernameDialog.add(newUsernameField);
            updateUsernameDialog.add(updateButton);

            updateUsernameDialog.setVisible(true);
        }

        private void updateUsername(String currentUsername, String newUsername) {
            boolean usernameExists = false;
            ArrayList<Patient> patients = FileOperationsPatient.readAllFromFile();

            for (Patient patient : patients) {
                if (patient.getUsername().equals(currentUsername)) {
                    usernameExists = true;
                    patient.setUsername(newUsername);
                    FileOperationsPatient.writeToFile(patients); // Update the file with the modified data
                    break; // Assuming there is only one patient with the given username
                }
            }

            if (usernameExists) {
                JOptionPane.showMessageDialog(null, "Username updated successfully");
            } else {
                JOptionPane.showMessageDialog(null, "You have entered an incorrect username");
            }
        }

        private void updatePassword(JFrame parentFrame) {
            JDialog updatePasswordDialog = new JDialog(parentFrame, "Update Password", true);
            updatePasswordDialog.setSize(400, 250);
            updatePasswordDialog.setContentPane(new BackgroundPanel("patient.jpg"));
            updatePasswordDialog.setLayout(new FlowLayout());

            JLabel currentPasswordLabel = new JLabel("Enter your current password:");
            JTextField currentPasswordField = new JTextField(15);
            JLabel newPasswordLabel = new JLabel("Enter your new password:");
            JTextField newPasswordField = new JTextField(15);
            JButton updateButton = new JButton("Update");

            updatePasswordDialog.add(currentPasswordLabel);
            updatePasswordDialog.add(currentPasswordField);
            updatePasswordDialog.add(newPasswordLabel);
            updatePasswordDialog.add(newPasswordField);
            updatePasswordDialog.add(updateButton);

            updateButton.addActionListener(new ActionListener() {
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

            updatePasswordDialog.setVisible(true);
        }

        private void updatePassword(JFrame parentFrame, String oldp, String newp) {
            ArrayList<Patient> patients = new ArrayList<>(FileOperationsPatient.readAllFromFile());
            boolean found = false;
            for (Patient patient : patients) {
                if (patient.getPassword().equals(oldp)) {
                    found = true;
                    patient.setPassword(newp);
                    FileOperationsPatient.writeToFile(patients); // Update the file with the modified data
                    break;
                }
            }
            if (found) {
                JOptionPane.showMessageDialog(null, "Password updated successfully");
            } else {
                JOptionPane.showMessageDialog(null, "You have entered an incorrect password");
            }
        }
    private void displayAllPatientsDialog(JFrame parentFrame) {
        JDialog allPatientsDialog = new JDialog(parentFrame, "All Patients", true);
        allPatientsDialog.setSize(800, 500);

        // Set the background of the dialog
        allPatientsDialog.setContentPane(new BackgroundPanel("patient.jpg"));

        PatientManager patientManager = new PatientManager();
        String[] columnNames = {"Name", "Gender", "Mobile", "Email", "Address", "ID","Appointment"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add data to the table model
        for (Patient patient : patientManager.getPatientList()) {
            Object[] rowData = {patient.getName(), patient.getGender(), patient.getMobilenumber(), patient.getEmail(),
                    patient.getAdress(), patient.getPatientID(),patient.getpatientappointments()};
            model.addRow(rowData);
        }

        JTable patientsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(patientsTable);

        allPatientsDialog.add(scrollPane);
        allPatientsDialog.setLocationRelativeTo(parentFrame);
        allPatientsDialog.setVisible(true);
    }

    private void addAppointment(JFrame parentFrame) {
        JDialog addAppointmentDialog = new JDialog(parentFrame, "Add Appointment", true);
        addAppointmentDialog.setSize(400, 250);
        addAppointmentDialog.setContentPane(new BackgroundPanel("appointment2.jpeg"));
        addAppointmentDialog.setLayout(new FlowLayout());

        JLabel dateLabel = new JLabel("Enter the appointment date:");
        JTextField dateField = new JTextField(15);

        JLabel timeLabel = new JLabel("Enter the appointment time:");
        JTextField timeField = new JTextField(15);

        JLabel doctorLabel = new JLabel("Enter the doctor's name:");
        JTextField doctorField = new JTextField(15);

        JLabel patientIDLabel = new JLabel("Enter your patient ID:");
        JTextField patientIDField = new JTextField(15);

        JLabel specializationLabel = new JLabel("Enter the doctor's specialization:");
        JTextField specializationField = new JTextField(15);

        JButton addButton = new JButton("Add");
        addAppointmentDialog.add(dateLabel);
        addAppointmentDialog.add(dateField);
        addAppointmentDialog.add(timeLabel);
        addAppointmentDialog.add(timeField);
        addAppointmentDialog.add(doctorLabel);
        addAppointmentDialog.add(doctorField);
        addAppointmentDialog.add(patientIDLabel);
        addAppointmentDialog.add(patientIDField);
        addAppointmentDialog.add(specializationLabel);
        addAppointmentDialog.add(specializationField);
        addAppointmentDialog.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve input fields for appointment details
                String date = dateField.getText();
                String time = timeField.getText();
                String doctorName = doctorField.getText();
                String patientID = patientIDField.getText();
                String specialization = specializationField.getText();

                addAppointment(date, time, doctorName, patientID, specialization);
                addAppointmentDialog.dispose();
            }
        });

        addAppointmentDialog.setVisible(true);
    }

    private void addAppointment(String date, String time, String doctorName, String patientID, String specialization) {
        ArrayList<Patient> patients = new ArrayList<>(FileOperationsPatient.readAllFromFile());
        boolean found = false;

        ArrayList<Doctor> doctors = new ArrayList<>(FileOperationsDoctor.readAllFromFile());
        for (Patient patient : patients) {
            if (patient.getPatientID().equals(patientID)) {
                // Creating the appointment
                Appointment appointment = new Appointment(date, patientID, time, doctorName, specialization);

                // appointment to the patient's list
                patient.addAppointment(appointment);

                // Finding the doctor with the corresponding specialization
                for (Doctor doctor : doctors) {
                    if (doctor.getSpecialization().equals(specialization)) {
                        // Add the appointment to the doctor's list
                        doctor.addAppointment(appointment);
                        break;
                    }
                }

                found = true;
                break;
            }
        }

        if (found) {
            JOptionPane.showMessageDialog(null, "Appointment added successfully!");
            FileOperationsPatient.writeToFile(patients); // Update the file with the modified patient data
            FileOperationsDoctor.writeToFile(doctors); // Update the file with the modified doctor data
        } else {
            JOptionPane.showMessageDialog(null, "Patient not found with the given ID.");
        }
    }}