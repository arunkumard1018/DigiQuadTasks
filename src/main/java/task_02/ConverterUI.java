/**
 * The {@code ConverterUI$} class represents Functionalities
 *
 * @author ArunKumar D
 */

package task_02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ConverterUI {

    private String convertedJson = ""; // Store converted JSON temporarily

    public void createAndShowGUI() {
        JFrame frame = new JFrame("XML to JSON Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JTextField xmlFilePathField = new JTextField(40);
        xmlFilePathField.setEditable(false);
        JButton browseXmlButton = new JButton("Browse XML");
        JButton convertButton = new JButton("Convert to JSON");
        JButton saveButton = new JButton("Save JSON");
        saveButton.setEnabled(false);

        JTextArea jsonTextArea = new JTextArea(20, 60);
        jsonTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(jsonTextArea);
        scrollPane.setPreferredSize(new Dimension(700, 400));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("XML File Path:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(xmlFilePathField, gbc);

        gbc.gridx = 2; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(browseXmlButton, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(convertButton, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(saveButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        frame.add(panel);
        frame.setVisible(true);

        browseXmlButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                xmlFilePathField.setText(selectedFile.getAbsolutePath());
            }
        });

        convertButton.addActionListener(e -> {
            String xmlFilePath = xmlFilePathField.getText();
            if (xmlFilePath.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please select an XML file!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                convertedJson = XmlToJsonConverter.convertXmlToJson(xmlFilePath);
                jsonTextArea.setText(convertedJson);
                saveButton.setEnabled(true);
                JOptionPane.showMessageDialog(frame, "Conversion successful! Now you can save the JSON.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error during conversion: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        saveButton.addActionListener(e -> saveJson(frame));
    }

    private void saveJson(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save JSON File");
        fileChooser.setSelectedFile(new File("output.json"));

        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String jsonFilePath = selectedFile.getAbsolutePath();

            if (!jsonFilePath.toLowerCase().endsWith(".json")) {
                jsonFilePath += ".json";
            }

            try {
                JsonFileHandler.saveJsonToFile(jsonFilePath, convertedJson);
                JOptionPane.showMessageDialog(frame, "JSON saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving JSON: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

