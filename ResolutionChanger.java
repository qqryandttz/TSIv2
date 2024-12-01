import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResolutionChanger {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("分辨率调节");
        String[] resolutions = { "640x480", "1600x900", "1280x720", "1920x1080" };
        JComboBox<String> resolutionComboBox = new JComboBox<>(resolutions);
        JButton button = new JButton("调整分辨率");

        // 123
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedResolution = (String) resolutionComboBox.getSelectedItem();
                String[] parts = selectedResolution.split("x");
                int width = Integer.parseInt(parts[0]);
                int height = Integer.parseInt(parts[1]);
                frame.setSize(width, height);
            }
        });

        JPanel panel = new JPanel();
        panel.add(resolutionComboBox);
        panel.add(button);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ResolutionChanger::createAndShowGUI);
    }
}