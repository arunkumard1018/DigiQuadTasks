/**
 * The {@code Task_01_App$} class represents Functionalities
 *
 * @author ArunKumar D
 */

import task_01.FileProcessorPanel;

import javax.swing.*;
import java.awt.*;
public class Task_01_App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Task_01_App::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JFrame frame = new JFrame("File Processor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        FileProcessorPanel panel = new FileProcessorPanel(frame);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
