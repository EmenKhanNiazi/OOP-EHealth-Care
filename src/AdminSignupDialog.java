import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminSignupDialog extends JDialog {
    private JTextField nameField;
    private JTextField genderField;
    private JTextField mobileField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private BackgroundPanel backgroundPanel;

    public AdminSignupDialog(JFrame parentFrame, String backgroundImageName) {
        super(parentFrame, "Admin Signup", true);

        backgroundPanel = new BackgroundPanel(backgroundImageName);
        setContentPane(backgroundPanel);

        setSize(400, 300);
        setLocationRelativeTo(parentFrame);

        setLayout(new GridLayout(8, 2));

        nameField = new JTextField();
        genderField = new JTextField();
        mobileField = new JTextField();
        emailField = new JTextField();
        addressField = new JTextField();
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
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                Admin newAdmin = new Admin(name, gender, mobile, email, address, username, password);
                Admin.getAdminList().add(newAdmin);
                FileOperationsAdmin.writeToFile(Admin.getAdminList());
                JOptionPane.showMessageDialog(null, "Admin signed up successfully!");
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
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(signupButton);

        setVisible(true);
    }
}

