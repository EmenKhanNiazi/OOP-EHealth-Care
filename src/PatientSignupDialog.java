import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class PatientSignupDialog extends JDialog implements Serializable {
    private JTextField nameField;
    private JTextField pIDField;
    private JTextField genderField;
    private JTextField mobileField;
    private JTextField emailField;
    private JTextField addressField;
//    private JTextField dateField;
//    private JTextField timeField;
//    private JTextField didField;
//    private JTextField dnameField;
//    private JComboBox<String> specializationComboBox;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private PatientManager patientManager;
    private BackgroundPanel backgroundPanel;

    public PatientSignupDialog(JFrame parentFrame, PatientManager patientManager, String backgroundImageName) {
        super(parentFrame, "Patient Signup", true);
        setSize(400, 400);
        backgroundPanel = new BackgroundPanel(backgroundImageName);
        setContentPane(backgroundPanel);

        setLocationRelativeTo(parentFrame);
        setLayout(new GridLayout(14, 2));

        this.patientManager = patientManager;

        nameField = new JTextField(15);
        pIDField = new JTextField(15);
        genderField = new JTextField(15);
        mobileField = new JTextField(15);
        emailField = new JTextField(15);
        addressField = new JTextField(15);
//        dateField = new JTextField(15);
//        timeField = new JTextField(15);
//        didField = new JTextField(15);
//        dnameField = new JTextField(15);
//
//        specializationComboBox = new JComboBox<>();
//        ArrayList<Doctor> doc = FileOperationsDoctor.readAllFromFile();
//        for (Doctor doctor2 : doc) {
//            specializationComboBox.addItem(doctor2.getSpecialization());
//        }

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton signupButton = new JButton("Signup");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Extract values from the fields
                String name = nameField.getText();
                String pID = pIDField.getText();
                String gender = genderField.getText();
                String mobile = mobileField.getText();
                String email = emailField.getText();
                String address = addressField.getText();
//                String date = dateField.getText();
//                String time = timeField.getText();
//                String did = didField.getText();
//                String dname = dnameField.getText();
//                String specialization = (String) specializationComboBox.getSelectedItem();
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                // Add your signup logic here, using the provided patientManager
//                Patient newpatient = new Patient(name, gender, mobile, email, address, pID, did, date, time, dname, specialization, username, password);
                Patient newpatient = new Patient(name, gender, mobile, email, address, pID, username, password);
//                newpatient.addAppointment(new Appointment(date, did, time, dname, specialization));

                patientManager.getPatientList().add(newpatient);
                FileOperationsPatient.writeToFile(patientManager.getPatientList());

                        dispose();
                        JOptionPane.showMessageDialog(null, "Patient signed up successfully!");
                        dispose();
            }
        });

        // Add components to the layout
        add(new JLabel("Name: "));
        add(nameField);
        add(new JLabel("Patient ID: "));
        add(pIDField);
        add(new JLabel("Gender: "));
        add(genderField);
        add(new JLabel("Mobile Number: "));
        add(mobileField);
        add(new JLabel("Email: "));
        add(emailField);
        add(new JLabel("Address: "));
        add(addressField);
//        add(new JLabel("Appointment Date: "));
//        add(dateField);
//        add(new JLabel("Appointment Time: "));
//        add(timeField);
//        add(new JLabel("Doctor ID: "));
//        add(didField);
//        add(new JLabel("Doctor Name: "));
//        add(dnameField);
//        add(new JLabel("Doctor Specialization: "));
//        add(specializationComboBox);
        add(new JLabel("Username: "));
        add(usernameField);
        add(new JLabel("Password: "));
        add(passwordField);
        add(signupButton);

        setVisible(true);
    }
}
