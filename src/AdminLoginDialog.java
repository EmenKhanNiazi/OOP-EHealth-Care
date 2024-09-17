import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminLoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private BackgroundPanel backgroundPanel;

    public AdminLoginDialog(JFrame parentFrame, String backgroundImageName) {
        super(parentFrame, "Admin Login", true);

        backgroundPanel = new BackgroundPanel(backgroundImageName);
        setContentPane(backgroundPanel);

        setSize(300, 150);
        setLocationRelativeTo(parentFrame);

        setLayout(new FlowLayout());

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if (Admin.signin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Logged in successfully!");
                    setVisible(false);
                    showOptionsDialog(backgroundImageName); // Pass the backgroundImageName to the method
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

    private void showOptionsDialog(String backgroundImageName) {
        JFrame optionsFrame = new JFrame("Admin Options");
        optionsFrame.setSize(400, 200);

        // Set the background of the options frame based on the backgroundImageName
        optionsFrame.setContentPane(new BackgroundPanel(backgroundImageName));

        optionsFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JButton addDoctorButton = new JButton("Add New Doctor");
        JButton addPatientButton = new JButton("Add New Patient");
        JButton showDoctorsButton = new JButton("Show All Doctors");
        JButton showPatientsButton = new JButton("Show All Patients");
        JButton showAppointmentsButton = new JButton("Show specific appointments:");

        JButton exitButton = new JButton("Exit");

        addDoctorButton.addActionListener(e -> {
            new DoctorSignupDialog(optionsFrame, new DoctorManager(),"docc.jpg");
        });

        addPatientButton.addActionListener(e -> {
            new PatientSignupDialog(optionsFrame, new PatientManager(),"patient.jpg");
        });

        showDoctorsButton.addActionListener(e -> {
          //  new DoctorManager().displayAllDoctors();
            displayAllDoctorsDialog(optionsFrame);
        });

        showPatientsButton.addActionListener(e -> {
            //new PatientManager().displayingAllPatients();
            displayAllPatientsDialog(optionsFrame);
        });
        showAppointmentsButton.addActionListener(e -> {
            //new PatientManager().displayingAllPatients();
            //displayAllPatientsDialog(optionsFrame);
            showAppointmentsDialog(optionsFrame);
        });

        exitButton.addActionListener(e -> {
            System.out.println("Exiting Admin Options.");
            optionsFrame.dispose();

        });

        optionsFrame.add(addDoctorButton);
        optionsFrame.add(addPatientButton);
        optionsFrame.add(showDoctorsButton);
        optionsFrame.add(showPatientsButton);
        optionsFrame.add(showAppointmentsButton);
        optionsFrame.add(exitButton);

        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsFrame.setLocationRelativeTo(null);
        optionsFrame.setVisible(true);

    }


    private void showAppointmentsDialog(JFrame parentFrame){
        JDialog allDoctorsDialog = new JDialog(parentFrame, "All Appointments", true);
        allDoctorsDialog.setSize(800, 500);

        allDoctorsDialog.setContentPane(new BackgroundPanel("docc.jpg"));

        String[] columnNames = {"Date", "Time", "Doctor ID", "Doctor Name", "Type of Doctor"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        ArrayList<Doctor> doctors = FileOperationsDoctor.readAllFromFile();
        String specialization = JOptionPane.showInputDialog(parentFrame, "Enter Doctor's Specialization:");
        Doctor currentDoc = new Doctor();

        for (Doctor doc : doctors) {
            if (doc.getSpecialization().equals(specialization)) {
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
        allDoctorsDialog.add(scrollPane);

        allDoctorsDialog.setVisible(true);
    }

    private void displayAllDoctorsDialog(JFrame parentFrame) {
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
}