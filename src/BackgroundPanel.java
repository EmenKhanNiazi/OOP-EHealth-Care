import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
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

