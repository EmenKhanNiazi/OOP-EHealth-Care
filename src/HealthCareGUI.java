import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HealthCareGUI extends JFrame {
    private JButton adminButton;
    private JButton doctorButton;
    private JButton patientButton;
    private JButton exitButton;
    private DoctorManager doctorManager;
    private PatientManager patientManager;
    private BackgroundPanel backgroundPanel;

    public HealthCareGUI() {
        setTitle("E-HEALTH CARE SYSTEM");
        setSize(950, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        backgroundPanel = new BackgroundPanel("1706788460035.jpg");
        setContentPane(backgroundPanel);

        // Set the layout to BoxLayout along Y_AXIS
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Create and set the welcome label
        JLabel welcomeLabel = new JLabel("WELCOME TO E HEALTH CARE SYSTEM");
        welcomeLabel.setFont(new Font("Arial",Font.BOLD, 18));  // Set the font to bold
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center align the label
        add(welcomeLabel);

        // Add some vertical space
        add(Box.createRigidArea(new Dimension(0, 20)));

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Adjust as needed
        buttonPanel.setOpaque(false);  // Make the button panel transparent

        adminButton = new JButton("Admin");
        adminButton.addActionListener(new ButtonHandler());
        buttonPanel.add(adminButton);

        doctorButton = new JButton("Doctor");
        doctorButton.addActionListener(new ButtonHandler());
        buttonPanel.add(doctorButton);

        patientButton = new JButton("Patient");
        patientButton.addActionListener(new ButtonHandler());
        buttonPanel.add(patientButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ButtonHandler());
        buttonPanel.add(exitButton);

        // Add the button panel to the frame
        add(buttonPanel);

        doctorManager = new DoctorManager();
        patientManager = new PatientManager();
    }
    static class BackgroundPanel extends JPanel {
        private Image background;
        private String currentImageName;

        public BackgroundPanel(String imageName) {
            // Load the background image based on the given filename
            background = new ImageIcon(imageName).getImage();
            currentImageName = imageName;
        }

        public void setBackgroundImage(String imageName) {
            // Change the background image dynamically
            background = new ImageIcon(imageName).getImage();
            currentImageName = imageName;
            repaint();
        }

        public String getCurrentImageName() {
            return currentImageName;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background image
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }



    class ButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == adminButton) {

                backgroundPanel.setBackgroundImage("admin.jpg");
                handleAdminAction();

            } else if (e.getSource() == doctorButton) {
                backgroundPanel.setBackgroundImage("docc.jpg");
                handleDoctorAction();

            } else if (e.getSource() == patientButton) {
                backgroundPanel.setBackgroundImage("patient.jpg");
                handlePatientAction();

            } else if (e.getSource() == exitButton) {
                System.out.println("Exiting E-HEALTH CARE SYSTEM. Goodbye!");
                System.exit(0);
            }
        }

        private void handleAdminAction() {
            JFrame adminFrame = new JFrame("Admin Login/Signup");
            adminFrame.setSize(400, 200);
            adminFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

            BackgroundPanel adminBackgroundPanel = new BackgroundPanel("admin.jpg");
            adminFrame.setContentPane(adminBackgroundPanel);
            JButton loginButton = new JButton("Login");
            JButton signupButton = new JButton("Signup");

            loginButton.addActionListener(e -> {

                new AdminLoginDialog(adminFrame, adminBackgroundPanel.getCurrentImageName());

            });

               signupButton.addActionListener(e -> {

                new AdminSignupDialog(adminFrame,  adminBackgroundPanel.getCurrentImageName());

            });

            adminFrame.add(loginButton);
            adminFrame.add(signupButton);

            adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            adminFrame.setLocationRelativeTo(null);
            adminFrame.setVisible(true);
        }
        private void handleDoctorAction() {
            JFrame docFrame = new JFrame("Doctor Login/Signup");

            BackgroundPanel doctorBackgroundPanel = new BackgroundPanel("docc.jpg");
            docFrame.setContentPane(doctorBackgroundPanel);
            docFrame.setSize(400, 200);
            docFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

            JButton loginButton = new JButton("Login");
            JButton signupButton = new JButton("Signup");

            // Creating an instance of DoctorManager
            DoctorManager doctorManager = new DoctorManager();

            loginButton.addActionListener(e -> {
                new DoctorLoginDialog(docFrame, doctorManager,doctorBackgroundPanel.getCurrentImageName());
            });

            signupButton.addActionListener(e -> {
                new DoctorSignupDialog(docFrame, doctorManager,doctorBackgroundPanel.getCurrentImageName());
            });

            docFrame.add(loginButton);
            docFrame.add(signupButton);

            docFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            docFrame.setLocationRelativeTo(null);
            docFrame.setVisible(true);
        }

        private void handlePatientAction() {
            JFrame patientFrame = new JFrame("Patient Login/Signup");
            patientFrame.setSize(500, 300);

            BackgroundPanel patientBackgroundPanel = new BackgroundPanel("patient.jpg");
            patientFrame.setContentPane(patientBackgroundPanel);

            patientFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

            JButton loginButton = new JButton("Login");
            JButton signupButton = new JButton("Signup");

            // Creating an instance of PatientManager
            PatientManager patientManager = new PatientManager();

            loginButton.addActionListener(e -> {
                new PatientLoginDialog(patientFrame, patientManager, patientBackgroundPanel.getCurrentImageName());
            });

            signupButton.addActionListener(e -> {
                new PatientSignupDialog(patientFrame, patientManager, patientBackgroundPanel.getCurrentImageName());
            });

            patientFrame.add(loginButton);
            patientFrame.add(signupButton);

            patientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            patientFrame.setLocationRelativeTo(null);
            patientFrame.setVisible(true);
        }


    }
}
