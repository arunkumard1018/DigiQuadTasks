package task_01; /**
 * The {@code task_01.FileProcessorPanel$} class represents Functionalities
 *
 * @author ArunKumar D
 */

import task_01.FileProcessor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FileProcessorPanel extends JPanel {
    private final JTextField filePathField = new JTextField(30);
    private final JTextField startRowField = new JTextField(5);
    private final JTable table = new JTable();

    public FileProcessorPanel(JFrame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton browseButton = new JButton("Browse");
        JButton processButton = new JButton("Process File");

        filePathField.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("File Path:"), gbc);
        gbc.gridx = 1; add(filePathField, gbc);
        gbc.gridx = 2; add(browseButton, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Start Row:"), gbc);
        gbc.gridx = 1; add(startRowField, gbc);
        gbc.gridx = 2; add(processButton, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0; add(scrollPane, gbc);

        browseButton.addActionListener(e -> chooseFile(frame));
        processButton.addActionListener(e -> processFile(frame));
    }

    private void chooseFile(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            filePathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void processFile(JFrame frame) {
        String filePath = filePathField.getText();
        if (filePath.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select a file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int startRow;
        try {
            startRow = startRowField.getText().isEmpty() ? 0 : Integer.parseInt(startRowField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid starting row number!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            DefaultTableModel model;
            if (filePath.endsWith(".xlsx")) {
                model = FileProcessor.processExcelFile(filePath, startRow);
            } else if (filePath.endsWith(".csv")) {
                model = FileProcessor.processCSVFile(filePath, startRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Unsupported file format!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
