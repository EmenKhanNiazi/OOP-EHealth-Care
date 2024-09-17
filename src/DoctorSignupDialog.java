import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorSignupDialog extends JDialog {
    private JTextField nameField;
    private JTextField genderField;
    private JTextField mobileField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField specializationField;
    private JTextField instituteofspecializationField;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private DoctorManager doctorManager;
    private BackgroundPanel backgroundPanel;

    public DoctorSignupDialog(JFrame parentFrame, DoctorManager doctorManager,String backgroundImageName) {
        super(parentFrame, "Doctor Signup", true);
        setSize(400, 400);

        backgroundPanel = new BackgroundPanel(backgroundImageName);
        setContentPane(backgroundPanel);

        setLocationRelativeTo(parentFrame);
        setLayout(new GridLayout(10, 2));

        this.doctorManager = doctorManager;

        nameField = new JTextField();
        genderField = new JTextField();
        mobileField = new JTextField();
        emailField = new JTextField();
        addressField = new JTextField();
        specializationField = new JTextField();
        instituteofspecializationField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton signupButton = new JButton("Signup");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String gender = genderField.getText();
                String mobile = mobileField.getText();
                String email = emailField.getText();
                String address = addressField.getText();
                String specialization = specializationField.getText();
                String institute = instituteofspecializationField.getText();
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                Doctor newDoc = new Doctor(name, gender, mobile, email, address, specialization, institute, username, password);
                doctorManager.getDoctorList().add(newDoc);
                FileOperationsDoctor.writeToFile(doctorManager.getDoctorList());
                JOptionPane.showMessageDialog(null, "Doctor signed up successfully!");
                dispose();
            }
        });

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Gender:"));
        add(genderField);
        add(new JLabel("Mobile:"));
        add(mobileField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Address:"));
        add(addressField);
        add(new JLabel("Specialization"));
        add(specializationField);
        add(new JLabel("Institute of Specialization:"));
        add(instituteofspecializationField);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(signupButton);

        setVisible(true);
    }
}
